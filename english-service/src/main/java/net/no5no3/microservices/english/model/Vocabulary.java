package net.no5no3.microservices.english.model;

import java.io.Serializable;
import java.util.List;

public class Vocabulary implements Serializable{

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String en;
    private String cn;
    private String classify;
    private List<String> ens;
    private List<String> cns;
    private List<String> subs;

    public Vocabulary() {
    }

    public List<String> getEns() {
        return ens;
    }

    public void setEns(List<String> ens) {
        this.ens = ens;
    }

    public List<String> getCns() {
        return cns;
    }

    public void setCns(List<String> cns) {
        this.cns = cns;
    }

    public List<String> getSubs() {
        return subs;
    }

    public void setSubs(List<String> subs) {
        this.subs = subs;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }

    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }
}
