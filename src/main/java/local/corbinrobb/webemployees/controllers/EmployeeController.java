package local.corbinrobb.webemployees.controllers;

import local.corbinrobb.webemployees.models.Employee;
import local.corbinrobb.webemployees.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

// Tells spring that this is a REST controller
@RestController
public class EmployeeController {

    private final EmployeeRepository emprepos;

    @Autowired
    private EmployeeController(EmployeeRepository emprepos) {
        this.emprepos = emprepos;
    }

//    http://localhost:9090/employees/all
    @GetMapping(value = "/employees/all", produces = {"application/json"})
    public ResponseEntity<?> listAllEmployees() {
        List<Employee> myList = new ArrayList<>();

//        emprepos.findAll().iterator().forEachRemaining(myList::add);

        for(Employee e : emprepos.findAll()) myList.add(e);

//        Sort by last name
        myList.sort((e1, e2) -> e1.getLname().compareToIgnoreCase(e2.getLname()));

        return new ResponseEntity<>(myList, HttpStatus.OK);
    }

//    http://localhost:9090/employees/name/{startsWithLetter}
    @GetMapping(value = "/employees/name/{startingLetter}", produces = {"application/json"})
    public ResponseEntity<?> listEmployeesStartingWithLetter(@PathVariable String startingLetter) {
        List<Employee> employees = new ArrayList<>();

        for(Employee e : emprepos.findAll()) {
            if(e.getFname().substring(0, 1).equalsIgnoreCase(startingLetter)) {
                employees.add(e);
            }
        }

        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

//    http://localhost:9090/employees/total
    @GetMapping(value = "/employees/total", produces = {"application/json"})
    public ResponseEntity<?> listAllEmployeesSalaryTotal() {
        double salaryTotal = 0;

        for(Employee e : emprepos.findAll()) salaryTotal += e.getSalary();

        return new ResponseEntity<>(salaryTotal, HttpStatus.OK);
    }
}