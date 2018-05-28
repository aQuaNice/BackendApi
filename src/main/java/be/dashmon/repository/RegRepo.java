package be.dashmon.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import be.dashmon.domain.Register;

@Repository
public interface RegRepo extends PagingAndSortingRepository<Register, Long> {

	Register findByUseremail(String doMember);

	Register findByNotlp(String doPhone);

	Register findByNotlpAndPasscode(String lcnotlp, String lcpasscode);

//	Register findByLcemail(String lcEmail);
//	Register findByLctkn(String lcToken);
//	Register findByApptknAndLcid(String lctoken, Long lcid);
//	String findByLcinvoorderAndLcid(String lcinvoorder, Long lcidmember);
//	Register findByLcnotlp(String doPhone);
//	Register findByLcnotlpAndLcpasscode(String lcnotlp, String lcpasscode);
//	

	Register findByApptknAndId(String lctoken, Long lcid);
	
}
