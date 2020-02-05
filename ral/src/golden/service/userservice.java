package golden.service;
import java.util.List;

import golden.model.User;

public interface userservice {
	public void insertuser(User nuser);

	public User selectuser(User nuser);


	public User selectbyusername(User nuser);

	public User selectUserById(Integer valueOf);
}
