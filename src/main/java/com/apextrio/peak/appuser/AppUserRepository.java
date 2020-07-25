package com.apextrio.peak.appuser;

import com.apextrio.peak.appuser.AppUser;
import org.springframework.data.repository.CrudRepository;

public interface AppUserRepository extends CrudRepository<AppUser, Long> {
    public AppUser findByUsername(String username);
}
