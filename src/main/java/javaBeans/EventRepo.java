package javaBeans;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Event;

@Stateless
public class EventRepo {
	
	@PersistenceContext(unitName = "eventunit")
	private EntityManager em;

	public void add(Event event) {
		em.persist(event);
	}

	@SuppressWarnings("unchecked")
	public List<Event> findAll() {
		return (List<Event>) em.createQuery("select e from " + Event.class.getName() + " e").getResultList();
	}
	@SuppressWarnings("unchecked")
	public List<Event> findByCountry(String country){
		return em.createQuery("select e from " + Event.class.getName() + " e where country = '" + country + "'").getResultList();
		
	}
	public Event findOneEvent(int id){
		return em.find(Event.class, id);
	}
}