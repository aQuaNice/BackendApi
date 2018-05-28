package be.dashmon.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import be.dashmon.domain.OrderHead;

@Repository
public interface SetOrderRepository extends PagingAndSortingRepository<OrderHead, Integer>{

	OrderHead findByInvoorderAndIdmember(String lcinvoorder, int lcidmember);

//	OrderHead findByLcinvoorderAndLcidmember(String lcinvoorder, Long lcidmember);
//	OrderHead findByLcidmemberAndLcstat(Long lcidmember, int i);
}
