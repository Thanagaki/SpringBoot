package web.service;

import org.springframework.stereotype.Service;
import web.model.User;
import web.repository.UserRepository;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id){
        return userRepository.findById(id).orElse(null);
    }

     public void deleteById (Long id) {
        userRepository.deleteById(id);
     }

     public void update(User user, Long id) {
        User userToBeUpdate = userRepository.findById(id).orElse(null);
         userToBeUpdate.setAge(user.getAge());
         userToBeUpdate.setName(user.getName());
         userToBeUpdate.setSurname(user.getSurname());
         userToBeUpdate.setEmail(user.getEmail());
        userRepository.save(userToBeUpdate);
     }

     public void create(User user) {
        userRepository.save(user);
     }


}
