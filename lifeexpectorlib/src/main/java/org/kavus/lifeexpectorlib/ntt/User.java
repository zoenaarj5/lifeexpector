package org.kavus.lifeexpectorlib.ntt;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    @OneToOne
    @JoinColumn(name="LOGIN_DATA_ID")
    protected LoginData loginData;
    protected String firstName;
    protected String lastName;
    @Column(name="BIRTH_DATE")
    protected LocalDate birthDate;
    @Enumerated(EnumType.STRING)
    protected Sex sex;
    @ManyToOne
    @JoinColumn(name="COUNTRY_ID")
    protected Country nationality;
    @Transient
    protected Period expectedLifeLeft;

    public User(LoginData loginData, String firstName, String lastName, LocalDate birthDate, Sex sex, Country nationality, Period expectedLifeLeft) {
        this.loginData = loginData;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.sex = sex;
        this.nationality = nationality;
        this.expectedLifeLeft = expectedLifeLeft;
    }

    public User(String firstName, String lastName, LocalDate birthDate, Sex sex, Country nationality) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.sex = sex;
        this.nationality = nationality;
        updateExpectedLifeLeft();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LoginData getLoginData() {
        return loginData;
    }

    public void setLoginData(LoginData loginData) {
        this.loginData = loginData;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Country getNationality() {
        return nationality;
    }

    public void setNationality(Country nationality) {
        this.nationality = nationality;
        updateExpectedLifeLeft();
    }

    public User() {
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        updateExpectedLifeLeft();
    }

    public Period getExpectedLifeLeft() {
        return expectedLifeLeft;
    }

    public void setExpectedLifeLeft(Period expectedLifeLeft) {
        this.expectedLifeLeft = expectedLifeLeft;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
        updateExpectedLifeLeft();
    }
    public void updateExpectedLifeLeft(){
        if(nationality==null || nationality.getLifeExpectancySet()==null || birthDate==null){
            expectedLifeLeft=null;
        }else {
            Object[] oCLEs = nationality.getLifeExpectancySet().stream().filter(le -> le.getSex().equals(getSex())).toArray();
            if (oCLEs.length == 0) {
                expectedLifeLeft = null;
            }
            CountryLifeExpectancy cle=nationality.getLifeExpectancyBySex(getSex());
            if(cle!=null){
                Period lex=cle.getValue();
                if(lex!=null){
                    expectedLifeLeft = Period.between(LocalDate.now(), birthDate.plus(lex));
                }
            }
        }
    }
    @Override
    public String toString(){
        StringBuilder sb=new StringBuilder();
        sb.append("\nPRINTING USER");
        sb.append("\nID:\t"+this.id);
        sb.append("\nFIRST NAME:\t"+this.firstName);
        sb.append("\nFIRST NAME:\t"+this.firstName);
        sb.append("\nLAST NAME:\t"+this.lastName);
        sb.append("\nLAST NAME:\t"+this.sex);
        sb.append("\nBIRTH DATE:\t"+this.birthDate);
        sb.append("\nNATIONALITY:\t"+(this.nationality==null?"None" : (this.nationality.getName()+" ["+this.nationality.getCode()+"]")));
        sb.append("\nEXPECTED LIFE LEFT:\t"+(this.expectedLifeLeft==null?"No indication available":(this.expectedLifeLeft.getYears()+" years "+this.expectedLifeLeft.getMonths()+" months "+this.expectedLifeLeft.getDays()+" days")));
        sb.append("\n");
        return sb.toString();
    }
}
