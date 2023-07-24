package com.atguigu.srb.mybatisPlus;

import com.atguigu.srb.mybatisPlus.mapper.ProductMapper;
import com.atguigu.srb.mybatisPlus.mapper.UserMapper;
import com.atguigu.srb.mybatisPlus.pojo.entity.Product;
import com.atguigu.srb.mybatisPlus.pojo.entity.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.function.Consumer;

@SpringBootTest
public class Test6 {

    @Autowired
    UserMapper userMapper;

    @Autowired
    ProductMapper productMapper;

    @Test
    public void t8(){
        String name = "n";
        Integer age = null;
        //查询名字中包含n，且（年龄小于20或email为空的用户），
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<User>();
        userQueryWrapper
                .like(StringUtils.isNotBlank(name),"name",name)
                .and((innerWrapper)->innerWrapper.lt(age!=null,"age",age).or().isNull("email"));

        List<User> users = userMapper.selectList(userQueryWrapper);
        System.out.println(users);
    }
    
    @Test
    public void t7(){

        QueryWrapper<User> userQueryWrapper = new QueryWrapper<User>();
        userQueryWrapper.eq("name","张三");
        User user = new User();
        user.setName("李四");
        userMapper.update(user,userQueryWrapper);

        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.eq("name","李四");
        userUpdateWrapper.setSql("name = '张三',age=100");
        userMapper.update(null,userUpdateWrapper);

    }

    @Test
    public void t6(){
        String ids = "3 or true";
        //查询id不大于3的所有用户的id列表
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<User>();
        userQueryWrapper.inSql("id","select id from user where id <="+ids);
        List<User> users = userMapper.selectList(userQueryWrapper);
        System.out.println(users);
    }

    @Test
    public void t5(){
        //查询所有用户的用户名和年龄
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<User>();
        userQueryWrapper.select("name","age");
        List<User> users = userMapper.selectList(userQueryWrapper);
        System.out.println(users);

    }

    @Test
    public void t4_2(){
        //查询名字中包含n，且（年龄小于20或email为空的用户），
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<User>();
        userQueryWrapper
                .like("name","n")
                .and((innerWrapper)->innerWrapper.lt("age",20).or().isNull("email"));

        List<User> users = userMapper.selectList(userQueryWrapper);
        System.out.println(users);
    }

    @Test
    public void t4(){

        Consumer<QueryWrapper<User>> consumer = new Consumer<QueryWrapper<User>>() {
            @Override
            public void accept(QueryWrapper<User> userQueryWrapper) {
                userQueryWrapper
                        .lt("age",20)
                        .or()
                        .isNull("email");
            }
        };
        //查询名字中包含n，且（年龄小于20或email为空的用户），
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<User>();
        userQueryWrapper
                .like("name","n")
                .and(consumer);

        List<User> users = userMapper.selectList(userQueryWrapper);
        System.out.println(users);

        //并将这些用户的年龄设置为18，邮箱设置为user@atguigu.com

    }

    @Test
    public void t3(){
        //删除email为空的用户
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<User>();
        userQueryWrapper.isNull("email");
        int i = userMapper.delete(userQueryWrapper);
        System.out.println(i);

    }

    @Test
    public void t2(){
        // 按年龄降序查询用户，如果年龄相同则按id降序排列
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<User>();
        userQueryWrapper
                .orderByDesc("age")
                .orderByDesc("id");
        List<User> users = userMapper.selectList(userQueryWrapper);
        System.out.println(users);

    }

    @Test
    public void t1(){

        // 查询名字中包含n，年龄大于等于10且小于等于20，email不为空的用户
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<User>();
        userQueryWrapper
                .like("name","n")
                .between("age",10,20)
                .isNotNull("email");
        List<User> users = userMapper.selectList(userQueryWrapper);
        System.out.println(users);


    }
}
