package sample.java.damebatis.domain.repository;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import sample.java.damebatis.domain.dto.UserEntity;

@Mapper
public interface UserRepository {

  @Select("SELECT COUNT(*) FROM m_user WHERE email = #{email}")
  int countByEmail(String email);

  @Insert(
      "INSERT INTO "
          + "m_user("
          + "  email"
          + "  , challenge"
          + "  , attestation"
          + ") "
          + "VALUES("
          + "  #{user.email}"
          + "  , #{user.challenge}"
          + "  , #{user.attestation}"
          + ")")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void save(@Param("user") UserEntity user);

  @Select("SELECT * FROM m_user")
  @Results(
      id = "allUserEntity",
      value = {
        @Result(column = "id", property = "id"),
        @Result(column = "email", property = "email"),
        @Result(column = "challenge", property = "challenge"),
        @Result(column = "attestation", property = "attestation")
      })
  List<UserEntity> getAll();

  @Update(
      "UPDATE "
          + "m_user "
          + "SET"
          + " email = #{user.email}"
          + " , challenge = #{user.challenge}"
          + " , attestation = #{user.attestation}"
          + "WHERE"
          + " id = #{user.id}")
  int updateUserById(@Param("user") UserEntity user);

  @Delete("DELETE FROM m_user")
  void deleteAllUsers();

  @Insert(
      "<script>"
          + "INSERT INTO "
          + "  m_user("
          + "    email"
          + "    , challenge"
          + "    , attestation"
          + "  ) "
          + "  VALUES"
          + "  <foreach collection=\"users\" item=\"user\" separator=\",\">"
          + "    ( #{user.email}, #{user.challenge}, #{user.attestation} )"
          + "  </foreach>"
          + "</script>" //
  )
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void bulkInsert(List<UserEntity> users);
}
