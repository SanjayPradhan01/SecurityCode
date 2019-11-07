package com.dbs.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.entity.BookEntity;
import com.dbs.entity.StudentEntity;
import com.dbs.model.BookDTO;
import com.dbs.model.StudentDTO;
import com.dbs.repository.StudentRepository;

import swagger.codegen.api.EmployeeApi;
import swagger.codegen.api.model.Employee;

@RestController
public class EmployeeController implements EmployeeApi {
	
	@Autowired
	StudentRepository studentRepository;

	//@PreAuthorize("hasRole('ROLE_admins')")
	@Override
	public ResponseEntity<Employee> getEmployeeById(Integer empId) {
		Employee emp = new Employee();
		Optional<StudentEntity> entity = studentRepository.findById(empId);
		if(entity.isPresent()){
			StudentEntity stu1 = entity.get();
			emp = new Employee();
			emp.setFirstName(stu1.getName());
			emp.setEmpFullName("Sanjay pradhan");
			emp.setContact("9121003558");
			emp.setId(stu1.getId());
			emp.setEmail("skp.pradhan1989@gmail.com");
			emp.setAddress("Hyderabad");
			System.out.println("--------" + stu1.getBooks().size());
		}
		
		return new ResponseEntity<>(emp, HttpStatus.OK);
	}
	
	@Override
	public  ResponseEntity<String> addEmployee(Employee body) {
		StudentEntity stu = new StudentEntity();
		stu.setName("Deepika");
		stu.setPassport("4545454");
		stu.setId(body.getId());
		BookEntity book = new BookEntity();
		book.setBookname("Oriya");
		book.setBookid(body.getId());
		book.setStudent(stu);
		BookEntity book1 = new BookEntity();
		book1.setBookname("History");
		book1.setBookid(body.getId() +1);
		book1.setStudent(stu);
		stu.getBooks().add(book);
		stu.getBooks().add(book1);
		
		studentRepository.save(stu);
		return new ResponseEntity<>("Student added successfully", HttpStatus.OK);
	}
	
	@GetMapping("/retrieveall")
	public  ResponseEntity<List<StudentDTO>> retrieveAllEmployee() {
		List<StudentEntity> entity = studentRepository.findAll();
		List<StudentDTO> dtos = new ArrayList<>();
		for(StudentEntity e : entity){
			StudentDTO stu = new StudentDTO();
			stu.setId(e.getId());
			stu.setName(e.getName());
			stu.setPassport(e.getPassport());
			
			List<BookEntity> be = e.getBooks();
			for (BookEntity bookEntity : be) {
				BookDTO bdto = new BookDTO();
				bdto.setBookid(bookEntity.getBookid());
				bdto.setBookname(bookEntity.getBookname());
				stu.getBooks().add(bdto);
			}
			dtos.add(stu);
		}
		
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
}
