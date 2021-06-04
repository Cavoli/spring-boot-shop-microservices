package com.cavoli.auth.service;

import com.cavoli.auth.model.User;
import com.cavoli.auth.repository.UserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service //this one is for crud
public class UserServiceImpl implements UserService {

    private UserDetailRepository userDetailRepository;

    @Autowired
    public UserServiceImpl(UserDetailRepository userDetailRepository) {
        this.userDetailRepository = userDetailRepository;
    }

    @Override
    public void saveUser(User user) throws DataAccessException {
        this.userDetailRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) throws DataAccessException {
        this.userDetailRepository.deleteById(Math.toIntExact(id));
    }

    @Override
    public User findUser(String name) throws DataAccessException {
        return this.userDetailRepository.findByUsername(name).orElse(null);
    }
}
