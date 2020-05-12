package com.imooc.miaosha.provider;


import com.alibaba.fastjson.JSON;
import com.imooc.miaosha.dao.AccessTokenDto;
import com.imooc.miaosha.dao.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
//okHttp
public class GithubProvider {
    // 传递参数 调用地址 获取access_token
    public String getAccessToekn(AccessTokenDto accessTokenDto){
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        //将accessTokenDto转为json字符串传入参数
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDto));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            //得到的是类似这样的字符串，我们需要将它分割，只要access_token部分
            //access_token=9566ba3483a556c610be42d44338f3fd16a3b8d1&scope=&token_type=bearer
            return string.split("&")[0].split("=")[1];
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
    /**
     * 根据access_token获取用户信息
     *
     * @param access_token
     * @return
     */
    public GithubUser getGithubUser(String access_token) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user")
                .header("Authorization", "token " + access_token)
                .build();

        try (Response response = client.newCall(request).execute()){
            //得到的是json字符串，因此需要转为GithubUser对象
            String str = response.body().string();
            return JSON.parseObject(str, GithubUser.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
