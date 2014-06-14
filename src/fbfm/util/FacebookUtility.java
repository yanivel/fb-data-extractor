/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fbfm.util;

import com.google.common.base.Joiner;
import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.User;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author Yaniv Elimor <yaniv.elimor at gmail.com>
 */
public class FacebookUtility {
    static protected String facebookAccessToken = "CAACEdEose0cBAIMSsFnKIijIUk545CB9GjwkKq3yXJd1rLHKPefygjhJ3qDpLtBpUu1g3KPB9qWe0LO0js4iL5lNzZAEjsZCGpg3emDUWLAFi7XpLZAn2HZAsl6upejZCqyq0cFQTfftW9Ovt2iGGD20DavqMeoKvlqkdZAmzdqRPJKCZCkpLga7J0yU26aCzR1VSyxUXjasgZDZD";
    
    static protected FacebookClient facebookClient = null;
    
    private FacebookUtility() {
    
    }
    
    public static String getAccessToken() {
        return FacebookUtility.facebookAccessToken;
    }
    
    public static FacebookClient getFacebookClient() {
        if (FacebookUtility.facebookClient == null) {
            FacebookUtility.facebookClient = new DefaultFacebookClient(FacebookUtility.getAccessToken());
        }
        
        return FacebookUtility.facebookClient;
    }
    
    public static int AllFriendsToCSV(String filename) {
        FacebookClient client = FacebookUtility.getFacebookClient();
        
        Connection<User> friends = client.fetchConnection("me/friends", User.class, Parameter.with("fields", "id,name"));
        
        int numFriends = 0;
        try
	{
	    FileWriter writer = new FileWriter(filename);
          
            for (List<User> friendList : friends) {
                for (User friend : friendList) {
                    numFriends++;
                    writer.append(friend.getId()+ "," + friend.getName() + "\n");
                }
            }
            writer.flush();
	    writer.close();
	}catch(IOException e)
	{
	     e.printStackTrace();
	}
        
        return numFriends;
    }
    
}
