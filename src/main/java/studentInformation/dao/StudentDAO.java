package studentInformation.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import studentInformation.model.Student;

@Repository
public class StudentDAO implements StudentRepository  {

	@PersistenceContext
	private EntityManager entityManager;

	public List<Student> searchStudent(List<SearchCriteria> params) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Student> query = builder.createQuery(Student.class);
		Root<Student> r = query.from(Student.class);

		Predicate predicate = builder.conjunction();
		
		for (SearchCriteria param : params) {
            if (param.getOperation().equalsIgnoreCase(">")) {
                predicate = builder.and(predicate, 
                  builder.greaterThan(r.get(param.getKey()), 
                  param.getValue().toString()));
            } else if (param.getOperation().equalsIgnoreCase("<")) {
                predicate = builder.and(predicate, 
                  builder.lessThan(r.get(param.getKey()), 
                  param.getValue().toString()));
            } else if (param.getOperation().equalsIgnoreCase(":")) {
                if (r.get(param.getKey()).getJavaType() == String.class) {
                    predicate = builder.and(predicate, 
                      builder.like(r.get(param.getKey()), 
                      "%" + param.getValue() + "%"));
                } else {
                    predicate = builder.and(predicate, 
                      builder.equal(r.get(param.getKey()), param.getValue()));
                }
            }
        }

		query.where(predicate);

		List<Student> result = entityManager.createQuery(query).getResultList();
		return result;
	}

	@Override
	public <S extends Student> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Student> Iterable<S> save(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student findOne(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exists(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<Student> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Student> findAll(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Student entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Iterable<? extends Student> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Student> findByLastName(String lastName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> findBystudentIdIn(List<Integer> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> findBystudentDOBBefore(Date studentDOB) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> findBystudentDOBBeforeAndFirstName(Date studentDOB, String firstName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> findStudentsByCityId(int city_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> search(String fatherName, String lastName, String firstName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student findOne(Specification<Student> spec) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> findAll(Specification<Student> spec) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Student> findAll(Specification<Student> spec, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> findAll(Specification<Student> spec, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count(Specification<Student> spec) {
		// TODO Auto-generated method stub
		return 0;
	}

}
