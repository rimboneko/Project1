import java.sql.SQLException;
import java.util.List;

import com.project1.dao.ReimbursementDaoDB;
import com.project1.dao.UserDao;
import com.project1.dao.UserDaoDB;
import com.project1.exceptions.InvalidCredentialsException;
import com.project1.exceptions.UserDoesNotExistException;
import com.project1.exceptions.UsernameAlreadyExistsException;
import com.project1.models.Reimbursement;
import com.project1.models.User;
import com.project1.services.ReimbService;
import com.project1.services.UserService;

public class test {

	public static void main(String[] args) throws SQLException, UserDoesNotExistException, InvalidCredentialsException, UsernameAlreadyExistsException {
		// TODO Auto-generated method stub
		
		//User u= new User("mugisha","password","joelle","mugisha","joelle@mail.com",2);
		//UserDaoDB udao= new UserDaoDB();
		//UserDao u = new UserDaoDB();
		//UserService userv = new UserService(u);
		ReimbursementDaoDB rdao= new ReimbursementDaoDB();
		ReimbService rServ = new ReimbService(rdao);
		//Reimbursement r = new Reimbursement(100.0, "2021-10-10", "2021-10-20", "good", false, 1, 1, 3, 1);
		//rdao.createReimb(r);
		//udao.createUser(u);
		//List<User> test = udao.getAllEmployees();
		//System.out.println(test);
		//User test = userv.signIn("mboneko", "password");
		//System.out.println(test);
		//userv.register("smith","password","smith","jones","smith@mail.com",2);
		//List<Reimbursement> test= rdao.viewResReimb("mboneko");
		//System.out.println(test);
		//rdao.changeStatus(3, 1);
		rServ.subReimb(200, "2021-10-21", "-", "good", false, 3, 1, 1, 4);
	}

}
