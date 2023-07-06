package org.example.splitbill.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Group {
    private int id;
    private String groupName;
    private List<User> groupMember;
    private double amount;
    private User paidBy;
}
