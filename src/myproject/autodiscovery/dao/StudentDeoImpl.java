package myproject.autodiscovery.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.mysql.cj.jdbc.MysqlDataSource;

import myproject.autodiscovery.dto.Student;

//@Component
@Repository("studentDao")
public class StudentDeoImpl implements StudentDao{

	@Autowired
	private MysqlDataSource dataSource;
	
	@Override
	public String add(Student student) {
		String status = "";
		try {
			Connection con = dataSource.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from student where sid=?");
			ps.setString(1, student.getSid());
			ResultSet rs = ps.executeQuery();
			boolean b = rs.next();
			if(b==true) {
				status = "existed";
			}else {
				ps = con.prepareStatement("insert into student values(?,?,?)");
				ps.setString(1, student.getSid());
				ps.setString(2, student.getSname());
				ps.setString(3, student.getSaddr());
				int row = ps.executeUpdate();
				if(row ==1) {
					status = "success";
				}else {
					status = "failure";
				}
			}
		}catch (Exception e) {
			status = "failure";
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public Student search(String sid) {
		Student student = null;
		try {
			Connection con = dataSource.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from student where sid=?");
			ps.setString(1, sid);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
			student = new Student();
			student.setSid(rs.getString(1));
			student.setSname(rs.getString(2));
			student.setSaddr(rs.getString(3));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return student;
	}

	@Override
	public String update(Student student) {
		String status = "failure";
		try {
			Connection con = dataSource.getConnection();
			PreparedStatement ps = con.prepareStatement("update student set sname=?, saddr=? where sid=?");
			ps.setString(1, student.getSname());
			ps.setString(2, student.getSaddr());
			ps.setString(3, student.getSid());
			int row = ps.executeUpdate();
			if(row==1) {
				status = "success";
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return status;
	}

	@Override
	public String delete(String sid) {
		String status = "failure";
		try {
			Connection con = dataSource.getConnection();
			PreparedStatement ps = con.prepareStatement("delete from student where sid=?");
			ps.setString(1, sid);
			int row = ps.executeUpdate();
			if(row==1) {
				status="success";
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return status;
	}

}
