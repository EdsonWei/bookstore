package onlinebook;

import java.util.List;
import javaee.jsf.StuEntities.StudentEO;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

//������һ�� ��״̬�ỰBean��ʵ��ʵ��ĸ��ֲ���
@Stateless
public class DBopBook {
	//@PersistenceContext�����Ա�ע�ķ�ʽע��һ��ʵ������������еġ�jsf_example������persistence.xml�ж���ĳ־û���Ԫ������
	@PersistenceContext(unitName = "jsf_example")
	private EntityManager em;

    public DBopBook() {
        
    }
    //������ʵ��BeanStudentEO�ж���Ĳ�ѯ"findAllStudent"��ȡ�����е�ѧ������           	
	public List <BookEO> getAllBook(){
		@SuppressWarnings("unchecked")
        List <BookEO> Students= em.createNamedQuery("findAllBook")
        							.getResultList();
        return Students;
	}	
	//������ʵ��BeanStudentEO�ж���Ĳ�ѯ"findStudentByID"����ѧ�Ų���һ��ѧ��	
	public BookEO findBook(String isbn){
		@SuppressWarnings("unchecked")
		List <BookEO> tStuList= em.createNamedQuery("findBookByIsbn")
    	.setParameter("isbn", isbn).getResultList();
		return tStuList.get(0);    	
	}	
	//���һ���µ�ѧ����Ϣ
	public void addNewBook(BookEO newStu) {
		em.persist(newStu);
	}
	//ɾ��һ��ѧ����Ϣ	
	public void deleteBook(String isbn){
		BookEO aStu= findBook( isbn );
		em.remove(aStu);
	}
	//����һ��ѧ����Ϣ	
	public void updateBook(BookEO Stu){
		em.merge(Stu);
	}
	//ִ��JPQL�Ĳ�ѯ���������ѧ��
	public List <BookEO> executeQuery(String sql){
		Query query = em.createQuery(sql);
		@SuppressWarnings("unchecked")
    	List <BookEO> Students= query.getResultList();
    	return Students;
	}	
}
