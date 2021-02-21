package impl;

import Beans.Jobs;
import jdbc_dao.BaseDao;
import jdbc_dao.JobsDao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class JobsDaoImpl extends BaseDao <Jobs> implements JobsDao  {
    @Override
    public List<Jobs> getJobsList(Connection conn) {
        List<Jobs> jobsList = new ArrayList<>();
        String sql = "select * from jobs";
        jobsList = queryForT(conn,sql);   // 不传参数
        return jobsList;
    }

    @Override
    public Jobs queryJobs(Connection conn, String jobs_id) {
        String sql = "select * from jobs where job_id=?";
        Jobs jobs = null;
        try {
            jobs = queryOne(conn, sql);
        }catch(Exception e){
            e.printStackTrace();
        }
        return jobs;
    }
    // 这里一般不会有这种东西出现
    @Override
    public <E> E queryOneData(Connection conn, String jobs_id, String sql) {
        E e = query(conn,sql);
        return e;
    }

    @Override
    public int addOne(Connection conn, Jobs jobs) {
        String sql = "insert into jobs(job_id,job_title,min_salary,max_salary) values(?,?,?,?)";
        try {
            int n = updata(conn, sql,jobs.getJob_id(), jobs.getJob_title(), jobs.getMax_salary(), jobs.getMin_salary());
            return n;
        }catch(Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int delOne(Connection conn, String jobs_id){
        String sql = "delect from jobs where jobs_id=?";
        try {
            int n = updata(conn, sql, jobs_id);
            return n;
        }catch(Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int modifyOne(Connection conn, Jobs jobs) {
        String sql = "update jobs set job_id=?,job_title=?,min_salary=?,max_salary=? where job_id=?";
        try{
            return updata(conn,sql,jobs.getJob_id(), jobs.getJob_title(),jobs.getMin_salary(),jobs.getMax_salary(),jobs.getJob_id());
        }catch(Exception e){
            e.printStackTrace();
            return 0;
        }
    }
}
