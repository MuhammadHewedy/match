package match.beans.repos;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import match.beans.User;

public interface UserRepo extends PagingAndSortingRepository<User, Long>, QueryDslPredicateExecutor<User> {

}
