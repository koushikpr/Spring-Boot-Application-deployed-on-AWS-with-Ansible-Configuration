package payroll;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// tag::hateoas-imports[]
// end::hateoas-imports[]

@RestController
class EmployeeController {

	private final EmployeeRepository repository;

	EmployeeController(EmployeeRepository repository) {
		this.repository = repository;
	}

	// Aggregate root

	// tag::get-aggregate-root[]
	@GetMapping("/patients")
	CollectionModel<EntityModel<Patient>> all() {

		List<EntityModel<Patient>> patients = repository.findAll().stream()
				.map(patient -> EntityModel.of(patient,
						linkTo(methodOn(EmployeeController.class).one(patient.getId())).withSelfRel(),
						linkTo(methodOn(EmployeeController.class).all()).withRel("employees")))
				.collect(Collectors.toList());

		return CollectionModel.of(patients, linkTo(methodOn(EmployeeController.class).all()).withSelfRel());
	}
	// end::get-aggregate-root[]

	@PostMapping("/patients")
	Patient newEmployee(@RequestBody Patient newEmployee) {
		return repository.save(newEmployee);
	}

	// Single item

	// tag::get-single-item[]
	@GetMapping("/patients/{id}")
	EntityModel<Patient> one(@PathVariable Long id) {

		Patient patient = repository.findById(id) //
				.orElseThrow(() -> new EmployeeNotFoundException(id));

		return EntityModel.of(patient, //
				linkTo(methodOn(EmployeeController.class).one(id)).withSelfRel(),
				linkTo(methodOn(EmployeeController.class).all()).withRel("patients"));
	}
	// end::get-single-item[]

	@PutMapping("/patients/{id}")
	Employee replaceEmployee(@RequestBody Patient newEmployee, @PathVariable Long id) {

		return repository.findById(id) //
				.map(patient -> {
					patient.setName(newEmployee.getName());
					patient.setContact(newEmployee.getContact());
					return repository.save(patient);
				}) //
				.orElseGet(() -> {
					newEmployee.setId(id);
					return repository.save(newEmployee);
				});
	}

	@DeleteMapping("/patients/{id}")
	void deleteEmployee(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
