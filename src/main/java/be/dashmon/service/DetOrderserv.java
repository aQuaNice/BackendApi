package be.dashmon.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import be.dashmon.domain.OrderDetail;
import be.dashmon.domain.OrderHead;
import be.dashmon.domain.Register;
import be.dashmon.domain.ResponServ;


public interface DetOrderserv {

	OrderHead findbyidhead(Long lcitemid);
//	Page<DetailTrans> Lcidhead(Pageable pageable,Long lcitemid);

//	List<OrderDetail> Lcidhead(int i);
}
