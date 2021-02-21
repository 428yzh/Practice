package jdbc_dao;

import Beans.Jobs;

import java.sql.Connection;
import java.util.List;

/*
这个接口 主要实现操作   数据库里面的 Jobs表
有几个观念
1. 里面本来就有数据
2. 增删改通过jdbc操作改数据库里的
 */
public interface  JobsDao {
    // 获取全部对象，获取部分没有价值
    public List<Jobs> getJobsList(Connection conn);
    // 获取一个对象
    public Jobs queryJobs(Connection conn, String jobs_id);
    // 获取某个数据
    public <E> E queryOneData(Connection conn, String jobs_id, String sql);
    // 添加一个对象
    public int addOne(Connection conn, Jobs jobs);
    // 删除一个对象
    public int delOne(Connection conn, String jobs_id);
    // 修改一个对象
    public int modifyOne(Connection conn, Jobs jobs);
}
