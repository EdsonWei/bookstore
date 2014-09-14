package onlinebook;

import javaee.jsf.StuEntities.StudentEO;
import javaee.jsf.ejb.DBop;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

//�����й�BeanStudent������Ŀ��JSFҳ�����������������ΪSessionScoped
@ManagedBean
@SessionScoped
public class CarItem {
	//����һЩ���������洢JSFҳ���е�����
	String isbn;
	String author;
	String pub;
	float price;
	String type;
    int count;
public CarItem(){}
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
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
   
}
