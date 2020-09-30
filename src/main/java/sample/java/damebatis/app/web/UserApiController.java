package sample.java.damebatis.app.web;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sample.java.damebatis.app.web.response.ResponseData;
import sample.java.damebatis.domain.dto.UserEntity;
import sample.java.damebatis.domain.service.UserService;

@RestController
@RequestMapping("user")
@Slf4j
public class UserApiController {

  @Autowired UserService userService;

  @GetMapping
  public ResponseData readAll() {
    List<UserEntity> users = userService.getAllUsers();

    ResponseData responseData =
        new ResponseData().builder().status(HttpStatus.OK).data(users).build();
    return responseData;
  }

  @GetMapping("reset")
  public ResponseData reset() {
    userService.resetUsers();

    ResponseData responseData =
        new ResponseData().builder().status(HttpStatus.CREATED).message("リセット成功").build();
    return responseData;
  }
}
