package be.dashmon.implement;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import be.dashmon.domain.OrderDetail;
import be.dashmon.domain.OrderHead;
import be.dashmon.domain.Register;
import be.dashmon.domain.ResponServ;
import be.dashmon.repository.DetOrderepo;
import be.dashmon.repository.RegRepo;
import be.dashmon.repository.SetOrderRepository;
import be.dashmon.service.DetOrderserv;
import be.dashmon.service.RegService;
import be.dashmon.service.SetOrderService;

@Service
public class DetOrderImpl implements DetOrderserv {

	
	@Autowired
	private DetOrderepo detrepo;

	@Override
	public OrderHead findbyidhead(Long lcitemid) {
		// TODO Auto-generated method stub
		return detrepo.findOne(lcitemid);
	}

//	@Override
//	public List<OrderDetail> Lcidhead(int lcitemid) {
//		return this.detrepo.Idhead(lcitemid);
//	}

//	@Override
//	public Page<DetailTrans> Lcidhead(Pageable pageable,Long lcitemid) {
//		// TODO Auto-generated method stub
//		return this.detrepo.Lcidhead(pageable,lcitemid);
//	}
//	

}
