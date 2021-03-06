package servlet;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * Created by Thpffcj on 2017/12/10.
 */
@WebServlet(name = "AsynServlet", urlPatterns = {"/index1.jsp"}, asyncSupported = true)
public class AsynServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
        out.println("<HTML>");
        out.println("  <HEAD><TITLE>A AsynServlet</TITLE></HEAD>");
        out.println("  <BODY>");
        out.print("    This is ");
        out.print(this.getClass());
        out.println(", using the POST method");
        out.println("  </BODY>");
        out.println("</HTML>");
        out.flush();
        out.close();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Servlet执行开始时间:"+new Date());
        AsyncContext context =  request.startAsync();
        new Thread(new Executor(context)).start();
        System.out.println("Servlet执行结束时间:"+new Date());
    }

    public class Executor implements Runnable{

        private AsyncContext context;
        public Executor(AsyncContext context ) {
            this.context = context;
        }

        public void run() {
            // 执行相关复杂业务
            try {
                Thread.sleep(1000*10);
//				context.getRequest();
//				context.getResponse();
                System.out.println("业务执行完成时间:" + new Date());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
