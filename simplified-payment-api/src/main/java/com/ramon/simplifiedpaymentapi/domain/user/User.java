package com.ramon.simplifiedpaymentapi.domain.user;

import com.ramon.simplifiedpaymentapi.dtos.UserDTO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity(name = "users")
@Table(name = "tb_users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String document;

    @Column(unique = true)
    private String email;

    private String password;

    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    public  User(UserDTO userDTO) {
        this.firstName = userDTO.firsName();
        this.lastName = userDTO.lastName();
        this.document = userDTO.document();
        this.email = userDTO.email();
        this.password = userDTO.password();
        this.balance = userDTO.balance();
        this.userType = userDTO.userType();
    }

}
