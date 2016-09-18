package makarchuk.test.controller;

import java.util.Date;

import javax.servlet.ServletException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class LoginController { 
    
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public LoginResponse login() throws ServletException {    	
    	
        return new LoginResponse(Jwts.builder()           
            .setIssuedAt(new Date())
            .signWith(SignatureAlgorithm.HS256, "secretkey")
            .compact());
    }	    
   
    
    @SuppressWarnings("unused")
    private static class LoginResponse {
        public String token;

        public LoginResponse(final String token) {
            this.token = token;
        }
    }

}
