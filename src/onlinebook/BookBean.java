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
public class BookBean {
	//����һЩ���������洢JSFҳ���е�����
	String isbn;
	String author;
	String pub;
	float price;
	String type;
    BookEO aBook;
    List<BookEO> allBook = null;
    
    //ע���������ݿ��������״̬�ỰBean
    @EJB
	DBopBook op;
   
   
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
	public BookEO getaBook() {
		return aBook;
	}
	public void setaBook(BookEO aBook) {
		this.aBook = aBook;
	}
	public List<BookEO> getAllBook() {
		return allBook;
	}
	public void setAllBook(List<BookEO> allBook) {
		this.allBook = allBook;
	}
	public DBopBook getOp() {
		return op;
	}
	public void setOp(DBopBook op) {
		this.op = op;
	}
	//�������һ����ѧ���ķ���
    public String AddBook() throws Exception  {
    	BookEO b= new BookEO();
    	b.setIsbn(isbn);
    	b.setAuthor(author);
    	b.setPub(pub);
    	b.setPrice(price);
    	b.setType(type);
    	//����EJB�е� addNewStudent��ʵ����Ӳ���
    	op.addNewBook(b);
    	
    	List<MyLog> logs=opuser.getAllLog();
		Integer intid=(logs.size()+1);
		String logid=intid.toString();
		MyLog logtemp=new MyLog();
    	String datee=date();
    	logtemp.setDate(datee);
        logtemp.setId(logid);
        logtemp.setOp("��");
        logtemp.setType("ͼ�����Ա");
        logtemp.setUser(quanju.name);
        opuser.addLog(logtemp);
    	QueryBooks();
    	//����ֵΪstudent��ʹҳ�浼����student.xhtml
    	return "/book/student";
    }
	//���ڸ���һ��ѧ����Ϣ�ķ���
    public String UpdateBook(){
    	//����EJB�е� updateStudent��ʵ����Ӳ���
    	op.updateBook(this.aBook);
    	List<MyLog> logs=opuser.getAllLog();
		Integer intid=(logs.size()+1);
		String logid=intid.toString();
		MyLog logtemp=new MyLog();
    	String datee=date();
    	logtemp.setDate(datee);
        logtemp.setId(logid);
        logtemp.setOp("��");
        logtemp.setType("ͼ�����Ա");
        logtemp.setUser(quanju.name);
        opuser.addLog(logtemp);
    	//����ֵΪstudent��ʹҳ�浼����student.xhtml
    	QueryBooks();
    	return "/book/student";
    }

	//���ڸ���һ��ѧ����Ϣ�ķ���    
    public String EditBook(){
    	//����3��������ȡҳ����ͨ����f:param�����ݵĲ��������еġ�params.get����������봫�ݵĲ�����������ƥ��    	
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		String studentNum= params.get("isbn");
		//����EJB�е� findStudent�����Ҷ�Ӧ��ѧ������
    	this.aBook=op.findBook( studentNum );
    	//����ֵΪeditStudent��ʹҳ�浼����editStudent.xhtml
    	QueryBooks();
    	return "/book/editStudent";
    }
    //����ɾ��һ��ѧ����Ϣ�ķ���    
    public String DeleteBook(){
    	//����3��������ȡҳ����ͨ����f:param�����ݵĲ��������еġ�params.get����������봫�ݵĲ�����������ƥ��    
    	FacesContext fc = FacesContext.getCurrentInstance();
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		String studentNum= params.get("isbn");		
		//����EJB�е� deleteStudent��ɾ����Ӧ��ѧ����Ϣ
		op.deleteBook(studentNum);		
		//����ֵΪstudent��ʹҳ�浼����student.xhtml
		QueryBooks();
		
		List<MyLog> logs=opuser.getAllLog();
		Integer intid=(logs.size()+1);
		String logid=intid.toString();
		MyLog logtemp=new MyLog();
    	String datee=date();
    	logtemp.setDate(datee);
        logtemp.setId(logid);
        logtemp.setOp("ɾ");
        logtemp.setType("ͼ�����Ա");
        logtemp.setUser(quanju.name);
        opuser.addLog(logtemp);
    	return "/book/student";
    }    
    
  //����JPQLʵ�ֲ�ѯ��ʾ��
    public String QueryBooks(){
    	String sql=" select c from  BookEO c  "  ;
		//����EJB�е� executeQuery��ִ�й����JPQL��ѯ���    	
    	allBook=op.executeQuery(sql);
		//����ֵΪstudent��ʹҳ�浼����student.xhtml
    	return "/book/student";
    }
    @EJB
	DBopUser opuser;
    public String date(){   
        String temp_str="";   
        Date dt = new Date();   
        //����aa��ʾ�����硱�����硱    HH��ʾ24Сʱ��    �������hh��ʾ12Сʱ��   
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss aa");   
        temp_str=sdf.format(dt);   
        return temp_str;   
    } 
    public String QueryBooks2(){
    	String sql=" select c from  BookEO c  "  ;
		//����EJB�е� executeQuery��ִ�й����JPQL��ѯ���    	
    	allBook=op.executeQuery(sql);
    	
    	List<MyLog> logs=opuser.getAllLog();
		Integer intid=(logs.size()+1);
		String logid=intid.toString();
		MyLog logtemp=new MyLog();
    	String datee=date();
    	logtemp.setDate(datee);
        logtemp.setId(logid);
        logtemp.setOp("��");
        logtemp.setType("ͼ�����Ա");
        logtemp.setUser(quanju.name);
        opuser.addLog(logtemp);
		//����ֵΪstudent��ʹҳ�浼����student.xhtml
    	return "/book/student";
    }
}
