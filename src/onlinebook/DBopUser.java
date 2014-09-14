package onlinebook;

import java.util.List;

import javaee.jsf.StuEntities.StudentEO;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

//������һ�� ��״̬�ỰBean��ʵ��ʵ��ĸ��ֲ���
@Stateless
public class DBopUser {
	//@PersistenceContext�����Ա�ע�ķ�ʽע��һ��ʵ������������еġ�jsf_example������persistence.xml�ж���ĳ־û���Ԫ������
	@PersistenceContext(unitName = "jsf_example")
	private EntityManager em;

    public DBopUser() {
        
    }
    //������ʵ��BeanStudentEO�ж���Ĳ�ѯ"findAllStudent"��ȡ�����е�ѧ������           	
	public List <UserEO> getAllUser(){
		@SuppressWarnings("unchecked")
        List <UserEO> Users= em.createNamedQuery("findAllUser")
        							.getResultList();
        return Users;
	}	
	public List <MyLog> getAllLog(){
		@SuppressWarnings("unchecked")
        List <MyLog> Users= em.createNamedQuery("findAllLog")
        							.getResultList();
        return Users;
	}	
	public List <UserEO> getUserById(String name){
		@SuppressWarnings("unchecked")
        List <UserEO> Users= em.createNamedQuery("findUserById").setParameter("name", name)
        							.getResultList();
        return Users;
	}	
	//���һ���µ�ѧ����Ϣ
	public void addNewUser(UserEO newUser) {
		em.persist(newUser);
	}
	public void gaiNewUser(UserEO newUser) {
		em.merge(newUser);
	}
	public void updateUser(UserEO Stu){
		em.merge(Stu);
	}
	public List <UserEO> executeQuery(String sql){
		Query query = em.createQuery(sql);
    	List <UserEO> Users= query.getResultList();
    	return Users;
	}	
	//������ʵ��BeanStudentEO�ж���Ĳ�ѯ"findStudentByID"����ѧ�Ų���һ��ѧ��	
		public UserEO findUser(String name){
			@SuppressWarnings("unchecked")
			List <UserEO> tStuList= em.createNamedQuery("findUserById")
	    	.setParameter("name", name).getResultList();
			return tStuList.get(0);    	
		}	
		//ɾ��һ��ѧ����Ϣ	
		public void deleteUser(String name){
			UserEO aStu= findUser( name );
			em.remove(aStu);
		}
		public void addLog(MyLog x)
		{
			em.persist(x);
		}
		//����һ��ѧ����Ϣ	
		//public void updateStudent(StudentEO Stu){
		//	em.merge(Stu);
		//}
}
