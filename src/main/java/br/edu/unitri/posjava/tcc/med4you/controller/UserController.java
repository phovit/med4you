package br.edu.unitri.posjava.tcc.med4you.controller;

import br.edu.unitri.posjava.tcc.med4you.dto.UserDTO;
import br.edu.unitri.posjava.tcc.med4you.model.User;
import br.edu.unitri.posjava.tcc.med4you.service.UserService;
import org.apache.commons.logging.impl.Log4JLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by edufratari on 18/07/18.
 */
@RestController
@RequestMapping("users")
public class UserController {

    Logger logger = Logger.getLogger(this.getClass().getName());
    @Autowired
    private UserService service;

    @RequestMapping(value = "/isLogged", method = RequestMethod.GET)
    public boolean isLogged() {
        return service.isLogged();
    }

    @RequestMapping(value = "/logged", method = RequestMethod.GET)
    public UserDTO findLogged() {
        return service.findLogged();
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public UserDTO authenticate(@RequestBody User u) {

        if (service.autenticate(u)) {
            u = service.findByUsername(u.getUsername());
            UserDTO user = new UserDTO();
            user.setName(u.getName());
            user.setUsername(u.getUsername());
            user.setAddress(u.getAddress());
            user.setBirthDate(u.getBirthDate());
            user.setCellPhone(u.getCellPhone());
            user.setCpf(u.getCpf());
            user.setEmail(u.getEmail());
            user.setId(u.getId());
            user.setIdentity(u.getIdentity());
            user.setPhone(u.getPhone());

            return user;

        } else {
            return null;
        }
    }


    @RequestMapping(method = RequestMethod.POST)
    public void save(@RequestBody User user) {
        service.save(user);
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public void register(@RequestBody User user) {
        service.save(user);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void update(@RequestBody User user) {
        service.update(user);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<UserDTO> findAll() {
        List<UserDTO> dtos = new ArrayList<>();
        for(User user : service.findAll()){
            dtos.add(user.toDTO());
        }
        return dtos;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public UserDTO findById(@PathVariable("id") Long id) {
        return service.findById(id).toDTO();
    }

    @RequestMapping(value = "/findByCpf/{cpf}", method = RequestMethod.GET)
    public UserDTO findByCpf(@PathVariable("cpf") String cpf) {
        return service.findByCpf(cpf);
    }

    @RequestMapping(value = "/findByEmail/{email}", method = RequestMethod.GET)
    public UserDTO findByEmail(@PathVariable("email") String email) {
        return service.findByEmail(email);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(Long id) {
        service.delete(id);
    }


    @RequestMapping(value = "/getDependente", method = RequestMethod.GET)
    public UserDTO getDependente(){
        return service.findByResponsableId(service.findLogged().getId());
    }

    @RequestMapping(value = "/updateFirebaseToken", method = RequestMethod.POST)
    public void updateFirebaseToken(@RequestBody String token){
        User user = new User().fromDTO(service.findLogged());
        user.setFirebaseToken(token);
        service.save(user);
    }

    @RequestMapping(value = "/updateFirebaseTokenByUsername/{username}", method = RequestMethod.POST)
    public void updateFirebaseToken(@RequestBody String token, @PathVariable("username") String username){
        User user = service.findByUsername(username);
        token = token.replaceAll("token=","");
        user.setFirebaseToken(token);
        logger.info(token);
        service.save(user);
    }
}
