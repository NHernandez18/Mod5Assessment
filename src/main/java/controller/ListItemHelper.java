package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.ListItem;

/**
 * Nicholas Hernandez - NHernandez6
 * CIS175 - Fall 2023
 * Oct 20, 2023
 */
public class ListItemHelper {
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Mod5Assessment");
	
	public void insertItem(ListItem	li) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(li);
		em.getTransaction().commit();
		em.close();
	}
	
	public	List<ListItem> showAllItems(){
		EntityManager	em	=	emfactory.createEntityManager();
		List<ListItem> allItems = em.createQuery("SELECT i FROM ListItem i").getResultList();
		return allItems;
		}
	
	public	void	cleanUp(){
		emfactory.close();
		}
	
	public	void	deleteItem(ListItem	toDelete)	{
		EntityManager	em	=	emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListItem>	typedQuery	=	em.createQuery("select	li	from ListItem	li	where	li.model	=	:selectedModel	and	li.os	=	:selectedOs",	ListItem.class);
		//Substitute	parameter	with	actual	data	from	the	toDelete	item
		typedQuery.setParameter("selectedModel",	toDelete.getModel());
		typedQuery.setParameter("selectedOs",	toDelete.getOs());
		//we	only	want	one	result
		typedQuery.setMaxResults(1);
		//get	the	result	and	save	it	into	a	new	list	item
		ListItem	result	=	typedQuery.getSingleResult();
		//remove	it
		em.remove(result);
		em.getTransaction().commit();
		em.close();
		}
	
	public ListItem searchForItemById(int idToEdit) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		ListItem found = em.find(ListItem.class, idToEdit);
		em.close();
		return found;
	}

	public ListItem searchForItemByModel(String model) {
		// TODO Auto-generated method stub
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Mod5Assessment");
		EntityManager manager = factory.createEntityManager();
		TypedQuery<ListItem> query = manager.createQuery("SELECT i FROM ListItem AS i WHERE i.model = :model", ListItem.class);
		query.setParameter("model", model);
		ListItem dbEntity = query.getSingleResult();
		manager.close();
		return dbEntity;
	}
	
	public void update(ListItem model) {
		EntityManager manager = emfactory.createEntityManager();
		ListItem dbEntity = manager.find(ListItem.class, model.getId());
		manager.getTransaction().begin();
		dbEntity.setModel(model.getModel());
		dbEntity.setOs(model.getOs());
		manager.getTransaction().commit();
		manager.close();
	}	
}
