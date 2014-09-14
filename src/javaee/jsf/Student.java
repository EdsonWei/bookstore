package javaee.jsf;

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
public class Student {
	//����һЩ���������洢JSFҳ���е�����
	int stu_Num;
	String stu_Name;
    int stu_TeamNum;
    int stu_Age;
    String stu_Major;
    String stu_Gender;
    StudentEO aStudent;
    List<StudentEO> allStudent = null;
    
    //ע���������ݿ��������״̬�ỰBean
    @EJB
	DBop op;
   
    public int getStu_Num() {
		return stu_Num;
	}

	public void setStu_Num(int stu_Num) {
		this.stu_Num = stu_Num;
	}

	public String getStu_Name() {
		return stu_Name;
	}

	public void setStu_Name(String stu_Name) {
		this.stu_Name = stu_Name;
	}

	public int getStu_TeamNum() {
		return stu_TeamNum;
	}

	public void setStu_TeamNum(int stu_TeamNum) {
		this.stu_TeamNum = stu_TeamNum;
	}

	public int getStu_Age() {
		return stu_Age;
	}

	public void setStu_Age(int stu_Age) {
		this.stu_Age = stu_Age;
	}

	public String getStu_Major() {
		return stu_Major;
	}

	public void setStu_Major(String stu_Major) {
		this.stu_Major = stu_Major;
	}

	public String getStu_Gender() {
		return stu_Gender;
	}

	public void setStu_Gender(String stu_Gender) {
		this.stu_Gender = stu_Gender;
	}

	public StudentEO getaStudent() {
		return aStudent;
	}
	public void setaStudent(StudentEO aStudent) {
		this.aStudent = aStudent;
	}
	public List<StudentEO> getAllStudent() {
		return this.allStudent;
	}
	
	public void setAllStudent(List<StudentEO> allStudent) {
		this.allStudent = allStudent;
	}  
	
	//�������һ����ѧ���ķ���
    public String AddStudent() throws Exception  {
    	StudentEO newStu= new StudentEO();
    	newStu.setStudentName(this.stu_Name);
    	newStu.setStudentNum(this.stu_Num);
    	newStu.setTeamNum(this.stu_TeamNum);
    	newStu.setAge(this.stu_Age);
    	newStu.setGender(this.stu_Gender);
    	newStu.setMajor(this.stu_Major);
    	//����EJB�е� addNewStudent��ʵ����Ӳ���
    	op.addNewStudent(newStu);
    	//����ֵΪstudent��ʹҳ�浼����student.xhtml
    	return "student";
    }
	//���ڸ���һ��ѧ����Ϣ�ķ���
    public String UpdateStudent(){
    	//����EJB�е� updateStudent��ʵ����Ӳ���
    	op.updateStudent(this.aStudent);
    	//����ֵΪstudent��ʹҳ�浼����student.xhtml
    	return "student";
    }

	//���ڸ���һ��ѧ����Ϣ�ķ���    
    public String EditStudent(){
    	//����3��������ȡҳ����ͨ����f:param�����ݵĲ��������еġ�params.get����������봫�ݵĲ�����������ƥ��    	
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		String studentNum= params.get("studentNum");
		//����EJB�е� findStudent�����Ҷ�Ӧ��ѧ������
    	this.aStudent=op.findStudent( Integer.valueOf(studentNum) );
    	//����ֵΪeditStudent��ʹҳ�浼����editStudent.xhtml
    	return "editStudent";
    }
    //����ɾ��һ��ѧ����Ϣ�ķ���    
    public String DeleteStudent(){
    	//����3��������ȡҳ����ͨ����f:param�����ݵĲ��������еġ�params.get����������봫�ݵĲ�����������ƥ��    
    	FacesContext fc = FacesContext.getCurrentInstance();
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		String studentNum= params.get("studentNum");		
		//����EJB�е� deleteStudent��ɾ����Ӧ��ѧ����Ϣ
		op.deleteStudent(studentNum);		
		//����ֵΪstudent��ʹҳ�浼����student.xhtml
    	return "student";
    }    
    
  //����JPQLʵ�ֲ�ѯ��ʾ��
    public String QueryStudents(){
    	String sql=" select c from  StudentEO c  where c.studentName like '%" + this.stu_Name + "%' and studentNum like '%" + this.stu_Num +"%' " ;
		//����EJB�е� executeQuery��ִ�й����JPQL��ѯ���    	
    	allStudent=op.executeQuery(sql);
		//����ֵΪstudent��ʹҳ�浼����student.xhtml
    	return "student";
    }
}
