package com.demo.controller;

import com.demo.DemoApplication;
import com.demo.entity.User;
import com.demo.repository.UserRepository;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by steadyjack on 2017/3/22.
 */
@SpringBootTest(classes = DemoApplication.class)
@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserRepository userRepository;

  /**
   * 用户列表
   * @return
   */
  @RequestMapping("/list")
  public List<User> listUser() {
    List<User> userList=null;
    try {
      userList=userRepository.getUserList();
    }catch (Exception e){
      System.out.println("异常信息:  "+e.getMessage());
    }
    return userList;
  }

  /**
   * 根据id查询User实体
   * @param id
   * @return
   */
  @RequestMapping("/{id}")
  public User getUserById(@PathVariable Integer id){
    User user=null;
    try {
      user=userRepository.getUserById(id);
    }catch (Exception e){
      user=new User(1,"admin","admin@sina.com");
      System.out.println("异常信息： "+e.getMessage());
    }
    return user;
  }

  /**
   * 保存user实体
   * @param user
   * @return
   */
  @RequestMapping(value = "/save",method = RequestMethod.POST)
  public int insertUser(User user){
    int res=1;
    try {
      res=userRepository.saveUser(user);
    }catch (Exception e){
      System.out.println("异常信息： "+e.getMessage());
    }
    return res;
  }

  /**
   * 保存User实体-PreparedStatementSetter
   * @param user
   * @return
   */
  @RequestMapping(value = "/saveWithSafe",method = RequestMethod.POST)
  public int insertUserWithSafe(User user){
    int res=1;
    try {
      res=userRepository.saveUserWithSafe(user);
    }catch (Exception e){
      System.out.println("异常信息： "+e.getMessage());
    }
    return res;
  }

  /**
   * 保存user实体-PreparedStatementCreator、KeyHolder-保存实体后返回实体的主键
   * @param user
   * @return
   */
  @RequestMapping(value = "/saveWithKey",method = RequestMethod.POST)
  public int insertUserWithKey(User user){
    int res=1;
    try {
      res=userRepository.saveUserWithKey(user);
    }catch (Exception e){
      System.out.println("异常信息： "+e.getMessage());
    }
    return res;
  }

  /**
   * 根据id更新user实体
   * @param id
   * @param request
   * @return
   */
  @RequestMapping(value = "/update/{id}",method = RequestMethod.POST)
  public int updateUserWithId(@PathVariable Integer id,HttpServletRequest request){
    int res=1;
    try {
      if (id!=null && !id.equals(0)){
        String name=request.getParameter("name");
        String email=request.getParameter("email");
        User updateUser=new User(id, Strings.isNullOrEmpty(name)?null:name,Strings.isNullOrEmpty(email)?null:email);
        res=userRepository.updateUser(updateUser);
      }
    }catch (Exception e){
      System.out.println("异常信息： "+e.getMessage());
    }
    return res;
  }

  /**
   * 根据id删除user实体
   * @param id
   * @return
   */
  @RequestMapping("/delete/{id}")
  public int deleteUserById(@PathVariable Integer id){
    int res=1;
    try {
      User deleteUser=userRepository.getUserById(id);
      res=userRepository.deleteUser(deleteUser);
    }catch (Exception e){
      System.out.println("异常信息： "+e.getMessage());
    }
    return res;
  }

  /**
   * 根据name查询是否存在某个user实体
   * @param request
   * @return
   */
  @RequestMapping("/isExistUser")
  public Boolean isExistUser(HttpServletRequest request){
    Boolean res=false;
    try {
      String name=request.getParameter("name");
//      User queryUser=new User(null,Strings.isNullOrEmpty(name)?null:name,null);
//      User deleteUser=userRepository.getUserByUserName(queryUser);
//      if (deleteUser!=null){
//        res=true;
//      }
    }catch (Exception e){
      System.out.println("异常信息： "+e.getMessage());
    }
    return res;
  }

  /**
   * 查询user实体的总数
   * @return
   */
  @RequestMapping("/total")
  public Integer getTotal(){
    Integer res=0;
    try {
      res=userRepository.getCount();
    }catch (Exception e){
      System.out.println("异常信息： "+e.getMessage());
    }
    return res;
  }

}

