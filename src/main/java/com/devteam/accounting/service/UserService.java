package com.devteam.accounting.service;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * User: pancara
 * Date: 1/27/14
 * Time: 9:56 AM
 */

public interface UserService {

    boolean validateUser(String userId, String password);

}
