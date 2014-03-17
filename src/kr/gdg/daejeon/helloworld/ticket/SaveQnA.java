package kr.gdg.daejeon.helloworld.ticket;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.proinlab.util.DBUtil;

@WebServlet("/SaveQnA")
public class SaveQnA extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SaveQnA() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");

		if (request.getSession() == null || request.getSession().getAttribute("user") == null) {
			PrintWriter out = response.getWriter();
			String callBack = request.getParameter("callback");
			out.write(callBack + "(");
			JSONObject json = new JSONObject().put("code", false);
			out.print(json);
			out.print(")");
		} else {
			DBUtil db = new DBUtil();

			PrintWriter out = response.getWriter();
			String callBack = request.getParameter("callback");

			out.write(callBack + "(");
			out.print(new JSONObject().put(
					"code",
					db.sendQuery("INSERT INTO qna(speaker,user,msg) VALUES('" + request.getParameter("speaker") + "','"
							+ request.getSession().getAttribute("user") + "','" + request.getParameter("msg") + "');")));
			out.print(")");
		}
	}
}
