package com.dayspass.datacenter.domain;

import java.io.Serializable;

public class LqSclassWithBLOBs extends LqSclass implements Serializable {
    private String historywinner;

    private String rules;

    private static final long serialVersionUID = 1L;

    public String getHistorywinner() {
        return historywinner;
    }

    public void setHistorywinner(String historywinner) {
        this.historywinner = historywinner == null ? null : historywinner.trim();
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules == null ? null : rules.trim();
    }
}