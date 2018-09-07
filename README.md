## 1. run openldap
```
•100% ➜ docker run -d -P osixia/openldap:1.2.2
```

## 2. check ldap

user: admin
pass: admin

```
•100% ➜ docker ps                                                          
CONTAINER ID        IMAGE                                COMMAND                 CREATED             STATUS              PORTS                                            NAMES
91a124f34b81        osixia/openldap:1.2.2                "/container/tool/run"   19 hours ago        Up 19 hours         0.0.0.0:32774->389/tcp, 0.0.0.0:32773->636/tcp   musing_curie

•100% ➜ docker exec 91a124f34b81 ldapsearch -x -H ldap://localhost -b dc=example,dc=org -D "cn=admin,dc=example,dc=org" -w admin
# extended LDIF
#
# LDAPv3
# base <dc=example,dc=org> with scope subtree
# filter: (objectclass=*)
# requesting: ALL
#

# example.org
dn: dc=example,dc=org
objectClass: top
objectClass: dcObject
objectClass: organization
o: Example Inc.
dc: example

# admin, example.org
dn: cn=admin,dc=example,dc=org
objectClass: simpleSecurityObject
objectClass: organizationalRole
cn: admin
description: LDAP administrator
userPassword:: e1NTSEF9WjVhVnYyU2wzN2R3Tk9kcndvK016ZklwVWFUemVFWkQ=

# song1, example.org
dn: cn=song1,dc=example,dc=org
sn: 22
cn: song1
objectClass: top
objectClass: person
objectClass: organizationalPerson
objectClass: inetOrgPerson
userPassword:: MTIzNDU2

# song2, example.org
dn: cn=song2,dc=example,dc=org
cn: song2
sn: 12
objectClass: organizationalPerson
objectClass: person
objectClass: top
userPassword:: MTIzNDU2

# search result
search: 2
result: 0 Success

# numResponses: 5
# numEntries: 4


``` 

## CRUD

**add user**
```
curl -X POST -d "cn=song2&userPassword=123456&sn=12" localhost:8080/create
```


**delete user**
```
curl -X POST -d "cn=song2" localhost:8080/delete
```


**update user**
```
curl -X POST -d "cn=song2&userPassword=123456&sn=12" localhost:8080/update
```

**select**
```
http://localhost:8080/findAll
curl http://localhost:8080/findOne\?cn\=song1
```