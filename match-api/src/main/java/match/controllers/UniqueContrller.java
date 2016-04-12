package match.controllers;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/unique")
public class UniqueContrller {

	private @Autowired EntityManager em;

	/**
	 * 
	 * @param name
	 *            object.property
	 * @param value
	 *            value to be checked
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> checkUnique(@RequestParam("name") String name, @RequestParam("value") String value,
			@RequestParam(defaultValue = "", name = "ignore") String idToIgnore) {

		if (!name.matches("(\\w+\\.{1}\\w+)")) {
			throw new IllegalArgumentException("invalid object.field: \"" + name + "\"");
		}

		String[] split = name.split("\\.");
		String objectName = split[0];
		String propName = split[1];
		boolean ignoreExists = idToIgnore.length() > 0;

		try {
			Query createQuery = em
					.createQuery("select count(o) from match.beans." + StringUtils.capitalize(objectName)
							+ " o where o." + propName + " = :value " + (ignoreExists ? " and id <> :id" : ""))
					.setParameter("value", value);

			if (ignoreExists) {
				createQuery.setParameter("id", Long.valueOf(idToIgnore));
			}

			Long result = (Long) createQuery.getSingleResult();

			log.debug("result {} for {} agianst value {}", (result == 0), name, value);

			return new ResponseEntity<>(result == 0 ? HttpStatus.OK : HttpStatus.FOUND);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
