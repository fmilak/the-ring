package com.milak.service;

import com.milak.model.Role;
import com.milak.model.User;

public class UserUploadLimitService implements UploadLimitService {

    @Override
    public boolean checkUpload(User user) {
        if (user.getRole() != Role.USER) {
            return false;
        }

        return user.getMaxLimit() > user.getCurrentLimit();
    }
}
