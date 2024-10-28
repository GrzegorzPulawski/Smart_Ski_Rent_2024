package com.smart_ski_rent_ver1_2.security.dbauth;

import com.google.common.collect.Lists;
import com.smart_ski_rent_ver1_2.security.model.CompanyUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.smart_ski_rent_ver1_2.security.entity.AppUserRole.DEVEL;

@Service("fake")
public class FakeUserService implements UserService{
    private final PasswordEncoder passwordEncoder;

    public FakeUserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public Optional<AppUser> getApplicationUserBy(String userName) {
        return getApplicationUsers()
                .stream()
                .filter(user -> user.getUsername().equals(userName))
                .findFirst();
    }
    private List<AppUser> getApplicationUsers() {
        return Lists.newArrayList(
                new AppUser(
                        passwordEncoder.encode("123456"),
                        "develop",
                        DEVEL.getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true
                )
        );
    }
}
