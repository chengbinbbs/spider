package com.dayspass.datacenter.domain;

import java.io.Serializable;
import java.util.Date;

public class FootBallSp implements Serializable {
    private Integer ID;

    private Integer footBallMatchId;

    private String matchCode;

    private Integer issue;

    private String teamId;

    private Integer spType;

    private Double f01;

    private Double f02;

    private Double f03;

    private Double f04;

    private Double f05;

    private Double f12;

    private Double f13;

    private Double f14;

    private Double f15;

    private Double f23;

    private Double f24;

    private Double f25;

    private Double fother;

    private Double p00;

    private Double p11;

    private Double p22;

    private Double p33;

    private Double pother;

    private Double s10;

    private Double s20;

    private Double s30;

    private Double s40;

    private Double s50;

    private Double s21;

    private Double s31;

    private Double s41;

    private Double s51;

    private Double s32;

    private Double s42;

    private Double s52;

    private Double sother;

    private Double ff;

    private Double fp;

    private Double fs;

    private Double pf;

    private Double pp;

    private Double ps;

    private Double sf;

    private Double sp;

    private Double ss;

    private Double sheng;

    private Double ping;

    private Double fu;

    private Double rqSheng;

    private Double rqPing;

    private Double rqFu;

    private Double t0;

    private Double t1;

    private Double t2;

    private Double t3;

    private Double t4;

    private Double t5;

    private Double t6;

    private Double t7;

    private Byte status;

    private Date createTime;

    private Date updateTime;
    
    private Double rate;
    
    public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	private static final long serialVersionUID = 1L;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getFootBallMatchId() {
        return footBallMatchId;
    }

    public void setFootBallMatchId(Integer footBallMatchId) {
        this.footBallMatchId = footBallMatchId;
    }

    public String getMatchCode() {
        return matchCode;
    }

    public void setMatchCode(String matchCode) {
        this.matchCode = matchCode == null ? null : matchCode.trim();
    }

    public Integer getIssue() {
        return issue;
    }

    public void setIssue(Integer issue) {
        this.issue = issue;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId == null ? null : teamId.trim();
    }

    public Integer getSpType() {
        return spType;
    }

    public void setSpType(Integer spType) {
        this.spType = spType;
    }

    public Double getF01() {
        return f01;
    }

    public void setF01(Double f01) {
        this.f01 = f01;
    }

    public Double getF02() {
        return f02;
    }

    public void setF02(Double f02) {
        this.f02 = f02;
    }

    public Double getF03() {
        return f03;
    }

    public void setF03(Double f03) {
        this.f03 = f03;
    }

    public Double getF04() {
        return f04;
    }

    public void setF04(Double f04) {
        this.f04 = f04;
    }

    public Double getF05() {
        return f05;
    }

    public void setF05(Double f05) {
        this.f05 = f05;
    }

    public Double getF12() {
        return f12;
    }

    public void setF12(Double f12) {
        this.f12 = f12;
    }

    public Double getF13() {
        return f13;
    }

    public void setF13(Double f13) {
        this.f13 = f13;
    }

    public Double getF14() {
        return f14;
    }

    public void setF14(Double f14) {
        this.f14 = f14;
    }

    public Double getF15() {
        return f15;
    }

    public void setF15(Double f15) {
        this.f15 = f15;
    }

    public Double getF23() {
        return f23;
    }

    public void setF23(Double f23) {
        this.f23 = f23;
    }

    public Double getF24() {
        return f24;
    }

    public void setF24(Double f24) {
        this.f24 = f24;
    }

    public Double getF25() {
        return f25;
    }

    public void setF25(Double f25) {
        this.f25 = f25;
    }

    public Double getFother() {
        return fother;
    }

    public void setFother(Double fother) {
        this.fother = fother;
    }

    public Double getP00() {
        return p00;
    }

    public void setP00(Double p00) {
        this.p00 = p00;
    }

    public Double getP11() {
        return p11;
    }

    public void setP11(Double p11) {
        this.p11 = p11;
    }

    public Double getP22() {
        return p22;
    }

    public void setP22(Double p22) {
        this.p22 = p22;
    }

    public Double getP33() {
        return p33;
    }

    public void setP33(Double p33) {
        this.p33 = p33;
    }

    public Double getPother() {
        return pother;
    }

    public void setPother(Double pother) {
        this.pother = pother;
    }

