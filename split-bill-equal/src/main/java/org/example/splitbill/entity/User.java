package org.example.splitbill.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class User {
    private Integer id;
    private String name;
    private Long mobile;

    @Override
    public String toString(){
        return name+" ";
    }
}
