package be.dashmon.service;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import be.dashmon.domain.GetparamDetail;
import be.dashmon.domain.OrderHead;
import be.dashmon.domain.Register;
import be.dashmon.domain.ResponServ;



public interface SetOrderService {
//	OrderHead findByLcinvoorderAndLcidmember(String lcinvoorder, Long lcidmember);
//	Register findByLctknAndLcid(String lctoken, int lcidmember);
	void orderCancel(GetparamDetail lcdata, ResponServ respon);
	void saveOrder(OrderHead lcdata, ResponServ respon);
	void GetdataOrder(GetparamDetail lcdata, ResponServ respon);
	OrderHead findByLcinvoorderAndLcidmember(String lcinvoorder, int lcidmember);
	Register findByLctknAndLcid(String lctoken, Long lcidmember);
}

