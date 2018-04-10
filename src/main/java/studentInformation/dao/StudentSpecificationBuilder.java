package studentInformation.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import studentInformation.model.Student;

public class StudentSpecificationBuilder {
	
	private final List<SearchCriteria> params;
	
	public StudentSpecificationBuilder(List<SearchCriteria> params) {
		this.params = new ArrayList<SearchCriteria>();
	}

	public StudentSpecificationBuilder with(String key, String operation, Object value) {
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }
	
	public Specification<Student> build() {
        if (params.size() == 0) {
            return null;
        }
 
        List<Specification<Student>> specs = new ArrayList<Specification<Student>>();
        for (SearchCriteria param : params) {
            specs.add(new StudentSpecification(param));
        }
 
        Specification<Student> result = specs.get(0);
        for (int i = 1; i < specs.size(); i++) {
            result = Specifications.where(result).and(specs.get(i));
        }
        return result;
    }
	
	

}
