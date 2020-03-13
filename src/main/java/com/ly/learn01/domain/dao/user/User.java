package com.ly.learn01.domain.dao.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class User implements UserDetails {

    private String username;
    private String password;
    private List<Role> roles;
    private List<GrantedAuthority> authorityList;


    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<>();
        for (Role role : roles) {
            authorityList.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        }
        return list;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    ///账号未过期
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /// 账号未锁定
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    ///凭证未过期
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /// 已经启用
    @Override
    public boolean isEnabled() {
        return true;
    }
}
