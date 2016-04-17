package match.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;

import com.mysema.query.types.Predicate;

import match.beans.BaseEntity;

public abstract class CrudController<T extends BaseEntity, R extends CrudRepository<T, Long> & QueryDslPredicateExecutor<T>> {

	protected abstract R getRepository();

	public ResponseEntity<T> get(Long id) {
		return ResponseEntity.ok(getRepository().findOne(id));
	}

	public ResponseEntity<Void> save(T t) {
		getRepository().save(t);
		return ResponseEntity.ok().build();
	}

	public ResponseEntity<Void> update(T t) {
		getRepository().save(t);
		return ResponseEntity.ok().build();
	}

	public ResponseEntity<Page<T>> query(Predicate predicate, Pageable pageable) {
		return ResponseEntity.ok(getRepository().findAll(predicate, pageable));
	}
	
	public ResponseEntity<Void> delete(Long id) {
		getRepository().delete(id);
		return ResponseEntity.ok().build();
	}
}
