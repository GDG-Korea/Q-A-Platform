package kr.gdg.daejeon.helloworld.ticket;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import com.proinlab.util.DBUtil;

@WebServlet("/LoadQnA")
public class LoadQnA extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoadQnA() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");

		DBUtil db = new DBUtil();

		PrintWriter out = response.getWriter();
		String callBack = request.getParameter("callback");

		String speaker = request.getParameter("speaker");
		JSONArray result;
		if (speaker.equals("all"))
			result = db.select("select speaker, user, msg from qna order by date desc;", new String[] { "speaker", "user", "msg" });
		else
			result = db.select("select speaker, user, msg from qna where speaker='" + speaker + "' order by date desc;", new String[] { "speaker", "user", "msg" });

		out.write(callBack + "(");
		out.print(result);
		out.print(")");
	}
}
