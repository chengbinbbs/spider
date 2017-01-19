package com.dayspass.datacenter.contants;

public class FootBallContants {

	/**
     * 足球胜平负玩法代号
     */
    public final static int TYPE_SPF = 0;
    /**
     * 足球总进球玩法代号
     */
    public final static int TYPE_ZJQ = 1;
    /**
     * 足球比分玩法代号
     */
    public final static int TYPE_BF = 2;
    /**
     * 足球半全场玩法代号
     */
    public final static int TYPE_BQC = 3;
    /**
     * 足球混合投注玩法代号
     */
    public final static int TYPE_HHTZ = 4;
    /**
     * 足球胜平负玩法代号
     */
    public final static int TYPE_RQSPF = 5;
    /**
     * 2选1
     */
    public final static int TYPE_2X1 = 6;

    /**
     * 恒朋体彩玩法代号
     */
    public final static String FOOTBALL_CODE_SPF ="FT001";
    public final static String FOOTBALL_CODE_BF ="FT002";
    public final static String FOOTBALL_CODE_ZJQ ="FT003";
    public final static String FOOTBALL_CODE_BQC ="FT004";
    public final static String FOOTBALL_CODE_HHTZ ="FT005";
    public final static String FOOTBALL_CODE_RQSPF ="FT006";
    /**
     * 足球胜平负玩法代号
     */
    public final static String ZZY_FOOTBALL_CODE_SPF = "D51";
    /**
     * 足球比分玩法代号
     */
    public final static String ZZY_FOOTBALL_CODE_BF = "D52";
    /**
     * 足球总进球玩法代号
     */
    public final static String ZZY_FOOTBALL_CODE_ZJQ = "D53";
    /**
     * 足球半全场玩法代号
     */
    public final static String ZZY_FOOTBALL_CODE_BQC = "D54";
     /**
     * 足球半全场玩法代号
     */
    public final static String ZZY_FOOTBALL_CODE_GJ = "D91";
     /**
     * 足球半全场玩法代号
     */
    public final static String ZZY_FOOTBALL_CODE_GYJ = "D92";
    /**
     * 足球玩法代号数组
     */
    public final static String[] ZZY_FOOTBALL_CODE_ARR = {ZZY_FOOTBALL_CODE_SPF, ZZY_FOOTBALL_CODE_BF,
            ZZY_FOOTBALL_CODE_ZJQ, ZZY_FOOTBALL_CODE_BQC};


    /**
     * 足球单关
     */
    public final static int SINGLE_TYPE = 1;

    /**
     * 足球过关
     */
    public final static int PASS_TYPE = 0;


    /**
     * 足球投注状态-销售中
     */
    public final static int BET_STATUS_SALES = 0;

    /**
     * 足球投注状态-停止销售
     */
    public final static int BET_STATUS_STOP = 1;
    /**
     * 足球投注状态-已开奖，奖金处理中
     */
    public final static int BET_STATUS_OVER = 2;
    /**
     * 足球投注状态-奖金处理完毕
     */
    public final static int BET_STATUS_PRIZE = 3;
    /**
     * 足球投注状态-取消
     */
    public final static int BET_STATUS_CANCEL = 4;
    /**
     * 未开玩法
      */
    public final static int BET_STATUS_NOT_SELL = 5;

    /**
     * 平台赛事状态-未有开奖结果
     */
    public final static int STATUS_DEFAULT = 0;

    /**
     * 平台赛事状态-已有开奖结果
     */
    public final static int STATUS_OVER = 1;
    /**
     * 平台赛事状态-已派奖
     */
    public final static int STATUS_PRIZE = 2;

    /**
     * 销售提前截止时间 （相对掌中亦接口） 单位(分钟)                              d
     */
    public final static int SELL_ADVANCE_TIME_TO_ZZY = 20;  //我中了提起15分钟
    /**
     * 销售提前截止时间 （掌中亦接口） 单位(分钟)
     */
    public final static int SELL_ADVANCE_TIME_ZZY = 15;


     /**
     * 合买方案最后处理时间   单位(分钟)
     */
    public final static int LAST_PROCESS__TIME = SELL_ADVANCE_TIME_ZZY+SELL_ADVANCE_TIME_TO_ZZY;

    /**
     * 开售
     */
    //public final static Integer SALES = 1;
    /**
     * 未开售
     */
    //public final static Integer NOT_SALES = 0;
    
    /**
     * 足球胜平负玩法
     */
    public final static String TYPE_SPF_STR = "SPF";
    /**
     * 足球总进球玩法
     */
    public final static String TYPE_ZJQ_STR = "JQS";
    /**
     * 足球比分玩法
     */
    public final static String TYPE_CBF_STR = "CBF";
    /**
     * 足球半全场玩法
     */
    public final static String TYPE_BQC_STR = "BQC";
    /**
     * 足球胜平负玩法
     */
    public final static String TYPE_RQSPF_STR = "RQSPF";

    /**
     * 足球混合玩法
     */
    public final static String TYPE_HHTZ_STR = "HH";
}
