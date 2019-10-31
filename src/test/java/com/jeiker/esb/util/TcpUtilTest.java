package com.jeiker.esb.util;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Map;

/**
 * Description: spring-boot-esb
 * User: jeikerxiao
 * Date: 2019-10-31 09:18
 */
@Slf4j
class TcpUtilTest {


    @Test
    void sendTCPRequest() {
        String sendStr2 = "00000454<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<SERVICE><SYS_HEAD><SERVICE_CODE>60012000004</SERVICE_CODE><SERVICE_SCENE>03</SERVICE_SCENE><ORIGIN_SYS_ID></ORIGIN_SYS_ID><SYS_ID></SYS_ID><INF_TYPE></INF_TYPE><TRAN_MODE></TRAN_MODE><USER_ID></USER_ID><TRAN_CODE></TRAN_CODE><PRD_CODE></PRD_CODE><GLOB_SEQ_NUM>CMS01201907190024210907</GLOB_SEQ_NUM><SYS_SEQ_NUM></SYS_SEQ_NUM><INF_SEQ_NUM></INF_SEQ_NUM><BRANCH_ID></BRANCH_ID><TELLER_ID></TELLER_ID><TRAN_DATE>20190719</TRAN_DATE><TRAN_TIME>101604</TRAN_TIME><BATCH_ID></BATCH_ID><RET_CODE></RET_CODE><RET_MSG></RET_MSG><BACK_DATE></BACK_DATE><BACK_TIME></BACK_TIME><BACK_SEQ_NUM></BACK_SEQ_NUM><ACCT_DATE></ACCT_DATE><VER></VER></SYS_HEAD><APP_HEAD/><BODY><TEMP_CODE>DX0001</TEMP_CODE><TELE_NO>18664865196</TELE_NO><CONSUMER_ID>093</CONSUMER_ID></BODY></SERVICE>";
        String sendStr = "00000454<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><SERVICE><SYS_HEAD><GLOB_SEQ_NUM>CMS01201907220024210907</GLOB_SEQ_NUM><SERVICE_CODE>60012000004</SERVICE_CODE><SERVICE_SCENE>01</SERVICE_SCENE></SYS_HEAD><APP_HEAD/><BODY><CONSUMER_ID>093</CONSUMER_ID><SMS_ARRAY><TELE_NO>18664965196</TELE_NO></SMS_ARRAY><SMS_MSG>消息内容</SMS_MSG></BODY></SERVICE>";
        String IP = "10.7.4.198";
        String port = "9031";
        String reqCharset = "UTF-8";
        Map<String, String> respMap = TcpUtil.sendTCPRequest(IP, port, sendStr2, reqCharset);
        System.out.println("=============================================================================");
        System.out.println("请求报文如下");
        System.out.println(respMap.get("reqData"));
        String requestStr = respMap.get("reqData");
        String reqXmlData = StrUtil.sub(requestStr, 8, requestStr.length());
        String reqFormatXml = XmlUtil.format(reqXmlData);
        log.info("输出ESB请求:\n{}", reqFormatXml);
        System.out.println("=============================================================================");
        System.out.println("响应报文如下");
        System.out.println(respMap.get("respData"));
        String responseStr = respMap.get("respData");
        String respXmlData = StrUtil.sub(responseStr, 8, responseStr.length());
        String respFormatXml = XmlUtil.format(respXmlData);
        log.info("输出ESB响应:\n{}", respFormatXml);
        System.out.println("=============================================================================");
    }
}