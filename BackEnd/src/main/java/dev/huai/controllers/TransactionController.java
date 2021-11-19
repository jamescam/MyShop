package dev.huai.controllers;

import dev.huai.models.Transaction;

import dev.huai.services.TransactionService;
import dev.huai.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

@Controller
@CrossOrigin
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserService userService;

    @PostMapping("/transaction")
    @ResponseBody
    public ResponseEntity<?> addTransaction(@RequestBody Transaction transaction){
        if(transactionService.addTransaction(transaction)){
            return new ResponseEntity<>(true, HttpStatus.CREATED);
        }else
            return new ResponseEntity<>(false, HttpStatus.NOT_ACCEPTABLE);
    }
// "/user/{id}/paidtransactions
    @GetMapping("/paidtransactions")
    @ResponseBody
    public ResponseEntity<?> getPaidTransactionsByUser(){
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String authToken = request.getHeader("Authorization");
        String[] str = authToken.split(":");
        List<Transaction> transactionList = transactionService.getPaidTransactionsByUser(Integer.parseInt(str[0]));
        if(transactionList==null){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }else
            return new ResponseEntity<>(transactionList, HttpStatus.OK);
    }

    @GetMapping("/paidtransactions/{id}")
    @ResponseBody
    public ResponseEntity<?> getPaidTransactionsById(@PathVariable("id")int id){

        List<Transaction> transactionList = transactionService.getPaidTransactionsByUser(id);
        if(transactionList==null){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }else
            return new ResponseEntity<>(transactionList, HttpStatus.OK);
    }

    @GetMapping("/unpaidtransactions")
    @ResponseBody
    public ResponseEntity<?> getUnpaidTransactionsByUser(){
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String authToken = request.getHeader("Authorization");
        String[] str = authToken.split(":");
        List<Transaction> transactionList = transactionService.getUnpaidTransactionsByUser(Integer.parseInt(str[0]));
        if(transactionList==null){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }else
            return new ResponseEntity<>(transactionList, HttpStatus.OK);
    }

    @PutMapping("/transaction")
    @ResponseBody
    public ResponseEntity<?> updateTransactionPaid(@RequestBody Transaction transaction){

        if(transactionService.updateTransactionToPaidByUserId(transaction.getUser().getUserId())){
            return new ResponseEntity<>(true, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(false,HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping("/transaction/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteTransactionPaid(@PathVariable("id")int id){

        if(transactionService.deleteTransactionById(id)){
            return new ResponseEntity<>(true, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(false,HttpStatus.UNAUTHORIZED);
        }
    }
}
