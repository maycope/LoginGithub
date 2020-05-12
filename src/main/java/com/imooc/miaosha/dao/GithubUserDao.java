package com.imooc.miaosha.dao;


import com.imooc.miaosha.domain.GithubUsers;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface GithubUserDao {
//
    @Select("select * from githubuser where account_id=#{account_id}")
    GithubUsers selectById(@Param("account_id") String account_id);

    @Insert("insert into githubuser (id, account_id, name, token, gmt_create, gmt_modified,   bio, avatar_url, password, salt) values (#{id,jdbcType=BIGINT}, #{accountId,jdbcType=VARCHAR}," +
            " #{name,jdbcType=VARCHAR}, \n" +
            "      #{token,jdbcType=CHAR}, #{gmtCreate,jdbcType=BIGINT}, #{gmtModified,jdbcType=BIGINT},     #{bio,jdbcType=VARCHAR}, #{avatarUrl,jdbcType=VARCHAR}, #{password,jdbcType=VARCHAR}, \n" +
            "      #{salt,jdbcType=VARCHAR})")
    void insert(GithubUsers user);
}
