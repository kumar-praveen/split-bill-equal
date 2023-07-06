package org.example.splitbill.service;

import org.example.splitbill.entity.Group;
import org.example.splitbill.entity.User;

public class SplitService {
    private final AccountService accountService;
    public SplitService(AccountService accountService){
        this.accountService = accountService;
    }

    public void splitAmount(Group group){
        User paidBy = group.getPaidBy();
        double amount = group.getAmount();

        if(!isValidateGroup(group, paidBy)){
            return;
        }
        double amountPerHead = amount/group.getGroupMember().size();
        for(User user: group.getGroupMember()){
            if(user.equals(paidBy)){
                accountService.debitAmount(paidBy, amount);
                continue;
            }
            accountService.creditAmount(user, amountPerHead);
            accountService.maintainTransactionRecord(paidBy,user, group.getId(), amountPerHead);
            accountService.maintainTransactionRecord(user, paidBy, group.getId(), -amountPerHead);
        }
    }

    public boolean isValidateGroup(Group group, User paidBy){
        if(group.getGroupMember().size() < 2){
            System.out.println("Add 2 or more members");
            return false;
        }
        if(!group.getGroupMember().contains(paidBy)){
            System.out.println("Paying member not present in group...");
            return false;
        }
        return true;
    }
}
