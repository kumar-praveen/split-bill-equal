package org.example.splitbill.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Balance {
    private double debit;
    private double credit;

    @Override
    public String toString(){
        return "["+"net: "+(credit-debit)+", debit: "+debit+" credit: "+credit+"]";
    }
}
