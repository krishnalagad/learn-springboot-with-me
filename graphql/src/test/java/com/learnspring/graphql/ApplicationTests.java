package com.learnspring.graphql;

import com.learnspring.graphql.entity.Department;
import com.learnspring.graphql.repository.DeptRepository;
import com.learnspring.graphql.service.DeptService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.UUID;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
class ApplicationTests {

	@Mock
	private DeptRepository deptRepository;

	@Mock
	private DeptService deptService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testSaveDepartment() {
		Department department = new Department(UUID.randomUUID().toString(), "Development", Instant.now(),
				Instant.now(), List.of()); // Create a sample department
		when(deptRepository.save(department)).thenReturn(department);

		Department savedDepartment = deptService.create(department);

		assertNotNull(savedDepartment);
		verify(deptRepository, times(1)).save(department);
	}

}