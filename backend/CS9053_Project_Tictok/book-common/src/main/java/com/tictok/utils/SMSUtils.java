package com.tictok.utils;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20210111.SmsClient;
import com.tencentcloudapi.sms.v20210111.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20210111.models.SendSmsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SMSUtils {
    @Autowired
    private TencentCloudProperties tencentCloudProperties;

    public void sendSMS(String phone, String code) throws Exception {
        try {
            Credential cred = new Credential(tencentCloudProperties.getSecretId(),
                    tencentCloudProperties.getSecretKey());

            HttpProfile httpProfile = new HttpProfile();

            httpProfile.setEndpoint("sms.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);
            SmsClient client = new SmsClient(cred, "ap-nanjing", clientProfile);

            SendSmsRequest req = new SendSmsRequest();
            String[] phoneNumberSet1 = {"+86" + phone};
            req.setPhoneNumberSet(phoneNumberSet1);
            req.setSmsSdkAppId("10000000000");
            req.setSignName("NYU");
            req.setTemplateId("11217");

            String[] templateParamSet1 = {code};
            req.setTemplateParamSet(templateParamSet1);

            SendSmsResponse resp = client.SendSms(req);
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
    }
}


