package onlinebook;

import javaee.jsf.StuEntities.StudentEO;
import javaee.jsf.ejb.DBop;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

//�����й�BeanStudent������Ŀ��JSFҳ�����������������ΪSessionScoped
@ManagedBean
@SessionScoped
public class LogBean {
	//����һЩ���������洢JSFҳ���е�����
	String date;
	String type;
	 @EJB
		LogOp op;
	 @EJB
		DBopUser opp;
	 @EJB
		DBop oop;
	 List<MyLog> allLog = null;
	 public String QueryLogs(){
	    	allLog=opp.getAllLog();
			//����ֵΪstudent��ʹҳ�浼����student.xhtml
	    	return "/log/student";
	    }
	 public String QueryLog(){
	    	String sql=" select c from  MyLog c  where c.type like '%" + this.type + "%' and date like '%" + this.date +"%' " ;
			//����EJB�е� executeQuery��ִ�й����JPQL��ѯ���    	
	    	allLog=oop.executeQuerylog(sql);
			//����ֵΪstudent��ʹҳ�浼����student.xhtml
	    	return "/log/student";
	    }
	public LogOp getOp() {
		return op;
	}
	public void setOp(LogOp op) {
		this.op = op;
	}
	public DBopUser getOpp() {
		return opp;
	}
	public void setOpp(DBopUser opp) {
		this.opp = opp;
	}
	public List<MyLog> getAllLog() {
		return allLog;
	}
	public void setAllLog(List<MyLog> allLog) {
		this.allLog = allLog;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}
