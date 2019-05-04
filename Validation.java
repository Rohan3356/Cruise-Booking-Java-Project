package collegeproject;

import java.sql.*;
import java.util.regex.*;
import javax.swing.*;

public class Validation 
{
    


public boolean alphaTest(String TestString)
    {
        String alphaPattern = "[a-zA-Z\\s']+";
        Pattern p = Pattern.compile(alphaPattern);
        Matcher m = p.matcher(TestString);
        return m.matches();
    }
public boolean emailTest(String email) {
        String emailPattern = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern p = Pattern.compile(emailPattern);
        Matcher m = p.matcher(email);
        return m.matches();
    }
public boolean alphaNumTest(String TestString){
    String alphaNumPattern = "^[a-zA-Z0-9]+$";
    Pattern p = Pattern.compile(alphaNumPattern);
    Matcher m = p.matcher(TestString);
    return m.matches();
}
public boolean IsContain(String email, String username)
{
    Connection con =null;
    Statement stmt = null;
     ResultSet rs = null;
     int count1 =0;
     int count2 = 0;
        try{
                //For driver conncetion
		Class.forName("com.mysql.jdbc.Driver");
                //For conncetion
		con = DriverManager.getConnection("jdbc:mysql:8080//localhost/CollegeProject","root","");
		
                //For statement
                String Query = "Select UserName,Email FROM registered";
                stmt = con.createStatement();
                rs = stmt.executeQuery(Query);
               
                while(rs.next())
                {
                    String UserName= rs.getString("UserName");
                    String UserEmail = rs.getString("Email");
                    if(UserName.equals(username))
                    {
                        count1 = count1 +1;
                    }
                    if(UserEmail.equals(email))
                    {
                        count2 = count2 + 1;
                    }
                }

                 
	}
	catch(Exception e)	{
		System.out.println(e);
	}
        finally
        {
           if(stmt != null)
           {
                try
                {
                    stmt.close();
                }
           catch(SQLException e)
             {
               System.out.println(e);
             }
           }     
           if(con != null) 
           {
              {
                  try{
               con.close();
               }
           catch(SQLException e)
           {
               System.out.println(e);
           }   
        }
        
     }
   }
    if(count1 > 0)
    {
    JOptionPane.showMessageDialog(null,"Entered UserName Already exist");
     return false;
     }
    if(count2 > 0)
     {
         JOptionPane.showMessageDialog(null, "Entered email Already");
         return false;
         }
    return true;
}

}

