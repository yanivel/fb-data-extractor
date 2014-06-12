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
    @Test
    public void testExtractNoa() {
        System.out.println("extract friends");
        StatExtractor extractor = new StatExtractor();
        extractor.setUser("786753874");
        Set<Class<?>> stats = StatUtility.getAvailableStats();
        extractor.setStats(stats);
        extractor.setTimeout(100);
        List<String> profiles = new ArrayList();
        //profiles.add("100004551300688"); // noa gat
        //profiles.add("1467285958"); // dana elimor
        //profiles.add("100001531730648"); // valery sigal
        //profiles.add("100001558392789"); // valerie mangoni
        //profiles.add("1649032575"); // kiril ragchevsky
        //profiles.add("667859533"); // nadav katz
        profiles.add("1069036242"); // nati levin
        
        extractor.setProfileIds(profiles);
        
        extractor.addParameter(PropertyFriendTaggedMePhoto.class, "tagAmount", "20");
        extractor.addParameter(PropertyMeTaggedFriendPhoto.class, "tagAmount", "20");
        extractor.addParameter(PropertyPrivateMessages.class, "timePeriod", "20");
        extractor.addParameter(PropertyFriendTaggedUserInPost.class, "tagAmount", "20");
        extractor.addParameter(PropertyUserTaggedFriendInPost.class, "tagAmount", "20");
        
        DebugUtility.setDebug(true);
        extractor.extract();
        extractor.printToConsole();
        extractor.saveToCSVFile("nati.csv");
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
}
