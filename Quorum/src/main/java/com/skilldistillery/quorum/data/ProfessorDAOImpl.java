package com.skilldistillery.quorum.data;

import java.util.List;

import org.springframework.stereotype.Service;

import com.skilldistillery.quorum.entities.Professor;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProfessorDAOImpl implements ProfessorDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Professor getById(int id) {
		return em.find(Professor.class, id);
	}

	@Override
	public List<Professor> getAllBySchoolId(int id) {
		String jpql = "SELECT p FROM Professor p WHERE school.id = :id";
		return em.createQuery(jpql, Professor.class).setParameter("id", id).getResultList();
	}

	@Override
	public List<Professor> searchByQuery(String query) {
		query = "%" + query + "%";
		String jpql = "SELECT p FROM Professor p WHERE ((firstName LIKE :query) OR (lastName LIKE :query)) AND enabled = true";
		return em.createQuery(jpql, Professor.class).setParameter("query", query).getResultList();
	}

	@Override
	public List<Professor> getAll() {
		return em.createQuery("SELECT p FROM Professor p", Professor.class).getResultList();
	}

	@Override
	public Professor update(Professor professor) {
		Professor managed = em.find(Professor.class, professor.getId());
		managed.setFirstName(professor.getFirstName());
		managed.setLastName(professor.getFirstName());
		managed.setSchool(professor.getSchool());
		managed.setEnabled(professor.isEnabled());
		return professor;
	}

}