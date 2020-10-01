package sample.java.damebatis.domain.service;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sample.java.damebatis.domain.dto.UserEntity;
import sample.java.damebatis.domain.exception.UserException;
import sample.java.damebatis.domain.repository.UserRepository;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
  @Autowired private UserRepository userRepository;

  /**
   * ユーザをDBから取得する。
   *
   * @return List<UserEntity ユーザ情報List
   */
  @Override
  public List<UserEntity> getAllUsers() {
    List<UserEntity> results = userRepository.getAll();
    if (results.size() == 0) {
      throw new UserException("1件も取得できませんでした。");
    }
    return results;
  }

  /** ユーザをDBから全削除し、初期データを追加する。 */
  @Override
  @Transactional
  public void resetUsers() {
    userRepository.deleteAllUsers();

    List<UserEntity> users = new ArrayList<>();
    users.add(createEntity("1"));
    users.add(createEntity("2"));
    users.add(createEntity("3"));
    users.add(createEntity("4"));
    users.add(createEntity("5"));

    userRepository.bulkInsert(users);
  }

  @Override
  public void nonTransactionalInsert() {
    transactionalInsertPrivate();
  }

  @Override
  public void nonTransactionalInsertWithError() {
    transactionalInsertWIthErrorPrivate();
  }

  @Override
  @Transactional
  public void transactionalInsert() {
    List<UserEntity> users = new ArrayList<>();
    users.add(createEntity("★★1"));

    userRepository.bulkInsert(users);
  }

  @Override
  @Transactional
  public void transactionalInsertWithError() {
    List<UserEntity> users = new ArrayList<>();
    users.add(createEntity("★★0"));

    userRepository.bulkInsert(users);
    // 例外発生
    throw new RuntimeException();
  }

  @Override
  @Transactional(readOnly = true)
  public void readOnlyTransactionalInsert() {
    List<UserEntity> users = new ArrayList<>();
    users.add(createEntity("★★2"));

    userRepository.bulkInsert(users);
  }

  @Override
  public void callPublicTransactionalInsert() {
    transactionalInsertPublic_CalledFrom_AnotherPublicNonTransactionalMethod();
  }

  @Override
  public void callPublicTransactionalInsertWithError() {
    transactionalInsertWIthErrorPublic_CalledFrom_AnotherPublicNonTransactionalMethod();
  }

  @Override
  public void call_readOnlyTransactionalInsert2() {
    readOnlyTransactionalInsert2();
  }

  @Override
  @Transactional(readOnly = true)
  public void readOnlyTransactionalInsert2() {
    List<UserEntity> users = new ArrayList<>();
    users.add(createEntity("★★3"));

    userRepository.bulkInsert(users);
  }

  @Transactional
  private void transactionalInsertPrivate() {
    List<UserEntity> users = new ArrayList<>();
    users.add(createEntity("★1"));

    userRepository.bulkInsert(users);
  }

  @Transactional
  private void transactionalInsertWIthErrorPrivate() {
    List<UserEntity> users = new ArrayList<>();
    users.add(createEntity("★2"));

    userRepository.bulkInsert(users);

    // 例外発生
    throw new RuntimeException();
  }

  @Transactional
  public void transactionalInsertPublic_CalledFrom_AnotherPublicNonTransactionalMethod() {
    List<UserEntity> users = new ArrayList<>();
    users.add(createEntity("★3"));

    userRepository.bulkInsert(users);
  }

  @Transactional
  public void transactionalInsertWIthErrorPublic_CalledFrom_AnotherPublicNonTransactionalMethod() {
    List<UserEntity> users = new ArrayList<>();
    users.add(createEntity("★4"));

    userRepository.bulkInsert(users);

    // 例外発生
    throw new RuntimeException();
  }

  private UserEntity createEntity(String tooshiBangou) {
    return UserEntity.builder() //
        .email("email_test@ema.il" + tooshiBangou) //
        .challenge("challenge_test" + tooshiBangou) //
        .attestation("attestation_test" + tooshiBangou) //
        .build();
  }
}
