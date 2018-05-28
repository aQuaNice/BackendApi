package be.dashmon.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import be.dashmon.domain.LoginCheck;
//import be.dashmon.domain.Register;

@Repository
public interface LogRepo extends PagingAndSortingRepository<LoginCheck, Long> {

	LoginCheck findByUseremailAndUserpass(String lcemail, String lcpass);

	LoginCheck findByApptkn(String doLctoken);


	LoginCheck findByNotlpAndApptkn(String lcnotlp, String lctkn);


	LoginCheck findByNotlpAndPasscode(String lcnotlp, String lcpasscode);

	LoginCheck findByNotlpAndUserpass(String lcnotlp, String lcpass);

	LoginCheck findByNotlp(String lcnotlp);

//	Register findByLcemail(String lcEmail);

//	LoginCheck findByLcemailAndLcpass(String doMember, String doPass);

//	LoginCheck findByLctkn(String doLctoken);
//
//	LoginCheck findByLcemailAndLcpass(String lcemail, String lcpass);
//
//	LoginCheck findByLcnotlpAndLcpasscode(String lcnotlp, String lcpasscode);
//
//	LoginCheck findByLcnotlpAndLctkn(String lcnotlp, String lctkn);
//
//	LoginCheck findByLcnotlpAndLcpass(String lcnotlp, String lcpass);
//
//	LoginCheck findByLcnotlp(String lcnotlp);
	
}
