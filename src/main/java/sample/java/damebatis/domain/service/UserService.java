package sample.java.damebatis.domain.service;

import java.util.List;

import sample.java.damebatis.domain.dto.UserEntity;

public interface UserService {
  List<UserEntity> getAllUsers();

  void resetUsers();

  void nonTransactionalInsert();

  void nonTransactionalInsertWithError();

  void transactionalInsert();

  void transactionalInsertWithError();

  void readOnlyTransactionalInsert();

  void callPublicTransactionalInsert();

  void callPublicTransactionalInsertWithError();
}
