package tool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class ActionL {
	public abstract String execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception;
}
