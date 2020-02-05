package golden.serviceimpl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import golden.dao.userdao;
import golden.model.User;
import golden.service.userservice;
@Service
public class userserviceimpl implements userservice{
    @Autowired
	userdao userdao;
	@Override
	public void insertuser(User nuser) {
		// TODO Auto-generated method stub
		userdao.insertuser(nuser);
	}
	@Override
	public User selectuser(User nuser) {
		return userdao.selectuser(nuser);// TODO Auto-generated method stub
	}
	@Override
	public User selectbyusername(User nuser) {
		return userdao.selectbyusername(nuser);// TODO Auto-generated method stub
	}
	@Override
	public User selectUserById(Integer valueOf) {
		// TODO Auto-generated method stub
		return userdao.selectbyid(valueOf);
	}
}
