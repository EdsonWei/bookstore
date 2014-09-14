package onlinebook;

import java.util.List;

import javaee.jsf.StuEntities.StudentEO;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

//������һ�� ��״̬�ỰBean��ʵ��ʵ��ĸ��ֲ���
@Stateless
public class DBopAdmin {
	//@PersistenceContext�����Ա�ע�ķ�ʽע��һ��ʵ������������еġ�jsf_example������persistence.xml�ж���ĳ־û���Ԫ������
	@PersistenceContext(unitName = "jsf_example")
	private EntityManager em;

    public DBopAdmin() {
        
    }
    //������ʵ��BeanStudentEO�ж���Ĳ�ѯ"findAllStudent"��ȡ�����е�ѧ������           	
	public List <AdminEO> getAdminById(String name){
		@SuppressWarnings("unchecked")
        List <AdminEO> Admins= em.createNamedQuery("findAdminById").setParameter("name", name)
        							.getResultList();
        return Admins;
	}	
	public List <BookAdminEO> getAdminByIdbook(String name){
		@SuppressWarnings("unchecked")
        List <BookAdminEO> Admins= em.createNamedQuery("findAdminByIdbook").setParameter("name", name)
        							.getResultList();
        return Admins;
	}	
	public List <UserAdminEO> getAdminByIduser(String name){
		@SuppressWarnings("unchecked")
        List <UserAdminEO> Admins= em.createNamedQuery("findAdminByIduser").setParameter("name", name)
        							.getResultList();
        return Admins;
	}	
	
	
}
