package sample.java.damebatis.app.web;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sample.java.damebatis.app.web.response.ResponseData;
import sample.java.damebatis.domain.dto.UserEntity;
import sample.java.damebatis.domain.service.UserService;

@RestController
@RequestMapping("directlyCalled")
@Slf4j
public class DirectlyCalledApiController {
  @Autowired UserService userService;

  @Autowired private ModelMapper modelMapper;

  @GetMapping("dame1")
  public ResponseData dame1() {
    userService.nonTransactionalInsertWithError();

    ResponseData responseData =
        new ResponseData().builder().status(HttpStatus.OK).message("処理は成功").build();
    return responseData;
  }

  @GetMapping("dame2")
  public ResponseData dame2() {
    userService.nonTransactionalInsert();

    ResponseData responseData =
        new ResponseData().builder().status(HttpStatus.OK).message("処理は成功").build();
    return responseData;
  }

  @GetMapping("yoki1")
  public ResponseData yoki1() {
    userService.transactionalInsert();

    ResponseData responseData =
        new ResponseData().builder().status(HttpStatus.OK).message("処理は成功").build();
    return responseData;
  }

  @GetMapping("yoki2")
  public ResponseData yoki2() {
    userService.transactionalInsertWithError();

    ResponseData responseData =
        new ResponseData().builder().status(HttpStatus.OK).message("処理は成功").build();
    return responseData;
  }

  @GetMapping("yoki3")
  public ResponseData yoki3() {
    userService.readOnlyTransactionalInsert();

    ResponseData responseData =
        new ResponseData().builder().status(HttpStatus.OK).message("処理は成功").build();
    return responseData;
  }

  @GetMapping("dame3")
  public ResponseData dame3() {
    userService.callPublicTransactionalInsert();

    ResponseData responseData =
        new ResponseData().builder().status(HttpStatus.OK).message("処理は成功").build();
    return responseData;
  }

  @GetMapping("dame4")
  public ResponseData dame4() {
    userService.callPublicTransactionalInsertWithError();

    ResponseData responseData =
        new ResponseData().builder().status(HttpStatus.OK).message("処理は成功").build();
    return responseData;
  }

  @GetMapping("dame5")
  public ResponseData dame5() {
    userService.call_readOnlyTransactionalInsert2();

    ResponseData responseData =
        new ResponseData().builder().status(HttpStatus.OK).message("処理は成功").build();
    return responseData;
  }
}
