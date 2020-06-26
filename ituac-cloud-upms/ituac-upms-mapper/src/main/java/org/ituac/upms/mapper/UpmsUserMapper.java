package org.ituac.upms.mapper;

import org.ituac.api.upms.model.entity.SysUsers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UpmsUserMapper extends JpaRepository<SysUsers, Long> {

    SysUsers findByUsername(String username);

}