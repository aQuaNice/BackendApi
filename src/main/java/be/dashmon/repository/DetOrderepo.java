package be.dashmon.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import be.dashmon.domain.OrderDetail;
import be.dashmon.domain.OrderHead;
//import be.dashmon.domain.DetailTrans;
@Repository

public interface DetOrderepo extends JpaRepository<OrderHead, Long>{

//	Page<DetailTrans> Lcidhead(Pageable pageable, Long lcitemid);

//	List<OrderDetail> Idhead(int lcitemid);



}
