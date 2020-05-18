package dao.MemberDAO;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Service;
import dao.IDao;
import model.Member;

@Service
public class MemberDao implements IDao<Member>{

	private static Session session = IDao.getSession();
	
	public static void main(String[] args) {
		String sql = "select * from member";
		NativeQuery<Member> query = session.createNativeQuery(sql, Member.class);
		query.setFirstResult(10);
		query.setMaxResults(10);
		List<Member> members = query.list();
		for(Member m: members)
			System.out.println(m.getId());
	}
	
	@Override
	public List<Member> findAll() {
		String sql = "select * from member";
		NativeQuery<Member> query = session.createNativeQuery(sql, Member.class);
		List<Member> members = query.list();
		
		return members;
	}

	@Override
	public Member findOne(Integer id) {
		Member member = session.get(Member.class, id);
		if (member != null) return member;
		
		return null;
	}

	@Override
	public Member save(Member obj) {
		if (obj == null) return null;
		
		session.beginTransaction();
		session.save(obj);
		session.getTransaction().commit();
		return obj;
	}

	@Override
	public Member update(Integer id, Member obj) {
		if (obj == null || id < 1 || session.get(Member.class, id) == null)  
			return null;
		
		session.beginTransaction();
		session.clear();
		obj.setId(id);
		session.update(obj);
		session.getTransaction().commit();
		return obj;
	}

	@Override
	public boolean delete(Integer id) {
		Member member = session.get(Member.class, id);
		if(member != null) {
			session.beginTransaction();
			session.delete(member);
			session.getTransaction().commit();
			return true;
		}
		
		return false;
	}

}
