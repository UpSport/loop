package upsport.loop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Connector {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        String url = "jdbc:mysql://162.216.17.47:3306/loop";
        String user = "root";
        String password = "600191";

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();
             rs = st.executeQuery("SELECT * FROM event");
            
             if (rs.next()) {
                 System.out.println(rs.getString(1));
             }

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Connector.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(Connector.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
    }
}
