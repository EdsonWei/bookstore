package javaee.jsf.ejb;

import java.util.List;

import javaee.jsf.StuEntities.StudentEO;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import onlinebook.MyLog;

//������һ�� ��״̬�ỰBean��ʵ��ʵ��ĸ��ֲ���
@Stateless
public class DBop {
	//@PersistenceContext�����Ա�ע�ķ�ʽע��һ��ʵ������������еġ�jsf_example������persistence.xml�ж���ĳ־û���Ԫ������
	@PersistenceContext(unitName = "jsf_example")
	private EntityManager em;

    public DBop() {
        
    }
    //������ʵ��BeanStudentEO�ж���Ĳ�ѯ"findAllStudent"��ȡ�����е�ѧ������           	
	public List <StudentEO> getAllStudent(){
		@SuppressWarnings("unchecked")
        List <StudentEO> Students= em.createNamedQuery("findAllStudent")
        							.getResultList();
        return Students;
	}	
	//������ʵ��BeanStudentEO�ж���Ĳ�ѯ"findStudentByID"����ѧ�Ų���һ��ѧ��	
	public StudentEO findStudent(Integer pStuID){
		@SuppressWarnings("unchecked")
		List <StudentEO> tStuList= em.createNamedQuery("findStudentByID")
    	.setParameter("studentNum", pStuID).getResultList();
		return tStuList.get(0);    	
	}	
	//���һ���µ�ѧ����Ϣ
	public void addNewStudent(StudentEO newStu) {
		em.persist(newStu);
	}
	//ɾ��һ��ѧ����Ϣ	
	public void deleteStudent(String studentNum){
		StudentEO aStu= findStudent( Integer.valueOf(studentNum) );
		em.remove(aStu);
	}
	//����һ��ѧ����Ϣ	
	public void updateStudent(StudentEO Stu){
		em.merge(Stu);
	}
	//ִ��JPQL�Ĳ�ѯ���������ѧ��
	public List <StudentEO> executeQuery(String sql){
		Query query = em.createQuery(sql);
		@SuppressWarnings("unchecked")
    	List <StudentEO> Students= query.getResultList();
    	return Students;
	}	
	public List <MyLog> executeQuerylog(String sql){
		Query query = em.createQuery(sql);
		@SuppressWarnings("unchecked")
    	List <MyLog> Students= query.getResultList();
    	return Students;
	}	
}
