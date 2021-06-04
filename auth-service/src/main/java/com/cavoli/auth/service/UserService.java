//Müştərilərin yaradılması, silinməsi və dəyişdirilməsi (CRUD).
//        Addresslərin əlavə olunması
//        Müşdərilərin (name,surname,username) görə axtarışı

package com.cavoli.auth.service;

import com.cavoli.auth.model.User;
import org.springframework.dao.DataAccessException;

public interface UserService {

    void saveUser(User user) throws DataAccessException;

    void deleteUser(Long id) throws DataAccessException;

    User findUser(String name) throws DataAccessException;

}
