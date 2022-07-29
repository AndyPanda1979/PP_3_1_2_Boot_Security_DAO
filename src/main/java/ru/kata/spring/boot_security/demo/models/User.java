package ru.kata.spring.boot_security.demo.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {


    @Id
    @Column (name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column (name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column (name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Column (name = "password", nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )


    private List<Role> roles = new ArrayList<>();
    // roles
    public void addRole(Role role) {this.roles.add(role);}

    public void setRoles(List<Role> roles) {this.roles = roles;}
    public List<Role> getRoles() {return roles;}


    // password
    public void setPassword(String password) {this.password = password;}
    public String getPassword() {return password;}
    // id
    public void setId(long id) {this.id = id;}
    public long getId() {return id;}
    // firstName
    public void setFirstName(String firstName) {this.firstName = firstName;}
    public String getFirstName() {return firstName;}
    // lastName
    public void setLastName(String lastName) {this.lastName = lastName;}
    public String getLastName() {return lastName;}

    // конструкторы
    public User () {}

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }


}
