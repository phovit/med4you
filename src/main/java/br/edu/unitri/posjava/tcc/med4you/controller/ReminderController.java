package br.edu.unitri.posjava.tcc.med4you.controller;

import br.edu.unitri.posjava.tcc.med4you.model.Reminder;
import br.edu.unitri.posjava.tcc.med4you.service.ReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by edufratari on 31/07/18.
 */
@RestController
@RequestMapping("reminders")
public class ReminderController {

    @Autowired
    private ReminderService service;

    @RequestMapping(method = RequestMethod.POST)
    public void save(@RequestBody Reminder reminder) {
        service.save(reminder);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void update(@RequestBody Reminder reminder) {
        service.update(reminder);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Reminder> findAll() {
        return service.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Reminder findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @RequestMapping(value = "/findByUserId/{userId}", method = RequestMethod.GET)
    public List<Reminder> findByUserId(@PathVariable("userId") Long userId) {
        return service.findByUserId(userId);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
        service.delete(id);
    }

    @RequestMapping(value = "/findByResponsableUserId/{userId}", method = RequestMethod.GET)
    public List<Reminder> findByResponsableUserId(@PathVariable("userId") Long userId) {
        return service.findByResponsableUserId(userId);
    }

}
