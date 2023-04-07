package com.example.WarriorsTest.exeptions.user_role_change;

import com.example.WarriorsTest.error_strings.ErrorStrings;

public class UserDoesntExistException extends RuntimeException implements AdminPanelException {

    private final boolean isAdd ;
    public UserDoesntExistException(String username, boolean isAdd) {
        super(String.format(ErrorStrings.USER_DOESNT_EXIST, username));
        this.isAdd = isAdd;
    }

    public boolean isAdd() {
        return isAdd;
    }
}
