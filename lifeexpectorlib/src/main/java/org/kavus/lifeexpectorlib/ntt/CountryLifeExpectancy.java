package org.kavus.lifeexpectorlib.ntt;

import javax.persistence.*;
import java.time.Period;
@Entity
public class CountryLifeExpectancy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    @ManyToOne
    @JoinColumn(name="COUNTRY_ID")
    protected Country country;
    @Enumerated(EnumType.STRING)
    protected Sex sex;
    protected Period value;
    public CountryLifeExpectancy(){
        super();
    }

    public CountryLifeExpectancy(Country country, Sex sex, Period value) {
        this.country = country;
        this.sex = sex;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Period getValue() {
        return value;
    }

    public void setValue(Period value) {
        this.value = value;
    }
    @Override
    public String toString(){
        StringBuilder sb=new StringBuilder();
        sb.append("\nPRINTING COUNTRY LIFE EXPECTANCY");
        sb.append("\nID:\t"+this.id);
        sb.append("\nCOUNTRY CODE:\t"+this.country==null?"No country":this.country.getCode());
        sb.append("\nSEX:\t"+this.sex+"\n");
        sb.append("\nVALUE:\t"+this.value+"\n");
        sb.append("\n");
        return sb.toString();
    }
}
