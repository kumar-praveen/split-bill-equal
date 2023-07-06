package org.example.splitbill;

import org.example.splitbill.entity.Group;
import org.example.splitbill.entity.User;
import org.example.splitbill.service.AccountService;
import org.example.splitbill.service.SplitService;

import java.util.List;

public class SplitBillApplication {
    public static void main(String[] args) {
        System.out.println("Starting split Bill Application..");
        User u1 = new User(1,"Praveen",7680976L);
        User u2 = new User(2,"Vinay",78389392L);
        User u3 = new User(3,"Pratham",4893895L);
        User u4 = new User(4,"Vishal",843092L);
        User u5 = new User(5,"Yash", 38390509L);


        AccountService accountService = new AccountService();
        accountService.addUser(u1);
        accountService.showHistory(u1);

        SplitService splitService = new SplitService(accountService);

        List<User> g1 = List.of(u1,u2,u3,u4);
        splitService.splitAmount(new Group(1,"Meta",g1,100, u1));

        accountService.showHistory(u1);
        accountService.showHistory(u2);

        List<User> g2 = List.of(u1,u3,u5);
        splitService.splitAmount(new Group(2,"Apple",g2,90, u3));

        accountService.showHistory(u1);
        accountService.showHistory(u3);
        accountService.showHistory(u5);

        List<User> g3 = List.of(u1,u2,u4,u5);
        splitService.splitAmount(new Group(3,"Tesla",g3,200, u1));

        accountService.showHistory(u1);
        accountService.showHistory(u2);
        accountService.showHistory(u3);
        accountService.showHistory(u4);
        accountService.showHistory(u5);

    }
}