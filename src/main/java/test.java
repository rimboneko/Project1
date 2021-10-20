import java.sql.SQLException;

import com.project1.dao.ReimbursementDaoDB;
import com.project1.dao.UserDaoDB;
import com.project1.models.Reimbursement;
import com.project1.models.User;

public class test {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub

		//User u= new User("mboneko","password","rich","mboneko","rich@mail.com",1);
		//UserDaoDB udao= new UserDaoDB();
		ReimbursementDaoDB rdao= new ReimbursementDaoDB();
		Reimbursement r = new Reimbursement(200.0, "2020-10-10", "2020-10-20", "good", false, 1, 1, 1, 1);
		rdao.createReimb(r);
		//udao.createUser(u);
	}

}
