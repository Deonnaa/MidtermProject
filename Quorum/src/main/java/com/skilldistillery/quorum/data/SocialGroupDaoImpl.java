package com.skilldistillery.quorum.data;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.skilldistillery.quorum.entities.SocialGroup;
import com.skilldistillery.quorum.entities.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class SocialGroupDaoImpl implements SocialGroupDAO {
	@PersistenceContext
	private EntityManager em;

	@Override
	public SocialGroup getById(int id) {
		return em.find(SocialGroup.class, id);
	}

	@Override
	public List<SocialGroup> getByUserId(int userId) {
		String jpql = """
				SELECT sg
				FROM SocialGroup sg
				JOIN sg.members m
				WHERE m.id = :userId
				AND sg.enabled = true
				""";
		return em.createQuery(jpql, SocialGroup.class).setParameter("userId", userId).getResultList();
	}

	@Override
	public List<SocialGroup> getAll() {
		return em.createQuery("SELECT sg FROM SocialGroup sg", SocialGroup.class).getResultList();
	}

	@Override
	public boolean addUser(int socialGroupId, int userId) {
		boolean success = false;
		SocialGroup managedGroup = em.find(SocialGroup.class, socialGroupId);
		User user = em.find(User.class, userId);
		if (!managedGroup.getMembers().contains(user)) {
			managedGroup.getMembers().add(user);
			success = true;
		}
		return success;
	}

	@Override
	public boolean removeUser(int socialGroupId, int userId) {
		boolean success = false;
		SocialGroup managedGroup = em.find(SocialGroup.class, socialGroupId);
		User user = em.find(User.class, userId);
		if (user != null && managedGroup.getMembers().contains(user)) {
			managedGroup.getMembers().remove(user);
			success = true;
		}
		return success;

	}

	@Override
	public boolean update(SocialGroup group) {
		boolean success = false;
		SocialGroup managed = em.find(SocialGroup.class, group.getId());
		if (managed != null) {
			managed.setName(group.getName());
			managed.setDescription(group.getDescription());
			managed.setEnabled(group.isEnabled());
		}
		return success;
	}

	@Override
	public SocialGroup create(SocialGroup group, int ownerId) {
		User owner = em.find(User.class, ownerId);
		group.setOwner(owner);
		em.persist(group);
		group.setMembers(Arrays.asList(group.getOwner()));
		return group;
	}

	@Override
	public List<SocialGroup> searchByQuery(String query, User user) {
		query = "%" + query + "%";
		String jpql = """
				SELECT
				    sg
				FROM
				    SocialGroup sg
				WHERE
				    (
				        (sg.name LIKE :query)
				        OR (sg.description LIKE :query)
				    )
				""";
		if (!user.isAdmin()) {
			jpql += """
				AND 
					sg.enabled = true
				AND 
					sg.owner.enabled = true
				AND 
					sg.owner.school.id = 
				""" + user.getSchool().getId();
		}
		
		return em.createQuery(jpql, SocialGroup.class).setParameter("query", query).getResultList();
	}

	@Override
	public boolean userIsOwner(int groupId, int userID) {
		return em.find(SocialGroup.class, groupId).getOwner().getId() == userID;
	}

	@Override
	public boolean userIsInGroup(int groupId, int userId) {
		return em.find(SocialGroup.class, groupId).getMembers().stream().anyMatch(member -> member.getId() == userId);
	}

	@Override
	public void setEnabled(int groupId, boolean status) {
		em.find(SocialGroup.class, groupId).setEnabled(status);
	}

	@Override
	public boolean groupIsAccessable(int groupId, int userId) {
		SocialGroup group = em.find(SocialGroup.class, groupId);
		User user = em.find(User.class, userId);
		return group.isEnabled() || user.getRole().equals("admin");
	}

}
