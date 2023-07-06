package org.example.splitbill.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class Expense {
    private double amount;
    private Integer groupId;
}
