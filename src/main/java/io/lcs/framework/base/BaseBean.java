package io.lcs.framework.base;

import java.lang.reflect.Field;

/**
 * Created by lcs on 11/18/15.
 */
public class BaseBean extends Base  {

	/**
	 * 获取一个属性的值
	 *
	 * @param fieldName
	 * @return
	 */
	private Object fieldValue(String fieldName) {
		try {
			return this.getClass().getDeclaredMethod("get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1), (Class<?>[]) null).invoke(this, (Object[]) null);
		} catch (Exception e) {
			return "error";
		}
	}

	/**
	 * 打印一个实例
	 * 格式: fieldName		value		type
	 *
	 * @see Object#toString()
	 */
	@Override
	public String toString() {
		return this.toString("");
	}

	private String toString(String tab) {
		if( tab.length() > 3 ) return this.getClass().getSimpleName();

		StringBuffer buf = new StringBuffer();
		buf.append("\n" + tab + "<pre>-------------------start---------------------- \n");
		buf.append(tab + "class Name \t\t" + this.getClass().getSimpleName() + "\n");
		Field[] fields = this.getClass().getDeclaredFields();
		for (Field field : fields) {
			String name = field.getName();
			Object object = this.fieldValue(name);
			if (object instanceof BaseBean) {
				object = ((BaseBean) object).toString(tab + "\t");
			}
			buf.append(tab + name + "\t\t" + field.getType().getSimpleName() + "\t\t" + (object == null ? "null" : object.toString()) + "\n");
		}
		buf.append(tab + "-------------------end  ----------------------</pre>\n");
		return buf.toString();
	}
}
