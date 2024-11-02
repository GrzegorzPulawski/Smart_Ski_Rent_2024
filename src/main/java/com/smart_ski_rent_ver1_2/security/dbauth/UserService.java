package com.smart_ski_rent_ver1_2.security.dbauth;

import java.util.Optional;

public interface UserService {

    Optional<AppUser> getApplicationUserBy(String userName);

}