package org.kavus.lifeexpectorlib.ntt;

import javax.persistence.*;
@Entity
public class LoginData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;
    @Column(unique = true)
    protected String login;
    @Column(unique = true)
    protected String email;
    protected String password;
    @OneToOne(targetEntity = User.class,mappedBy = "loginData")
    protected User user;
}
