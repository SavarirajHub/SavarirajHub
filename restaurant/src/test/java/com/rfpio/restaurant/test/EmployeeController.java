//package com.rfpio.restaurant.test;
//
//import java.util.Objects;
//
//@Restcontroller
//@RequestMapping("employee")
//public class EmployeeController {
//
//	@Autowired
//	ServiceClassInterface interfaceObj;
//	
//	
//	@PostMapping
//	public ResponseEntity<?> createEmployee(@Valid @RequestBody Employee employee) {
//		if(Objects.nonNull(nemployee.getFirstName()) &&  !nemployee.getFirstName().isBlank()) {
//			return new ResponseEntity<Employee> (interfaceObj.save(employee), HTTP.CREATED);
//		}
//		
//		return new ResponseEntity<?> ("Invalid Request Body", HTTP.BAD_REQUEST);
//	}
//	
//	@GetMapping("/{id}")
//	public ResponseEntity<?> findEMployeeById(@PathVariable("id") long id) {
//		return new ResponseEntity<Employee> (interfaceObj.findById(id), HTTP.OK);
//	}
//	
//	@GetMapping()
//	public ResponseEntity<?> findAllEmployees() {
//		return new ResponseEntity<List<Employee>> (interfaceObj.findAll(), HTTP.OK);
//	}
//	
//	@PutMapping("/{id}") 
//	public ResponseEntity<?> updateEmployee(@PathVariable("id") long id, @RequestBody Employee employee) {
//		return new ResponseEntity<Employee> (interfaceObj.updateEmployee(id, employee), HTTP.OK);
//	}
//	
//	@DeleteMapping("/{id}") 
//	public ResponseEntity<?> deleteEmployee(@PathVariable("id") long id){
//		return new ResponseEntity<String>(interfaceObj.deleteById(id), HTTP.OK);
//	}
//}
