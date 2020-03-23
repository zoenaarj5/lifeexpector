package org.kavus.lifeexpectorlib.ntt;

import javax.persistence.*;
import java.time.Period;
import java.util.Set;
@Entity
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;
    @Column(name="CODE",unique = true)
    @Enumerated(EnumType.STRING)
    protected CountryCode code;
    @OneToMany(targetEntity = CountryLifeExpectancy.class,mappedBy = "country",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    protected Set<CountryLifeExpectancy> lifeExpectancySet;
    protected String name;

    public Country() {
        super();
    }

    public Country(CountryCode code, String name) {
        this.code = code;
        this.name = name;
    }

    public Country(CountryCode code, Set<CountryLifeExpectancy> lifeExpectancySet, String name) {
        this.code = code;
        this.lifeExpectancySet = lifeExpectancySet;
        this.name = name;
    }
    public CountryLifeExpectancy getLifeExpectancyBySex(Sex sex) throws ClassCastException{
        if(lifeExpectancySet==null){
            return null;
        }
        Object[] lebs=lifeExpectancySet.stream().filter(le->le.getSex().equals(sex)).toArray();
        if(lebs.length>0){
            return (CountryLifeExpectancy)lebs[0];
        }
        return null;
    }
    public Set<CountryLifeExpectancy> getLifeExpectancySet() {
        return lifeExpectancySet;
    }

    public void setLifeExpectancySet(Set<CountryLifeExpectancy> lifeExpectancySet) {
        this.lifeExpectancySet = lifeExpectancySet;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CountryCode getCode() {
        return code;
    }

    public void setCode(CountryCode code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString(){
        StringBuilder sb=new StringBuilder();
        sb.append("\nPRINTING COUNTRY");
        sb.append("\nID:\t"+this.id);
        sb.append("\nCODE:\t"+this.code);
        sb.append("\nNAME:\t"+this.name);
        sb.append("\nLIFE EXPECTANCIES:\n");
        if(lifeExpectancySet==null){
            sb.append("No life expectancies available");
        }else{
            for(CountryLifeExpectancy cle:this.lifeExpectancySet) {
                Period lfx=cle.getValue();
                sb.append("\n"+cle.getSex()+":\t" + (lfx==null?"Life expectancy not available":(lfx.getYears()+" years "+lfx.getMonths()+" months "+lfx.getDays()+" days"))+"\n");
            }
        }
        sb.append("\n");
        return sb.toString();
    }
}
