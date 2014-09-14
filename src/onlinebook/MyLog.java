package onlinebook;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;

@Entity
//ʵ��Bean StudentEO�������������ݿ��еı�students��ӳ��
@Table(name="mylog")
@NamedQueries({
	//�ڴ˶������ڴ�ʵ����ĳ��õĲ�ѯ
    @NamedQuery(name = "findAllLog",query = "SELECT l "
    + "FROM MyLog l")
    , @NamedQuery(name = "findLogByType", query = "SELECT l FROM MyLog l "
    + " WHERE l.type = :type")
    , @NamedQuery(name = "findLogByDate", query = "SELECT DISTINCT l FROM MyLog l "
    + " WHERE l.date = :date")
})
public class MyLog implements Serializable {
	private static final long serialVersionUID = 1L;
	//@Idָ���ؼ��֣�ÿ��ʵ���඼����Ҫ��
	@Id
	@Column(name="id")
	private String id;

	@Column(name="user")
	private String user;

	@Column(name="type")
	private String type;

	@Column(name="op")
	private String op;

	@Column(name="date")
	private String date;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String string) {
		date = string;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	
}