package org.kavus.lifeleftws;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.criteria.Predicate;
import java.time.Period;
import java.util.List;

import org.kavus.lifeleftlib.ntt.User;
import org.kavus.lifeleftlib.repo.UserRepository;
import org.springframework.data.jpa.domain.Specification;

@WebService
public class LifeLeftService {
    protected static Specification<User> hasCredentials(String loginOrEmail,String password){
        return (user,cq,cb)->{
            Predicate
                loginOK=cb.equal(user.get("loginData").get("login"),loginOrEmail),
                emailOK=cb.equal(user.get("loginData").get("email"),loginOrEmail),
                pwdOK=cb.equal(user.get("loginData").get("password"),password),
                credentialsOK=cb.and(cb.or(loginOK,emailOK),cb.and(pwdOK));
            return credentialsOK;
        };
    }
    @WebMethod
    public Period remainingLifetime(String loginOrEmail,String password, UserRepository userRepo){
        List<User> users=userRepo.findAll(hasCredentials(loginOrEmail,password));
        if(users.isEmpty()) return null;
        return users.get(0).getExpectedLifeLeft();
    }
}
