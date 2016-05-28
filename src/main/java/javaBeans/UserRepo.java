package javaBeans;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.User;

@Stateless
public class UserRepo {

	@PersistenceContext(unitName = "eventunit")
	private EntityManager em;

	public void add(User user) {
		em.persist(user);
	}

	@SuppressWarnings("unchecked")
	public List<User> findAll() {
		return (List<User>) em.createQuery("select u from " + User.class.getName() + " u").getResultList();
	}
	
	public boolean findOne(String username){
		return em.find(User.class, username) != null ? false : true;
	}
	public User getUser(String username){
		return em.find(User.class, username);
	}
}
