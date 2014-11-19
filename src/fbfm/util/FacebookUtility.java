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
 * The facebook utility where you can get a token from and other functions
 *
 * @author Yaniv Elimor <yaniv.elimor at gmail.com>
 */
public class FacebookUtility {
    
    
    // this is the token - needs to be updated manually unless you implement a way to get one as a user :)
    static protected String facebookAccessToken = "CAACEdEose0cBAErcHWyvN6WRV87vBnz2dbSE47mmVKjA6mjZB7NzWWYh4YJToug9oQIdN0retvS4O35vq60T9rZBHLqoEP39m8DDNFjxNAM81GerfZCzzyNUeMwTyEpM7xeE3wFFc6IXqDXBOCZCt6tmd8T2cwKBdZBUt60aNtEAGl16Y2UZA7HOvTDt67GCmRRDOL5wuWBgZDZD";
    
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
    
    /**
     * 
     * get the current user's friends into a csv file
     * 
     * @param filename
     * @return number of friends
     */
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
