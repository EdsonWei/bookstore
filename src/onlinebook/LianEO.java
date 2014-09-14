package onlinebook;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

@Entity
//ʵ��Bean StudentEO�������������ݿ��еı�students��ӳ��
@Table(name="lian")
@IdClass(pk.class)
/*
@NamedQueries({
	//�ڴ˶������ڴ�ʵ����ĳ��õĲ�ѯ
    @NamedQuery(name = "findAllBook",query = "SELECT l "
    + "FROM BookEO l")
    , @NamedQuery(name = "findBookByIsbn", query = "SELECT l FROM BookEO l "
    + " WHERE l.isbn = :isbn")
    , @NamedQuery(name = "findBookByType", query = "SELECT DISTINCT l FROM BookEO l "
    + " WHERE l.type = :type")
})
*/
public class LianEO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	//@Idָ���ؼ��֣�ÿ��ʵ���඼����Ҫ��
	@Id
	@Column(name="id")
	private String id;
	@Id
	@Column(name="isbn")
	private String isbn;

	
	@Column(name="count")
	private int count;

	
	public LianEO() {
	}




	

	
	public String getId() {
		return id;
	}







	public void setId(String id) {
		this.id = id;
	}







	public String getIsbn() {
		return isbn;
	}







	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}







	public int getCount() {
		return count;
	}







	public void setCount(int count) {
		this.count = count;
	}







	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}