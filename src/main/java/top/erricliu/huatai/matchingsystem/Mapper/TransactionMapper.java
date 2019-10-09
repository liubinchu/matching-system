package top.erricliu.huatai.matchingsystem.Mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import top.erricliu.huatai.matchingsystem.entity.transaction.Transaction;

import java.util.List;

@Mapper
@Repository
public interface TransactionMapper {
    @Select("select * from transaction")
    @Results({
            @Result(property = "bondId", column = "bondId")
    })
    List<Transaction> getAll();

    //@Select("select * from transaction where id=#{id}")
    //Transaction getById(Long id);

    @Insert({"insert into transaction(buyerId,sellerId,bondId,quantity,dealingPrice,timestamp) values(#{buyerId},#{sellerId},#{bondId},#{quantity},#{dealingPrice},UNIX_TIMESTAMP(#{timestamp}))"})
    void install(Transaction transaction);

    //@Update({"update transaction set name=#{name} where id=#{id}"})
    //void Update(Transaction transaction);


}