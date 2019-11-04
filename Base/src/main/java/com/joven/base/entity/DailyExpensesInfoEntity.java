package com.joven.base.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DailyExpensesInfoEntity implements Serializable {
    private Integer id;
    private String name;
    private String value;
    private String currency;
    private String paymentMethod;
    private String reason;
    private Date eff_time;
    private Date inputTime;
    private String haveCertificate;
    private String isreturn;
    private String sub_id;
    private String informationSources;
    private String remark1;
    private String remark2;

    public Date getEff_time() {
        return eff_time;
    }

    public void setEff_time(Date eff_time) {
        this.eff_time = eff_time;
    }

    public Date getInputTime() {
        return inputTime;
    }

    public void setInputTime(Date inputTime) {
        this.inputTime = inputTime;
    }

    public String getHaveCertificate() {
        return haveCertificate;
    }

    public void setHaveCertificate(String haveCertificate) {
        this.haveCertificate = haveCertificate;
    }

    public String getIsreturn() {
        return isreturn;
    }

    public void setIsreturn(String isreturn) {
        this.isreturn = isreturn;
    }

    public String getSub_id() {
        return sub_id;
    }

    public void setSub_id(String sub_id) {
        this.sub_id = sub_id;
    }

    public String getInformationSources() {
        return informationSources;
    }

    public void setInformationSources(String informationSources) {
        this.informationSources = informationSources;
    }

    public String getRemark1() {
        return remark1;
    }

    public void setRemark1(String remark1) {
        this.remark1 = remark1;
    }

    public String getRemark2() {
        return remark2;
    }

    public void setRemark2(String remark2) {
        this.remark2 = remark2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DailyExpensesInfoEntity that = (DailyExpensesInfoEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(value, that.value) &&
                Objects.equals(currency, that.currency) &&
                Objects.equals(paymentMethod, that.paymentMethod) &&
                Objects.equals(reason, that.reason);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, value, currency, paymentMethod, reason);
    }

    @Override
    public String toString() {
        return "DailyExpensesInfoEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", currency='" + currency + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", reason='" + reason + '\'' +
                '}';
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
