package com.cnesoa.utils;

import com.cnesoa.domain.Person.User;
import com.cnesoa.manager.Person.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

/**
 * Created by Maxime on 04/05/2016.
 * Class used to generate the credentials
 */
@Service
public class UserCredentialsGenerator {

    private static final String password = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final String code = "0123456789";

    private static SecureRandom rnd = new SecureRandom();

    private UserManager userManager;

    @Autowired
    public void setUserManager(UserManager userManager){
        this.userManager = userManager;
    }


    /**
     * Generated a username : it takes the first 2 letters of the surname, the whole first name
     * and add a number that is incremented if the username already exists
     * @param user
     * @return
     */
    public String generateUsername(User user){
        Integer incr = 0;
        String username = user.getPrenom().substring(0, 2).toLowerCase() + user.getNom().toLowerCase();
        String usernameTmp = username;
        while (!userManager.checkUniqueUsername(usernameTmp)){
            usernameTmp = username + incr.toString();
            incr++;
        }
        return usernameTmp;
    }

    /**
     * Generate a 10 length random password
     * @return
     */
    public String generatePassword(){
        //TODO enlever les commentaires et le return pass
//        StringBuilder sb = new StringBuilder(10);
//        for (int i = 0; i < 10; i++){
//            sb.append(password.charAt(rnd.nextInt(password.length())));
//        }
//        return sb.toString();
        return "pass";
    }

    /**
     * Generate a 4 length random code
     * @return
     */
    public String generateProfessorCode(){
        //TODO enlever les commentaires et return code
//        StringBuilder sb = new StringBuilder(4);
//        for (int i = 0; i < 4; i++){
//            sb.append(code.charAt(rnd.nextInt(code.length())));
//        }
//        return sb.toString();
        return "0000";
    }
}
