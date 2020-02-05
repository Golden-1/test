package golden.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import golden.model.User;

public interface userdao {
     public void insertuser(User nuser);

	 public User selectuser(User nuser);

	 public User selectbyusername(User nuser);

	public User selectbyid(Integer valueOf);
}
