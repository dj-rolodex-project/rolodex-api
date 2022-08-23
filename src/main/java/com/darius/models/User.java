package com.darius.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Entity
@Table(name="users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class User {

    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Length(min=2)
    private String firstName;
    private String lastName;

    @NotBlank
    @Length(min=2)
    @Pattern(regexp="[a-zA-Z][a-zA-Z0-9]*")
    @Column(unique=true)
    private String username;

    @NotBlank
    @Column(name="pwd")
    private String password;

    @Email
    @Column(unique=true)
    private String email;

    // userId 1 -- maps to address Id 4
    // userId 2 -- maps to address ID 4
    // userId 1 maps to address id 6
    @ManyToMany
    @JoinTable(name="users_address",
            joinColumns=@JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="address_id"))
    private Set<Address> addresses;

    /**
     * Regarding the validation constraints on some of the fields
     *
     * @NotNull: a constrained CharSequence, Collection, Map, or Array is valid as
     *           long as it's not null, but it can be empty.
     * @NotEmpty: a constrained CharSequence, Collection, Map, or Array is valid as
     *            long as it's not null, and its size/length is greater than zero.
     * @NotBlank: a constrained String is valid as long as it's not null, and the
     *            trimmed length is greater than zero.
     */

}

