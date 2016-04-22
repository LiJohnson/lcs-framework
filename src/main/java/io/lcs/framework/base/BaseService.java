package io.lcs.framework.base;

import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lcs on 15-5-26.
 * BaseService
 */
public class BaseService extends Base {
	@Autowired
	protected EntityManager entityManager;

	@Autowired
	protected Gson gson;

	/**
	 * 保处理这几个注解
	 */
	private final static String[] PERSIST_ANNOTATION = new String[]{"ID", "COLUMN", "MANYTOONE"};

	protected <T extends BasePojo> Specifications<T> initSearch(final T t) {
		return this.initSearch(t, new String[0]);
	}

	protected <T extends BasePojo> Specifications<T> initSearch(final T t, String... ignoreFileds) {
		Specifications<T> specifications = Specifications.where(null);
		List<String> ignoreFiledList = ignoreFileds == null ? new ArrayList<String>() : Arrays.asList(ignoreFileds);

		final Class<T> clazz = (Class<T>) t.getClass();

		for (final Field field : clazz.getDeclaredFields()) {
			if (!hasPersistAnnotation(field.getAnnotations())) continue;

			String filedName = field.getName();
			Object val = null;
			try {
				//TODO fix bug : bool hideImage;
				val = clazz.getDeclaredMethod(filedName.startsWith("is") ? filedName : "get" + filedName.substring(0, 1).toUpperCase() + filedName.substring(1)).invoke(t);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}

			if (ignoreFiledList.contains(filedName)) continue;

			if (val == null) continue;

			if (field.isAnnotationPresent(Id.class) && (Long) val == 0) continue;

			final Object val2 = val;

			specifications = specifications.and(new Specification<T>() {
				@Override
				public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					if (field.getType() == String.class ) {
						if (StringUtils.isNotBlank(val2.toString())) {
							return cb.like(root.<String>get(field.getName()), val2.toString());
						}
						return null;
					} else {
						return cb.equal(root.<String>get(field.getName()), val2);
					}
				}
			});
		}

		return specifications;
	}

	private boolean hasPersistAnnotation(Annotation[] annotations) {
		for (Annotation annotation : annotations) {
			String name = annotation.annotationType().getSimpleName().toUpperCase();
			for (String n : PERSIST_ANNOTATION) {
				if( n.equals(name) ) return true;
			}
		}
		return false;
	}

	/**
	 * @see io.lcs.framework.utils.AssertExt#hasId(BasePojo, String)
	 * @param basePojo
	 * @return
	 */
	protected static boolean hasId(BasePojo basePojo) {

		return basePojo != null && basePojo.getId() > 0;
	}
}
