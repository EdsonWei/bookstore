package onlinebook;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

@Entity
//ʵ��Bean StudentEO�������������ݿ��еı�students��ӳ��
@Table(name="user")
@NamedQueries({
	//�ڴ˶������ڴ�ʵ����ĳ��õĲ�ѯ
    @NamedQuery(name = "findAllUser",query = "SELECT l "
    + "FROM UserEO l")
    , @NamedQuery(name = "findUserById", query = "SELECT l FROM UserEO l "
    + " where l.name=:name")
    //, @NamedQuery(name = "findStudentByName", query = "SELECT DISTINCT l FROM StudentEO l "
    //+ " WHERE l.studentName = :studentName")
    //, @NamedQuery(name = "findStudentByID", query = "SELECT DISTINCT l FROM StudentEO l "
    //+ " WHERE l.studentNum = :studentNum")
})
public class UserEO implements Serializable {
	private static final long serialVersionUID = 1L;
	//@Idָ���ؼ��֣�ÿ��ʵ���඼����Ҫ��
	@Id
	@Column(name="name")
	private String name;

	@Column(name="pwd")
	private String pwd;

	@Column(name="home")
	private String home;

	@Column(name="school")
	private String school;

	@Column(name="id")
	private String id;

	@Column(name="rank")
	private String rank;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getHome() {
		return home;
	}

	public void setHome(String home) {
		this.home = home;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}