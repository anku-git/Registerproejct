package com.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.entity.Register;

import jakarta.transaction.Transactional;

public interface RegisterRepository extends CrudRepository<Register, Integer> {
	Optional<Register> findByTicketNo(String ticketNo);

	@Transactional
	@Modifying
	@Query(value = "update Register c set c.status = 'closed' WHERE c.id= :id", nativeQuery = true)
	void closeComplaintStatus(@Param("id") Integer id);

	Page<Register> findAll(Pageable pageable);

}
