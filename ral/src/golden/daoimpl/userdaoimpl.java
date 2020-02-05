package golden.daoimpl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import golden.dao.userdao;
import golden.model.User;
@Repository
public class userdaoimpl extends SqlSessionDaoSupport implements userdao{
	@Autowired//生成数据库会话工厂
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
	      super.setSqlSessionFactory(sqlSessionFactory);
	}
	
String ns="golden.mapper.UserMapper.";//记得加点
	public void insertuser(User nuser) {
		// TODO Auto-generated method stub
    this.getSqlSession().insert(ns+"insert",nuser);//调用UserMapper配置文件，用数据库会话工厂
	}

	public  User selectuser(User nuser) {
		return this.getSqlSession().selectOne(ns+"selectByusernameAndpassword", nuser);// TODO Auto-generated method stub
	}

	
	public  User selectbyusername(User nuser) {
		return this.getSqlSession().selectOne(ns+"selectbyusername", nuser);// TODO Auto-generated method stub
	
	}

	@Override
	public User selectbyid(Integer valueOf) {
		// TODO Auto-generated method stub
		return this.getSqlSession().selectOne(ns+"selectByPrimaryKey", valueOf);
	}
}
