package br.edu.unitri.posjava.tcc.med4you.service;

import br.edu.unitri.posjava.tcc.med4you.dto.UserDTO;
import br.edu.unitri.posjava.tcc.med4you.model.User;
import br.edu.unitri.posjava.tcc.med4you.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by edufratari on 18/07/18.
 */
@Component
public class UserService {

    @Autowired
    private UserRepository repository;

    public void save(User user) {
        repository.save(user);
    }

    public void update(User user) {
        repository.save(user);
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(Long id) {
        return repository.findById(id).get();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public boolean validateExists(Long id) {
        User user = null;
        try {
            user = this.findById(id);
        } catch (Exception e) {
            return false;
        }
        if (user != null) {
            return true;
        }
        return false;
    }

    public boolean autenticate(User user) {

        User u = repository.findByUsername(user.getUsername());

        if (u == null)
            return false;

        if (u.getPassword().equals(user.getPassword())) {
            return true;
        }
        return false;

    }

    public boolean isLogged() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof User) {
            return true;
        } else {
            return false;
        }
    }

    public UserDTO findLogged() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof User) {
            User u = this.findById(((User) principal).getId());

            UserDTO user = u.toDTO();

            return user;
        } else {
            String nome = principal.toString();
            UserDTO user = new UserDTO();
            user.setName(nome);
            user.setUsername(nome);
            return user;
        }
    }

    public User findByUsername(String username) {
        return repository.findByUsername(username);
    }

    public UserDTO findByEmail(String email) {

        return repository.findByEmail(email).toDTO();
    }

    public UserDTO findByCpf(String cpf) {
        return repository.findByCPF(cpf).toDTO();
    }

    public UserDTO findByResponsableId(Long userId) {
        UserDTO dto = null;
        User user = repository.findByResponsableId(userId);
        if(user!= null){
            dto = user.toDTO();
        }
        return dto;

    }

    public UserDTO findByFacebookId(Long fbId) {
        return repository.findByFacebookId(fbId);

    }
}
