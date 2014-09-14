package onlinebook;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

@Entity
//ʵ��Bean StudentEO�������������ݿ��еı�students��ӳ��
@Table(name="myorder")

@NamedQueries({
	//�ڴ˶������ڴ�ʵ����ĳ��õĲ�ѯ
    @NamedQuery(name = "findAllOrder",query = "SELECT l "
    + "FROM OrderEO l")
  , @NamedQuery(name = "findAllItem", query = "SELECT l FROM LianEO l "
   + " WHERE l.id = :id")
  //  , @NamedQuery(name = "findBookByType", query = "SELECT DISTINCT l FROM BookEO l "
  //  + " WHERE l.type = :type")
})

public class OrderEO implements Serializable {
	private static final long serialVersionUID = 1L;
	//@Idָ���ؼ��֣�ÿ��ʵ���඼����Ҫ��
	@Id
	@Column(name="num")
	private String num;

	@Column(name="ad")
	private String ad;

	@Column(name="ph")
	private String ph;

	@Column(name="zhe")
	private float zhe;

	@Column(name="total")
	private float total;

	
	public OrderEO() {
	}


	public String getNum() {
		return num;
	}


	public void setNum(String num) {
		this.num = num;
	}


	public String getAd() {
		return ad;
	}


	public void setAd(String ad) {
		this.ad = ad;
	}


	public String getPh() {
		return ph;
	}


	public void setPh(String ph) {
		this.ph = ph;
	}


	public float getZhe() {
		return zhe;
	}


	public void setZhe(float zhe) {
		this.zhe = zhe;
	}


	public float getTotal() {
		return total;
	}


	public void setTotal(float total) {
		this.total = total;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}




	

	
}