package collegeproject;
import java.net.*;
import java.io.*;
import java.sql.*;
import java.util.Vector;

class ServiceThread extends Thread
{
    Socket sock;
    
    public ServiceThread(Socket sock)
    {
        this.sock = sock;
    }
    public void run()
    {
        try
        {
            //For Passing Array
            ObjectInputStream  Request = new ObjectInputStream(sock.getInputStream());
            ObjectOutputStream Response = new ObjectOutputStream(sock.getOutputStream());
            
            // Getting request in message
            // Vector message = new Vector();
            Vector<String> Req = new Vector<String>(4, 1);
            Req = (Vector<String>) Request.readObject();
            String a = Req.get(0);
            
            
            Vector <String>Resp = new Vector<String>();
            
            switch(a)
                    {
                        case "Register":
                            Connection con =null;
                            PreparedStatement pstmt = null;
                            ResultSet rs = null;
                            String FirstName = Req.get(1);
                            String LastName = Req.get(2);
                            String Email = Req.get(3);
                            String CabinNum = Req.get(4);
                            String UserName = Req.get(5);
                            String UserPassword = Req.get(6);
        
                            try{
                                    //For driver conncetion
                                Class.forName("com.mysql.jdbc.Driver");
                                //For conncetion
                                con = DriverManager.getConnection("jdbc:mysql://localhost:8080/CollegeProject","root","");
		
                                 //For statement
                                String Query = "insert into Regiseter (FirstName, LastName, email, CabinNum, UserName, UserPassword)"
                                + " values (?, ?, ?, ?, ?,?)";
                                pstmt = con.prepareStatement(Query);
                                    
                
                            pstmt.setString(1,FirstName);
                            pstmt.setString(2,LastName);
                            pstmt.setString(3, Email);
                            pstmt.setInt(4, Integer.parseInt(CabinNum));
                            pstmt.setString(5, UserName);
                            pstmt.setString(6,UserPassword);
                
                            pstmt.execute();
                            System.out.println("Query Executed");
                            Resp.add("Request not connected!!!");
                            Response.writeObject(Resp);
                
      
                 
	}
	catch(Exception e)	{
		System.out.println(e);
                Resp.add("Request not connected!!!");
                Response.writeObject(Resp);
	}
        finally
        {
           if(pstmt != null)
           {
                try
                {
                    pstmt.close();
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
                            
                            
                        break;
                        default:
                            System.out.println("invalid");
                   }
            
            
            /*
            //Sending response to the clint
            Vector message2 = new Vector();
            message2.add(a);
            message2.add("is");
            message2.add("your");
            message2.add("message");
            Response.writeObject(message2);
            **/
            
            
        }
        catch(Exception e)
        {
            
        }
            
    }
}
public class Server {
    
    public static void main(String[] args) throws IOException{
        ServerSocket servSocket = new ServerSocket(4747);
        System.out.println("Server is initiated");
        
        while(true)
        {
            System.out.println("Waiting for client to port 4747");
            Socket server = servSocket.accept(); 
            
            //Create threads for multiple clients
            System.out.println("Creating multiple thread for multiple users");
            new ServiceThread(server).start();
            
        }
    }
}
