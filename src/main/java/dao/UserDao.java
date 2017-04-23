package dao;

import java.util.List;

import config.AppConfig;
import entity.User;
import org.seasar.doma.BatchInsert;
import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.jdbc.BatchResult;
import org.seasar.doma.jdbc.Result;

@Dao(config = AppConfig.class)
public interface UserDao {

    @Insert
    Result<User> insert(User user);

    @BatchInsert
    BatchResult<User> batchInsert(List<User> users);
}
