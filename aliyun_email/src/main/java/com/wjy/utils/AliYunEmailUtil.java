package com.wjy.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dm.model.v20151123.SingleSendMailRequest;
import com.aliyuncs.dm.model.v20151123.SingleSendMailResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;


/**
 * @author wangjingyang
 * @date 2021-02-01 14:27
 */
public class AliYunEmailUtil {
    /**
     * 区域id
     */
    private final static String REGION_ID = "cn-hangzhou";
    private final static String ACCESS_KEY_ID = "LTAI4GFtzSUXrBPeCsdniZg5";
    private final static String SECRET = "uAw7aQYDrPfRvlJxRtCRoTxDeDSvvO";
    /**
     * 控制台创建的发信地址
     */
    private final static String SEND_ADDRESS = "service@thunderex.org";

    /**
     * 发信人昵称
     */
    private final static String FROM_ALIAS = "王静洋";

    /**
     * 控制台创建的标签
     */
    private final static String TAG_NAME = "";

    private final static String BEFORE_HTML = "<h3>尊敬的Thunder用户：</h3><p style=\"text-indent: 2em;\">您好，</p ><p style=\"text-indent: 2em;\">验证码：<span style=\"color: blue;\">";

    private final static String SUFFIX_HTML = "</span></p ><p style=\"text-indent: 2em;\">若非您本人操作，并且有帐号与此电子邮件地址相关联，则可能是其他人在尝试访问您的帐号，请您及时修改密码，确保您的帐号安全无误。</p ><p style=\"text-align: right;\">此致</p ><p style=\"text-align: right;\">Thunder敬上</p >";
    /**
     * 发送邮件方法
     * @param toAddress 目标地址
     * @param subject 邮件主题
     * @param htmlBody 邮件正文
     */
    public static void sample(String toAddress,String subject,String htmlBody ) {
        IClientProfile profile = DefaultProfile.getProfile("ap-southeast-1", "LTAI4GFtzSUXrBPeCsdniZg5", "uAw7aQYDrPfRvlJxRtCRoTxDeDSvvO");
        // 如果是除杭州region外的其它region（如新加坡region）， 需要做如下处理
        try {
        DefaultProfile.addEndpoint("dm.ap-southeast-1.aliyuncs.com", "ap-southeast-1", "Dm",  "dm.ap-southeast-1.aliyuncs.com");
        } catch (ClientException e) {
        e.printStackTrace();
        }
        IAcsClient client = new DefaultAcsClient(profile);
        SingleSendMailRequest request = new SingleSendMailRequest();
        try {
            //request.setVersion("2017-06-22");// 如果是除杭州region外的其它region（如新加坡region）,必须指定为2017-06-22
            request.setAccountName("vc@thunderex.org");
            request.setFromAlias("Thunder");
            request.setAddressType(1);
            request.setTagName("Varify");
            request.setReplyToAddress(true);
            request.setToAddress(toAddress);
            request.setSubject(subject);
            request.setHtmlBody(htmlBody);
            SingleSendMailResponse httpResponse = client.getAcsResponse(request);
        } catch (ServerException e) {
            e.printStackTrace();
        }
        catch (ClientException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送邮件方法
     * @param toAddress 目标地址
     * @param subject 邮件主题
     * @param htmlBody 邮件正文
     */
    public static void sampleV2(String toAddress,String subject,String htmlBody ) {
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4FyVni632yZbouNQiito", "H1TzyWMM1UNslCuMpr5aJJFJvqdUBr");
        // 如果是除杭州region外的其它region（如新加坡region）， 需要做如下处理
        //try {
        //DefaultProfile.addEndpoint("dm.ap-southeast-1.aliyuncs.com", "ap-southeast-1", "Dm",  "dm.ap-southeast-1.aliyuncs.com");
        //} catch (ClientException e) {
        //e.printStackTrace();
        //}
        IAcsClient client = new DefaultAcsClient(profile);
        SingleSendMailRequest request = new SingleSendMailRequest();
        try {
            //request.setVersion("2017-06-22");// 如果是除杭州region外的其它region（如新加坡region）,必须指定为2017-06-22
            request.setAccountName("wjy@wjy8899.cn");
            request.setFromAlias("王静洋");
            request.setAddressType(1);
            request.setTagName("yzm");
            request.setReplyToAddress(true);
            request.setToAddress(toAddress);
            request.setSubject(subject);
            request.setHtmlBody(htmlBody);
            SingleSendMailResponse httpResponse = client.getAcsResponse(request);
        } catch (ServerException e) {
            e.printStackTrace();
        }
        catch (ClientException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        sample("296121471@qq.com","Thunder-验证码",BEFORE_HTML+123432+SUFFIX_HTML);
    }
}