    public Double getS10() {
        return s10;
    }

    public void setS10(Double s10) {
        this.s10 = s10;
    }

    public Double getS20() {
        return s20;
    }

    public void setS20(Double s20) {
        this.s20 = s20;
    }

    public Double getS30() {
        return s30;
    }

    public void setS30(Double s30) {
        this.s30 = s30;
    }

    public Double getS40() {
        return s40;
    }

    public void setS40(Double s40) {
        this.s40 = s40;
    }

    public Double getS50() {
        return s50;
    }

    public void setS50(Double s50) {
        this.s50 = s50;
    }

    public Double getS21() {
        return s21;
    }

    public void setS21(Double s21) {
        this.s21 = s21;
    }

    public Double getS31() {
        return s31;
    }

    public void setS31(Double s31) {
        this.s31 = s31;
    }

    public Double getS41() {
        return s41;
    }

    public void setS41(Double s41) {
        this.s41 = s41;
    }

    public Double getS51() {
        return s51;
    }

    public void setS51(Double s51) {
        this.s51 = s51;
    }

    public Double getS32() {
        return s32;
    }

    public void setS32(Double s32) {
        this.s32 = s32;
    }

    public Double getS42() {
        return s42;
    }

    public void setS42(Double s42) {
        this.s42 = s42;
    }

    public Double getS52() {
        return s52;
    }

    public void setS52(Double s52) {
        this.s52 = s52;
    }

    public Double getSother() {
        return sother;
    }

    public void setSother(Double sother) {
        this.sother = sother;
    }

    public Double getFf() {
        return ff;
    }

    public void setFf(Double ff) {
        this.ff = ff;
    }

    public Double getFp() {
        return fp;
    }

    public void setFp(Double fp) {
        this.fp = fp;
    }

    public Double getFs() {
        return fs;
    }

    public void setFs(Double fs) {
        this.fs = fs;
    }

    public Double getPf() {
        return pf;
    }

    public void setPf(Double pf) {
        this.pf = pf;
    }

    public Double getPp() {
        return pp;
    }

    public void setPp(Double pp) {
        this.pp = pp;
    }

    public Double getPs() {
        return ps;
    }

    public void setPs(Double ps) {
        this.ps = ps;
    }

    public Double getSf() {
        return sf;
    }

    public void setSf(Double sf) {
        this.sf = sf;
    }

    public Double getSp() {
        return sp;
    }

    public void setSp(Double sp) {
        this.sp = sp;
    }

    public Double getSs() {
        return ss;
    }

    public void setSs(Double ss) {
        this.ss = ss;
    }

    public Double getSheng() {
        return sheng;
    }

    public void setSheng(Double sheng) {
        this.sheng = sheng;
    }

    public Double getPing() {
        return ping;
    }

    public void setPing(Double ping) {
        this.ping = ping;
    }

    public Double getFu() {
        return fu;
    }

    public void setFu(Double fu) {
        this.fu = fu;
    }

    public Double getRqSheng() {
        return rqSheng;
    }

    public void setRqSheng(Double rqSheng) {
        this.rqSheng = rqSheng;
    }

    public Double getRqPing() {
        return rqPing;
    }

    public void setRqPing(Double rqPing) {
        this.rqPing = rqPing;
    }

    public Double getRqFu() {
        return rqFu;
    }

    public void setRqFu(Double rqFu) {
        this.rqFu = rqFu;
    }

    public Double getT0() {
        return t0;
    }

    public void setT0(Double t0) {
        this.t0 = t0;
    }

    public Double getT1() {
        return t1;
    }

    public void setT1(Double t1) {
        this.t1 = t1;
    }

    public Double getT2() {
        return t2;
    }

    public void setT2(Double t2) {
        this.t2 = t2;
    }

    public Double getT3() {
        return t3;
    }

    public void setT3(Double t3) {
        this.t3 = t3;
    }

    public Double getT4() {
        return t4;
    }

    public void setT4(Double t4) {
        this.t4 = t4;
    }

    public Double getT5() {
        return t5;
    }

    public void setT5(Double t5) {
        this.t5 = t5;
    }

    public Double getT6() {
        return t6;
    }

    public void setT6(Double t6) {
        this.t6 = t6;
    }

    public Double getT7() {
        return t7;
    }

    public void setT7(Double t7) {
        this.t7 = t7;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}