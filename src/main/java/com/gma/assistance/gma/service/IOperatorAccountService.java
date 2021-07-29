package com.gma.assistance.gma.service;

import com.gma.assistance.gma.exception.InvalidTokenException;
import com.gma.assistance.gma.exception.UnkownIdentifierException;

public interface IOperatorAccountService {
    void forgottenPassword(final String userName) throws UnkownIdentifierException;

    void updatePassword(final String password, final String token) throws InvalidTokenException, UnkownIdentifierException;

    boolean loginDisabled(final String username);
}