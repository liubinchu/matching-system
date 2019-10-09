package top.erricliu.huatai.matchingsystem.Mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import top.erricliu.huatai.matchingsystem.entity.transaction.testUser;

import java.util.List;

@Mapper
@Repository
public interface testUserMapper {
    @Select("select * from user")
    @Results({
            @Result(property = "name", column = "name")
    })
    List<testUser> getAll();

    @Select("select * from user where id=#{id}")
    testUser getById(Long id);

    @Insert({"insert into user(name,age,pwd) values(#{name},#{age},#{pwd})"})
    void install(testUser user);

    @Update({"update user set name=#{name} where id=#{id}"})
    void Update(testUser user);

    @Delete("delete from user where id=#{id}")
    void delete(Long id);
}
