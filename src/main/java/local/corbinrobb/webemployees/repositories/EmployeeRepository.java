package local.corbinrobb.webemployees.repositories;

import local.corbinrobb.webemployees.models.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

}
