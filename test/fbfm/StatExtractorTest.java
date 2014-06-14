/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fbfm;

import fbfm.stats.properties.PropertyFriendTaggedMePhoto;
import fbfm.stats.properties.PropertyFriendTaggedUserInPost;
import fbfm.stats.properties.PropertyMeTaggedFriendPhoto;
import fbfm.stats.properties.PropertyPrivateMessages;
import fbfm.stats.properties.PropertyUserTaggedFriendInPost;
import fbfm.util.DebugUtility;
import fbfm.util.StatUtility;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Yaniv Elimor <yaniv.elimor at gmail.com>
 */
public class StatExtractorTest {
    
    public StatExtractorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of extract method, of class StatExtractor.
     */
    //@Test
    public void testExtract() {
        System.out.println("extract");
        StatExtractor extractor = new StatExtractor();
        extractor.setUser("786753874");
        Set<Class<?>> stats = StatUtility.getAvailableFeatures();
        extractor.setStats(stats);
        extractor.setTimeout(100);
        List<String> profiles = new ArrayList(10);
        profiles.add("100001531730648");
        profiles.add("1467285958");
        profiles.add("100001558392789");
        profiles.add("100004551300688");
        
                
        extractor.setProfileIds(profiles);
        
        
        DebugUtility.setDebug(true);
        extractor.extract();
        extractor.printToConsole();
        extractor.saveToCSVFile("features.csv");
        
        extractor.setStats(StatUtility.getAvailableProperties());
        extractor.addParameter(PropertyFriendTaggedMePhoto.class, "tagAmount", "20");
        extractor.addParameter(PropertyMeTaggedFriendPhoto.class, "tagAmount", "20");
        extractor.addParameter(PropertyPrivateMessages.class, "timePeriod", "20");
        extractor.extract();
        extractor.printToConsole();
        extractor.saveToCSVFile("properties.csv");
        boolean expResult = false;
        //boolean result = extractor.extract();
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    /**
     * Test of extract method, of class StatExtractor.
     */
    //@Test
    public void testExtractNoa() {
        System.out.println("extract friends");
        StatExtractor extractor = new StatExtractor();
        extractor.setUser("786753874");
        Set<Class<?>> stats = StatUtility.getAvailableStats();
        extractor.setStats(stats);
        extractor.setTimeout(100);
        List<String> profiles = new ArrayList();
        profiles.add("100004551300688"); // noa gat
        profiles.add("1467285958"); // dana elimor
        profiles.add("100001531730648"); // valery sigal
        //profiles.add("100001558392789"); // valerie mangoni
        //profiles.add("1649032575"); // kiril ragchevsky
        //profiles.add("667859533"); // nadav katz
        //profiles.add("1069036242"); // nati levin
        
        extractor.setProfileIds(profiles);
        
        extractor.addParameter(PropertyFriendTaggedMePhoto.class, "tagAmount", "20");
        extractor.addParameter(PropertyMeTaggedFriendPhoto.class, "tagAmount", "20");
        extractor.addParameter(PropertyPrivateMessages.class, "timePeriod", "20");
        extractor.addParameter(PropertyFriendTaggedUserInPost.class, "tagAmount", "20");
        extractor.addParameter(PropertyUserTaggedFriendInPost.class, "tagAmount", "20");
        
        DebugUtility.setDebug(true);
        extractor.extract();
        extractor.printToConsole();
        extractor.saveToCSVFile("noa_dana_valery_optimized2.csv");
        //boolean expResult = false;
        //boolean result = extractor.extract();
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    
     /**
     * Test of extract method, of class StatExtractor.
     */
    //@Test
    public void testExtractPhotos() {
        System.out.println("extract my photos taggers");
        StatExtractor extractor = new StatExtractor();
        extractor.setUser("786753874");
        
        Set<Class<?>> a = new HashSet<>();
        a.add(PropertyFriendTaggedMePhoto.class);
            
        //Set<Class<?>> stats = StatUtility.getAvailableStats();
        extractor.setStats(a);
        extractor.setTimeout(100);
        List<String> profiles = new ArrayList();
        profiles.add("100004551300688"); // noa gat
        profiles.add("1467285958"); // dana elimor
        profiles.add("100001531730648"); // valery sigal
        profiles.add("100001558392789"); // valerie mangoni
        profiles.add("1649032575"); // kiril ragchevsky
        profiles.add("667859533"); // nadav katz
        profiles.add("1069036242"); // nati levin
        
        extractor.setProfileIds(profiles);
        
        extractor.addParameter(PropertyFriendTaggedMePhoto.class, "tagAmount", "20");
        //extractor.addParameter(PropertyMeTaggedFriendPhoto.class, "tagAmount", "20");
        //extractor.addParameter(PropertyPrivateMessages.class, "timePeriod", "20");
        //extractor.addParameter(PropertyFriendTaggedUserInPost.class, "tagAmount", "20");
        //extractor.addParameter(PropertyUserTaggedFriendInPost.class, "tagAmount", "20");
        
        DebugUtility.setDebug(true);
        extractor.extract();
        extractor.printToConsole();
        extractor.saveToCSVFile("myphototaggers.csv");
        //boolean expResult = false;
        //boolean result = extractor.extract();
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    
    /**
     * Test of extract method, of class StatExtractor.
     */
    @Test
    public void testExtractFriendsProperties() {
        
        int friendsPerRun = 5;
        System.out.println("extract friends");
        StatExtractor extractor = new StatExtractor();
        extractor.setUser("786753874");
        System.out.println("set stats to be all properties.");
        Set<Class<?>> stats = StatUtility.getAvailableProperties();
        extractor.setStats(stats);
        System.out.println("set timeout between calls to 100ms.");
        extractor.setTimeout(100);
        List<String> profiles = new ArrayList();
        int fileName = -1;
        File f = null;
        do {
            fileName++;
            f = new File(fileName + ".csv");
        } while(f.exists());
        System.out.println("writing to file : " + fileName + ".csv");
        int numLinesToIgnore = fileName * friendsPerRun;
        String friendsFileName = "yaniv_all_friends.csv";
        try {
            String line = null;
            
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(friendsFileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            System.out.println("ignoring " + numLinesToIgnore + " lines in friends file.");
            while(numLinesToIgnore != 0) {
                 line = bufferedReader.readLine();
                 numLinesToIgnore--;
                 if (line == null) {
                     break;
                 }
            }
            
            for (int i=0; i<friendsPerRun; i++) {
                line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                String delims = "[,]";
                String[] tokens = line.split(delims);
                profiles.add(tokens[0]); // 0 is ID, 1 is name
            }

            // Always close files.
            bufferedReader.close();			
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                friendsFileName + "'");				
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + friendsFileName + "'");					
            // Or we could just do this: 
            // ex.printStackTrace();
        }
        System.out.println("running stats for " + profiles);        
        extractor.setProfileIds(profiles);
        
        extractor.addParameter(PropertyFriendTaggedMePhoto.class, "tagAmount", "20");
        extractor.addParameter(PropertyMeTaggedFriendPhoto.class, "tagAmount", "20");
        extractor.addParameter(PropertyPrivateMessages.class, "timePeriod", "20");
        extractor.addParameter(PropertyFriendTaggedUserInPost.class, "tagAmount", "20");
        extractor.addParameter(PropertyUserTaggedFriendInPost.class, "tagAmount", "20");
        
        DebugUtility.setDebug(false);
        System.out.println("extracting..");  
        extractor.extract();
        extractor.printToConsole();
        System.out.println("finished. saving to " + fileName + ".csv");
        extractor.saveToCSVFile(fileName + ".csv");
        System.out.println("file saved.");
    }
}
