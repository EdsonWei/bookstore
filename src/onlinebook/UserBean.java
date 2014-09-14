package onlinebook;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;

import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.swing.JOptionPane;

import java.util.*;
//�����й�BeanStudent������Ŀ��JSFҳ�����������������ΪSessionScoped
@ManagedBean
@SessionScoped
public class UserBean {
	//����һЩ���������洢JSFҳ���е�����
	String name;
	String pwd;
    String home;
    String school;
    String id;
    String rank;
    UserEO aUser;
    String pwda;
    List<UserEO> allUser = null;
    
    //ע���������ݿ��������״̬�ỰBean
    @EJB
	DBopUser op;
   
    public void validate(FacesContext context,
 			UIComponent component,
			Object obj)throws ValidatorException {
		String password = (String) obj;
		if(password.length() < 8) {
			FacesMessage message = new FacesMessage(
			FacesMessage.SEVERITY_ERROR,
			"�ַ�����С��8",
			"�ַ����Ȳ���С��8");
			FacesContext.getCurrentInstance().addMessage(null, message);
			throw new ValidatorException(message);
		}
		if(!password.matches(".+[0-9]+")) {
			FacesMessage message = new FacesMessage(
			FacesMessage.SEVERITY_ERROR,
			"�����������ַ�������",
			"����������ַ������������");
			FacesContext.getCurrentInstance().addMessage(null, message);
			throw new ValidatorException(message);
		}

    }

	//�������һ����ѧ���ķ���
    public String AddUser() throws Exception  {
    	UserEO newUser= new UserEO();
    	newUser.setName(this.name);
    	newUser.setPwd(this.pwd);
    	newUser.setHome(this.home);
    	newUser.setSchool(this.school);
    	newUser.setId(this.id);
    	newUser.setRank("0");
    	List<UserEO> temp=op.getAllUser();
    	Iterator<UserEO> it=temp.iterator();
    	if(!pwd.equals(pwda))
    		return "register_buyizhi";
    	int ff=1;
    	while(it.hasNext()){
    		UserEO tmp=it.next();
    		if(tmp.getName().equals(this.name)) {
    			ff=-1;
    			break;
    		}
    	}
    	//����EJB�е� addNewStudent��ʵ����Ӳ���
    	if(ff==1)
    	{
    		
    		op.addNewUser(newUser);return "login";
    	}
    	else{
    	//����ֵΪstudent��ʹҳ�浼����student.xhtml
    		return "register_again";
    	}
    }
    public String date(){   
        String temp_str="";   
        Date dt = new Date();   
        //����aa��ʾ�����硱�����硱    HH��ʾ24Сʱ��    �������hh��ʾ12Сʱ��   
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss aa");   
        temp_str=sdf.format(dt);   
        return temp_str;   
    } 


    public String gaiUser() throws Exception  {
    	List<UserEO> temp=op.getUserById(this.name);
		Iterator<UserEO> it=temp.iterator();
		if(!it.hasNext()) return "wangji_bucunzai";
		UserEO next=it.next();
    	UserEO newUser= new UserEO();
    	if(!next.getHome().equals(home)||!next.getSchool().equals(school)||!next.getId().endsWith(id)) return "wangji_sb";
    	if(!pwd.equals(pwda))
    		return "wangji_buyizhi";
    	newUser.setName(this.name);
    	newUser.setPwd(this.pwd);
    	newUser.setHome(this.home);
    	newUser.setSchool(this.school);
    	newUser.setId(this.id);
    	newUser.setRank("0");
    	op.gaiNewUser(newUser);return "login";
    	
    }
    public String getPwda() {
		return pwda;
	}
	public void setPwda(String pwda) {
		this.pwda = pwda;
	}
	

	public String getName() {
		return name;
	}
	 @EJB
		DanLi opdan;
	 int zhong;
	 int cu;
	 public
	 UserBean(){
		 cu=0;
		 zhong=0;
	 }
	
	public String VerifyPassword(){
		List<UserEO> temp=op.getUserById(this.name);
		Iterator<UserEO> it=temp.iterator();
				
		if(!it.hasNext()) {return "login_sb";}		
		if(this.pwd.equals(temp.get(0).getPwd())) {CarBean.biao=name;
		opdan.setCu(opdan.getCu()+1);
		opdan.setZhong(opdan.getZhong()+1);
		cu=opdan.getCu();
		zhong=opdan.getZhong();
		return "success";}
		else {return "login_sb";}			
	}
	public DanLi getOpdan() {
		return opdan;
	}

	public void setOpdan(DanLi opdan) {
		this.opdan = opdan;
	}

	public int getZhong() {
		return zhong;
	}

