package match.beans.repos;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import match.beans.Applicant;

public interface ApplicantRepo
		extends PagingAndSortingRepository<Applicant, Long>, QueryDslPredicateExecutor<Applicant> {

}
