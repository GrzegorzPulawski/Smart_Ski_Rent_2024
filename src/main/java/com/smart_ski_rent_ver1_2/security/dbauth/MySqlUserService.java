package com.smart_ski_rent_ver1_2.security.dbauth;

import com.smart_ski_rent_ver1_2.security.entity.AppUserEntity;
import com.smart_ski_rent_ver1_2.security.repositories.AppUserRepository;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("mysql")
public class MySqlUserService implements UserService {
    private  final AppUserRepository appUserRepository;

    public MySqlUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }
    @Override
    public Optional<AppUser> getApplicationUserBy(String username) {
        return getApplicationUsers()
                .stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst();
    }
  public List<AppUser> getApplicationUsers() {
        return appUserRepository.findAll()
                .stream()
                .map(this::mapEntityToModel)
                .collect(Collectors.toList());
    }
    public AppUserEntity saveUser(AppUserEntity user) {
        return appUserRepository.save(user);
    }
    private AppUser mapEntityToModel(AppUserEntity entity) {
        return new AppUser(
                entity.getPassword(),
                entity.getUsername(),
                entity.getAuthorities(),
                entity.isAccountNonExpired(),
                entity.isAccountNonLocked(),
                entity.isCredentialsNonExpired(),
                entity.isEnabled()
        );
    }
}
