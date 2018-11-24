package br.edu.unitri.posjava.tcc.med4you.service;

import br.edu.unitri.posjava.tcc.med4you.jobs.ReminderNotificationJob;
import br.edu.unitri.posjava.tcc.med4you.model.Reminder;
import br.edu.unitri.posjava.tcc.med4you.model.User;
import br.edu.unitri.posjava.tcc.med4you.repository.ReminderRepository;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by edufratari on 31/07/18.
 */
@Component
public class ReminderService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ReminderRepository repository;

    @Autowired
    private UserService userService;

    public void save(Reminder reminder) {
        logger.info(reminder.toString());
        User user = reminder.getUser();
        Long id = user.getId();
        User user_ = userService.findById(id);
        reminder.setUser(user_);
        repository.save(reminder);
        scheduleNotification(reminder);
    }

    public void update(Reminder reminder) {
        repository.save(reminder);
    }

    public List<Reminder> findAll() {
        return repository.findAll();
    }

    public Reminder findById(Long id) {
        return repository.findById(id).get();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<Reminder> findByUserId(Long userId) {
        return repository.findByUserId(userId);
    }

    public void scheduleNotification(Reminder reminder) {

        if (reminder.getFirstDose().compareTo(new Date()) <= 0) {
            logger.info("Data no passado, por isso não foi agendado");
            return;
        }
        Map map = new HashMap<String, Reminder>();
        map.put("REMINDER", reminder);

        JobDetail job = JobBuilder.newJob(ReminderNotificationJob.class)
                .withIdentity("reminderNotificationJob"+new Date().toString(), "REMINDER")
                .usingJobData(new JobDataMap(map)).build();

        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("reminderNotificationTrigger"+new Date().toString(), "REMINDER")
                .startAt(reminder.getFirstDose())
                .withSchedule(SimpleScheduleBuilder.simpleSchedule())
                .build();

        Scheduler scheduler = null;

            try {
            scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.start();
            scheduler.scheduleJob(job, trigger);
            } catch (SchedulerException e) {
                try{
                scheduler = new StdSchedulerFactory().getScheduler("DefaultQuartzScheduler");
                scheduler.start();
                scheduler.scheduleJob(job, trigger);
                } catch (SchedulerException e2) {
                    e2.printStackTrace();

                }
            }
/*            try{
                scheduler = new StdSchedulerFactory().getScheduler("DefaultQuartzScheduler");
                scheduler.start();
                scheduler.scheduleJob(job, trigger);
            }catch (Exception e){
                e.printStackTrace();
                scheduler = new StdSchedulerFactory().getScheduler();
                scheduler.start();
                scheduler.scheduleJob(job, trigger);
            }*/

    }

    public List<Reminder> findByResponsableUserId(Long userId) {
        return this.findByUserId(userService.findByResponsableId(userId).getId());
    }
}
