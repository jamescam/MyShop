package dev.huai.controllers;

import dev.huai.models.Amount;
import dev.huai.models.User;
import dev.huai.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

@Controller
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    @ResponseBody
    public ResponseEntity<?> getUserByCredential(@RequestBody User user){
        User returnedUser = userService.getUserByCredential(user.getEmail(), user.getPassword());
        if(returnedUser ==null){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        else{
            HttpHeaders responseHeaders = new HttpHeaders();
            //1:
            String str = returnedUser.getUserId() + ":";
            String token;
            if(returnedUser.isManager())
                token = str + "MANAGER";
            else
                token = str + "CUSTOMER";
            responseHeaders.set("Authorization", token);
            responseHeaders.set("Access-Control-Expose-Headers", "Authorization");
            return new ResponseEntity<>(returnedUser, responseHeaders, HttpStatus.ACCEPTED);
        }
    }

    @GetMapping("/user/{id}")
    @ResponseBody
    public ResponseEntity<?> getUserById(@PathVariable("id")int id){
        User returnedUser = userService.getUserById(id);
        if(returnedUser == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else{
            return new ResponseEntity<>(returnedUser, HttpStatus.ACCEPTED);
        }
    }

    @GetMapping("/alluser")
    @ResponseBody
    public ResponseEntity<?> getAllUserByManager(){
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String authToken = request.getHeader("Authorization");
        String[] str = authToken.split(":");
        System.out.println("token is "+ authToken);
        //str[0] is the userId
        //str[1] is the MANAGER/CUSTOMER
        //get user information by userId
        // compare user.isManager with str[1]
        //if...
        User user = userService.getUserById(Integer.parseInt(str[0]));
        if(user.isManager()==true && str[1].equals("MANAGER")){
            return new ResponseEntity<>(userService.getUserByManager(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/newuser")
    @ResponseBody
    public ResponseEntity<?> signUpNewCustomer(@RequestBody User user){
        if(userService.addUser(user)){
            return new ResponseEntity<>(true, HttpStatus.CREATED);
        }else
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    @PutMapping("/updatebalance")
    @ResponseBody
    public ResponseEntity<?> updateBalanceOnUser(@RequestBody Amount amount){
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String authToken = request.getHeader("Authorization");
        String[] str = authToken.split(":");

            // this is a deposit update
            if(userService.updateBalanceDeposit(Integer.parseInt(str[0]), new BigDecimal(amount.getAmount())))
                return new ResponseEntity<>(true, HttpStatus.ACCEPTED);
            else
                return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);

    }

    @PutMapping("/password")
    @ResponseBody
    public ResponseEntity<?> updatePassword(@RequestBody User user){
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String authToken = request.getHeader("Authorization");
        String[] str = authToken.split(":");

        // this is a deposit update
        if(userService.updateUserPassword(Integer.parseInt(str[0]), user.getPassword()))
            return new ResponseEntity<>(true, HttpStatus.ACCEPTED);
        else
            return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);

    }
}
