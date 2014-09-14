package onlinebook;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

@Entity
//ʵ��Bean StudentEO�������������ݿ��еı�students��ӳ��
@Table(name="book")
@NamedQueries({
	//�ڴ˶������ڴ�ʵ����ĳ��õĲ�ѯ
    @NamedQuery(name = "findAllBook",query = "SELECT l "
    + "FROM BookEO l")
    , @NamedQuery(name = "findBookByIsbn", query = "SELECT l FROM BookEO l "
    + " WHERE l.isbn = :isbn")
    , @NamedQuery(name = "findBookByType", query = "SELECT DISTINCT l FROM BookEO l "
    + " WHERE l.type = :type")
})
public class BookEO implements Serializable {
	private static final long serialVersionUID = 1L;
	//@Idָ���ؼ��֣�ÿ��ʵ���඼����Ҫ��
	@Id
	@Column(name="isbn")
	private String isbn;

	@Column(name="author")
	private String author;

	@Column(name="pub")
	private String pub;

	@Column(name="price")
	private float price;

	@Column(name="type")
	private String type;

	
	public BookEO() {
	}


	public String getIsbn() {
		return isbn;
	}


	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public String getPub() {
		return pub;
	}


	public void setPub(String pub) {
		this.pub = pub;
	}


	public float getPrice() {
		return price;
	}


	public void setPrice(float price) {
		this.price = price;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}