package team6.porker.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface PlayerMapper {
  @Insert("INSERT INTO player (Playername) VALUES (#{name});")
  void insertPlayerName(String name);

  @Update("UPDATE player SET hand = #{result}, score = #{score} WHERE playername = #{name}")
  void updateResult(String name, int result, int score);

  @Select("SELECT * FROM player")
  ArrayList<Player> selectAllPlayer();
}
