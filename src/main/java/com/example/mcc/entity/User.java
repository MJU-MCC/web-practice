package com.example.mcc.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
public class User {

    @Id @GeneratedValue
    Long id;

    String userid;
    String password;
    String name;
    String phoneNumber;
}
