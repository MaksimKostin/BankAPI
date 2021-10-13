package com.example.bankapi.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.List;

@Entity
@NoArgsConstructor
@Setter
@Getter
@Table(name = "ACCOUNT")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "number")
    private String number;
    @Column(name = "balance")
    private float balance;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="client_id")
    private Client client;
    @JsonManagedReference
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch= FetchType.EAGER)
    private List<Card> cards;

}