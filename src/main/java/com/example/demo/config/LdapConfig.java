package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.ldap.NamingException;
import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;

import javax.naming.directory.DirContext;

@Configuration
@PropertySource("classpath:application.properties")
public class LdapConfig {

//    spring.ldap.urls=ldap://127.0.0.1:32774
//    spring.ldap.base=dc=example,dc=org
//    spring.ldap.username=cn=admin,dc=example,dc=org
//    spring.ldap.password=admin

    @Value("${spring.ldap.urls}")
    private String urls;

    @Value("${spring.ldap.base}")
    private String base;

    @Value("${spring.ldap.username}")
    private String username;

    @Value("${spring.ldap.password}")
    private String password;

    @Bean
    public LdapTemplate ldapTemplate(){
        return new LdapTemplate(ldapContextSource());
    }

    @Bean
    public LdapContextSource ldapContextSource(){
        LdapContextSource ldapContextSource = new LdapContextSource();
        ldapContextSource.setBase(base);
        ldapContextSource.setUrl(urls);
        ldapContextSource.setUserDn(username);
        ldapContextSource.setPassword(password);
        return ldapContextSource;
    }

}
