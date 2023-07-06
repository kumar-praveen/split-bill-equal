package org.example.splitbill.service;

import org.example.splitbill.entity.Balance;
import org.example.splitbill.entity.Expense;
import org.example.splitbill.entity.User;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AccountService {
    private final HashMap<User, Balance> userBalance;
    private final HashMap<User, HashMap<User, List<Expense>>> history;
    public AccountService(){
        userBalance = new HashMap<>();
        history = new HashMap<>();
    }
    public void addUser(User user){
        userBalance.put(user, new Balance(0,0));
        history.put(user, new HashMap<>());
        System.out.println("Added user successfully..");
    }

    public void debitAmount(User user, double amount) {
        if(!userBalance.containsKey(user)){
            userBalance.put(user, new Balance(0,0));
        }
        Balance balance = userBalance.get(user);
        balance.setDebit(balance.getDebit() + amount);
    }

    public void creditAmount(User user, double amount) {
        if(!userBalance.containsKey(user)){
            userBalance.put(user, new Balance(0,0));
        }
        Balance balance = userBalance.get(user);
        balance.setCredit(balance.getCredit() + amount);
    }

    public void maintainTransactionRecord(User paidBy, User paidTo, int groupId, double amount){
        if(!history.containsKey(paidBy)){
            history.put(paidBy,new HashMap<>());
        }
        HashMap<User, List<Expense>> userRecords = history.get(paidBy);
        if(!userRecords.containsKey(paidTo)){
            userRecords.put(paidTo, new ArrayList<>());
        }
        List<Expense> expenses = userRecords.get(paidTo);
        expenses.add(new Expense(amount, groupId));
    }

    public void showHistoryOfUser(User user) {
        if(!history.containsKey(user)){
            System.out.println("No history found..");
            return;
        }
        HashMap<User, List<Expense>> userRecords = history.get(user);
        for(User contributedUser: userRecords.keySet()){
            double total = 0;
            List<Expense> expenses = userRecords.get(contributedUser);
            for(int i=expenses.size()-1; i>=0; i--){
                System.out.println(expenses.get(i));
                total+=expenses.get(i).getAmount();
            }
            if(total > 0){
                System.out.println(contributedUser+" Owns "+user+" "+ total+ " amount");
            }else{
                System.out.println(user+" owns "+contributedUser + (-total) +" amount");
            }
        }
    }

    public void showAccountInformation(User user){
        if(!userBalance.containsKey(user)){
            System.out.println("User don't exits..");
            return;
        }
        System.out.println(userBalance.get(user));
        System.out.println("-------------------------------");
    }

    public void showHistory(User user){
        showAccountInformation(user);
        showHistoryOfUser(user);
        System.out.println("------------------------------------------\n\n");
    }
}
