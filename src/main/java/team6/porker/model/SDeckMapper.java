package team6.porker.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SDeckMapper {
  @Select("SELECT num from sdeck LIMIT 1")
  int selectBynumber1();

  @Insert("INSERT INTO sdeck (num) VALUES (#{num});")
  void insertsdecknumber(int num);

  @Delete("DELETE FROM sdeck WHERE num =#{num}")
  boolean deleteBynumber1(int num);

  @Select("SELECT num from sdeck")
  ArrayList<SDeck> selectAllSDecks();

  @Select("SELECT id from poker4 WHERE id = 1")
  Deck selectFirstByDeckid();
}
