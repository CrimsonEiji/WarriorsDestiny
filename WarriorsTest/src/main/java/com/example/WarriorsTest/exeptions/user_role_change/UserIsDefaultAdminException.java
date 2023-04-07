package com.example.WarriorsTest.exeptions.user_role_change;

import com.example.WarriorsTest.error_strings.ErrorStrings;

public class UserIsDefaultAdminException extends RuntimeException implements AdminPanelException{

    private final boolean isAdd ;
    public UserIsDefaultAdminException( boolean isAdd) {
        super(ErrorStrings.USER_IS_DEFAULT_ADMIN);
        this.isAdd = isAdd;
    }

    public boolean isAdd() {
        return isAdd;
    }
}