	public void setZhong(int zhong) {
		this.zhong = zhong;
	}

	public int getCu() {
		return cu;
	}

	public void setCu(int cu) {
		this.cu = cu;
	}

	public String tui(){
		opdan.setCu(opdan.getCu()-1);
		cu=opdan.getCu();
		return "login";	
	}
	//���ڸ���һ��ѧ����Ϣ�ķ���    
    public String EditUser(){
    	//����3��������ȡҳ����ͨ����f:param�����ݵĲ��������еġ�params.get����������봫�ݵĲ�����������ƥ��    
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		String studentNum= params.get("name");
		//����EJB�е� findStudent�����Ҷ�Ӧ��ѧ������
		List<UserEO> temp=op.getUserById(studentNum);
		Iterator<UserEO> it=temp.iterator();
    	this.aUser=it.next();
    	//����ֵΪeditStudent��ʹҳ�浼����editStudent.xhtml
    	QueryUsers();
    	return "/admin/editStudent";
    }
  //���ڸ���һ��ѧ����Ϣ�ķ���    
    public String EditUserss(){
    	//����3��������ȡҳ����ͨ����f:param�����ݵĲ��������еġ�params.get����������봫�ݵĲ�����������ƥ��    
		
    	return "/admin/success";
    }
    //����ɾ��һ��ѧ����Ϣ�ķ���    
    public String DeleteUser(){
    	//����3��������ȡҳ����ͨ����f:param�����ݵĲ��������еġ�params.get����������봫�ݵĲ�����������ƥ��    
    	FacesContext fc = FacesContext.getCurrentInstance();
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		String studentNum= params.get("name");		
		//����EJB�е� deleteStudent��ɾ����Ӧ��ѧ����Ϣ
		op.deleteUser(studentNum);	
		
		List<MyLog> logs=op.getAllLog();
		Integer intid=(logs.size()+1);
		String logid=intid.toString();
		MyLog logtemp=new MyLog();
    	String datee=date();
    	logtemp.setDate(datee);
        logtemp.setId(logid);
        logtemp.setOp("ɾ");
        logtemp.setType("�û�����Ա");
        logtemp.setUser(quanju.name);
        op.addLog(logtemp);
		//����ֵΪstudent��ʹҳ�浼����student.xhtml
		QueryUsers();
    	return "/admin/student";
    }    
    
  //����JPQLʵ�ֲ�ѯ��ʾ��
    public String QueryUsers(){
    	String sql=" select c from  UserEO c   " ;
		//����EJB�е� executeQuery��ִ�й����JPQL��ѯ���    	
    	allUser=op.executeQuery(sql);
		//����ֵΪstudent��ʹҳ�浼����student.xhtml
    	
    	return "/admin/student";
    }
    public String QueryUsers2(){
    	String sql=" select c from  UserEO c   " ;
		//����EJB�е� executeQuery��ִ�й����JPQL��ѯ���    	
    	allUser=op.executeQuery(sql);
		//����ֵΪstudent��ʹҳ�浼����student.xhtml
    	List<MyLog> logs=op.getAllLog();
		Integer intid=(logs.size()+1);
		String logid=intid.toString();
		MyLog logtemp=new MyLog();
    	String datee=date();
    	logtemp.setDate(datee);
        logtemp.setId(logid);
        logtemp.setOp("��");
        logtemp.setType("�û�����Ա");
        logtemp.setUser(quanju.name);
        op.addLog(logtemp);
    	return "/admin/student";
    }
	public void setName(String name) {
		this.name = name;
	}
	//���ڸ���һ��ѧ����Ϣ�ķ���
    public String UpdateUser(){
    	//����EJB�е� updateStudent��ʵ����Ӳ���
    	op.updateUser(this.aUser);
    	
    	List<MyLog> logs=op.getAllLog();
		Integer intid=(logs.size()+1);
		String logid=intid.toString();
		MyLog logtemp=new MyLog();
    	String datee=date();
        logtemp.setDate(datee);
        logtemp.setId(logid);
        logtemp.setOp("��");
        logtemp.setType("�û�����Ա");
        logtemp.setUser(quanju.name);
        op.addLog(logtemp);
    	//����ֵΪstudent��ʹҳ�浼����student.xhtml
    	QueryUsers();
    	return "/admin/student";
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


	public UserEO getaUser() {
		return aUser;
	}


	public void setaUser(UserEO aUser) {
		this.aUser = aUser;
	}


	public List<UserEO> getAllUser() {
		return allUser;
	}


	public void setAllUser(List<UserEO> allUser) {
		this.allUser = allUser;
	}


	public DBopUser getOp() {
		return op;
	}


	public void setOp(DBopUser op) {
		this.op = op;
	}
	
}
