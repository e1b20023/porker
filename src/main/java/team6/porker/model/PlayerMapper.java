package team6.porker.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface PlayerMapper {
  @Insert("INSERT INTO player (playername) VALUES (#{name});")
  void insertPlayerName(String name);

  @Update("UPDATE player SET hand = #{result}, score = #{score} WHERE id = #{id}")
  void updateResult(int id, int result, int score);

  @Select("SELECT * FROM player")
  ArrayList<Player> selectAllPlayer();

  @Select("SELECT id FROM player WHERE playername = #{loginuser}")
  int selectPlayerId(String loginuser);
}
