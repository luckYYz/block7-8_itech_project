package de.cook2gether.backend.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int receiptId;
    private String difficulty;
    private String duration;
    private String title;
    private String description;
    private String ingredients;
    private long saves;
}
