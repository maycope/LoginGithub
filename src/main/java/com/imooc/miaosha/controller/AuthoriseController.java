package com.imooc.miaosha.controller;


import com.imooc.miaosha.config.GithubConfig;
import com.imooc.miaosha.dao.AccessTokenDto;
import com.imooc.miaosha.dao.GithubUser;
import com.imooc.miaosha.domain.GithubUsers;
import com.imooc.miaosha.exception.GlobalException;
import com.imooc.miaosha.provider.GithubProvider;
import com.imooc.miaosha.result.CodeMsg;
import com.imooc.miaosha.service.GithubUserService;
import com.imooc.miaosha.util.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthoriseController {

    @Autowired
    GithubProvider githubProvider;
    @Autowired
    GithubConfig githubConfig;
    @Autowired
    GithubUserService githubUserService;

    private static final int COOKIE_EXPIRY = 60 * 60 * 24 * 7;// 设置过期的时间
    public AccessTokenDto accessTokenDto = new AccessTokenDto();
    @GetMapping("/githubCallback")
    public  String callback(@RequestParam("code")String code,
                            @RequestParam("state") String state,
                            HttpServletResponse response,
                            Model model)
    {
        setAccessTokenDto(code,state,githubConfig.getClient_id(),githubConfig.getClient_secret(),githubConfig.getRedirect_uri());
      String accessToken= githubProvider.getAccessToekn(accessTokenDto);
      GithubUser githubUser=githubProvider.getGithubUser(accessToken);
      if(githubUser!=null && githubUser.getId()!=null)
      {
          String token= UUID.randomUUID().toString();
          setUserInfo(token, githubUser.getName(), githubUser.getAvatarUrl(), "Github-" + githubUser.getId(), githubUser.getBio());
          CookieUtils.setCookie(response, "token", token, COOKIE_EXPIRY);
          model.addAttribute("name",githubUser.getName());
          return  "goods_list";
      } else {
          throw new GlobalException(CodeMsg.LOGIN_ERROR);
      }
    }
    private void setAccessTokenDto(String code, String state, String clientId, String clientSecret, String redirectUri) {
        accessTokenDto.setClient_id(clientId);
        accessTokenDto.setClient_secret(clientSecret);
        accessTokenDto.setCode(code);
        accessTokenDto.setRedirect_uri(redirectUri);
        accessTokenDto.setState(state);
    }
    private void setUserInfo(String token, String name, String avatarUrl, String accountId, String bio) {
        GithubUsers user = new GithubUsers();
        user.setToken(token);
        user.setName(name);
        user.setAvatarUrl(avatarUrl);
        user.setAccountId(accountId);
        user.setBio(bio);
      githubUserService.createOrUpdateUser(user);
    }

}
