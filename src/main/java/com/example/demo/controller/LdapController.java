package com.example.demo.controller;

import com.example.demo.po.Person;
import com.example.demo.service.OdmPersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: sugare
 * @Description:
 * @Date: Created in 2018/09/07 0:50
 * @Modified by:
 */
@RestController
public class LdapController {
    @Autowired
    private OdmPersonRepo odmPersonRepo;
    
    @RequestMapping(value = "/findOne",method = RequestMethod.GET)
    public Person findByCn(@RequestParam(name = "cn",required = true) String cn){
        return odmPersonRepo.findByCn(cn);
    }

    @RequestMapping(value = "/findAll",method = RequestMethod.GET)
    public List<Person> findAll(){
        return odmPersonRepo.findAll();
    }

    @PostMapping(value = "/create")
    public Person create(@RequestParam(name = "cn") String cn,@RequestParam(name = "sn") String sn,@RequestParam(name = "userPassword") String userPassworld){
        Person person = new Person();
        person.setCn(cn);
        person.setSn(sn);
        person.setUserPassword(userPassworld);
        return odmPersonRepo.create(person);
    }

    @PostMapping(value = "/update")
    public Person update(@RequestParam(name = "cn") String cn,@RequestParam(name = "sn") String sn,@RequestParam(name = "userPassword") String userPassworld){
        Person person = new Person();
        person.setCn(cn);
        person.setSn(sn);
        person.setUserPassword(userPassworld);
        return odmPersonRepo.modifyPerson(person);
    }

    @PostMapping(value = "/delete")
    public void delete(@RequestParam(name = "cn")String cn){
        Person person = new Person();
        person.setCn(cn);
        odmPersonRepo.deletePerson(person);
    }

}