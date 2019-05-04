/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collegeproject;

import java.io.*;
import java.net.*;
import java.util.*;

class Client 
{
public Socket serv;

    public void request(Vector info) throws IOException, ClassNotFoundException
    {
    System.out.println("Connecting to the server");
    Socket serv = new Socket("127.0.0.1",4747);
    System.out.println("Connected to the server");
        
        ObjectOutputStream Request = new ObjectOutputStream(serv.getOutputStream());
        ObjectInputStream  Response = new ObjectInputStream(serv.getInputStream());
        Request.writeObject(info);
    }
    public void response() throws IOException, ClassNotFoundException
    {
        ObjectOutputStream Request = new ObjectOutputStream(serv.getOutputStream());
        ObjectInputStream  Response = new ObjectInputStream(serv.getInputStream());      
        
        Vector<String> Resp = new Vector<String>();
        Resp = (Vector<String>) Response.readObject();
        
        System.out.println(Resp.get(0));
        
    }
    
    
    
        /*
        Vector mess = new Vector();
        mess.add("Bhagavad gita");
        mess.add("is");
        mess.add("my");
        mess.add("message");
        Request.writeObject(mess);
        
        Vector<String> message = new Vector<String>();
        message = (Vector<String>) Response.readObject();
        for(String value : message)
        {
            System.out.print(value+" ");
        }
        * */   
    }

