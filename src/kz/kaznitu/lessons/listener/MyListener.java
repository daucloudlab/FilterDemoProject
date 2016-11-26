package kz.kaznitu.lessons.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.*;
public class MyListener implements ServletContextListener {
    ServletContext ctx;
    Connection con;
    Statement s;
    PreparedStatement ps;
    ResultSet rs;
    int count;
    public void contextInitialized(ServletContextEvent sce) {

        try{
            Class.forName("com.mysql.jdbc.Driver");
            con= DriverManager.
                    getConnection("jdbc:mysql://localhost:3306/listener_test_db",
                            "root", "123");

            s=con.createStatement();
            rs=s.executeQuery("select pageview from counter");
            if(rs.next())
            {
                count=rs.getInt("pageview");
            }

            ctx=sce.getServletContext();
            ctx.setAttribute("pcount", count);


        }
        catch(Exception e){ e.printStackTrace(); }
    }

    public void contextDestroyed(ServletContextEvent sce) {
        try
        {
            ctx=sce.getServletContext();
            count=(Integer)ctx.getAttribute("pcount");
            ps=con.prepareStatement("update counter set pageview='"+count+"'");
            ps.executeUpdate();

            rs.close();
            s.close();
            con.close();
        }
        catch(Exception e){ e.printStackTrace(); }
    }
}
