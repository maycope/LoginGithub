package com.imooc.miaosha.service;


import com.imooc.miaosha.dao.GithubUserDao;
import com.imooc.miaosha.domain.GithubUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GithubUserService {

    @Autowired
    GithubUserDao githubUserDao;

    public void createOrUpdateUser(GithubUsers user) {
        String account_id=user.getAccountId();
       GithubUsers users =githubUserDao.selectById(account_id);
        if(user==null){
            // 表示是第一次进行登录和注册 写入到数据库
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            System.out.println(user.getGmtCreate());
            githubUserDao.insert(user);
        }
        else
            System.out.println("用户已经存在，可以查看是否更改了信息，后续增加");

    }
}
