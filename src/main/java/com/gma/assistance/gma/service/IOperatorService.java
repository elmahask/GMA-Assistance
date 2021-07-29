package com.gma.assistance.gma.service;

import com.gma.assistance.gma.entity.Operator;
import com.gma.assistance.gma.exception.InvalidTokenException;
import com.gma.assistance.gma.exception.UnkownIdentifierException;
import com.gma.assistance.gma.exception.UserAlreadyExistException;

public interface IOperatorService {

    Operator findByEmail(String email);

    Operator findByUserName(String userName);

    Operator registerOperator(Operator operator) throws UserAlreadyExistException;

    void sendRegistrationConfirmationEmail(Operator operator);

    //    void register(final UserData user) throws UserAlreadyExistException;
    boolean checkIfUserExist(final String email);
//    void sendRegistrationConfirmationEmail(final Operator user);
    boolean verifyUser(final String token) throws InvalidTokenException;

    Operator getUserById(final String id) throws UnkownIdentifierException;
}
