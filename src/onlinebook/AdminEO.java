package onlinebook;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;

@Entity
//ʵ��Bean StudentEO�������������ݿ��еı�students��ӳ��
@Table(name="admin")
@NamedQueries({
	//�ڴ˶������ڴ�ʵ����ĳ��õĲ�ѯ
    @NamedQuery(name = "findAdminById", query = "SELECT l FROM AdminEO l "
    + " where l.user=:name")
    //, @NamedQuery(name = "findStudentByName", query = "SELECT DISTINCT l FROM StudentEO l "
    //+ " WHERE l.studentName = :studentName")
    //, @NamedQuery(name = "findStudentByID", query = "SELECT DISTINCT l FROM StudentEO l "
    //+ " WHERE l.studentNum = :studentNum")
})
public class AdminEO implements Serializable {
	private static final long serialVersionUID = 1L;
	//@Idָ���ؼ��֣�ÿ��ʵ���඼����Ҫ��
	@Id
	@Column(name="user")
	private String user;

	@Column(name="pwd")
	private String pwd;

	
	public String getUser() {
		return user;
	}

	public void setUser(String name) {
		this.user = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}