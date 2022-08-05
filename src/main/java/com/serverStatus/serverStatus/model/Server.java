package com.serverStatus.serverStatus.model;

import com.serverStatus.serverStatus.model.enumeration.Status;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Server {

    @Id @GeneratedValue(strategy = AUTO)
    private Long id;
    private String name;
    @Column(unique = true)
    @NotEmpty(message = "Ip adress não pode estar vazio ou nulo")
    private String ipAddress;
    private String memory;
    private String type;
    private Status status;

}
