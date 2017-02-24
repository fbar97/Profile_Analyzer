import static org.junit.Assert.*; 

import java.io.BufferedReader; 
import java.io.FileReader; 
import java.io.InputStreamReader; 
import java.util.ArrayList; 
import java.util.List; 

import org.junit.Test; 


public class UnitTests { 
     
    public static int passed = 0; 

    @Test 
    public void testPhoto() { 
        Photo p = new Photo("kitten.jpg", 234, "C:\\MyPhotos\\kitten.jpg"); 
        assertEquals("kitten.jpg",p.getName()); 
        assertEquals(234,p.getSize()); 
        assertEquals("C:\\MyPhotos\\kitten.jpg",p.getLocation()); 
        assertNotEquals(0,++passed);         
    } 
     
    @Test 
    public void testPost() { 
        Post p = new Post("03/23/2016","It was as sunny day today!"); 
        assertEquals("03/23/2016",p.getDate()); 
        assertEquals("It was as sunny day today!",p.getText()); 
        assertEquals(null,p.getPhoto()); 
         
        Photo photo = new Photo("kitten.jpg", 234, "C:\\MyPhotos\\kitten.jpg"); 
        p.addPhoto(photo); 
        assertEquals(photo,p.getPhoto()); 
        assertNotEquals(0,++passed);         
    }     
     
    @Test 
    public void testMessage(){ 
        Message m = new Message("Jane Smith", "03/23/2016", "How did your class go?"); 
        assertEquals("Jane Smith", m.getContact()); 
        assertEquals("03/23/2016", m.getDate()); 
        assertEquals("How did your class go?", m.getBody()); 
        assertNotEquals(0,++passed);         
    } 
     
    @Test 
    public void testNegativeAnalysis(){ 
        String text = "This professor is really boring and careless. I don't like her at all."; 
        ArrayList<String> result = Message.getNegativeKeywords(text); 
        assertEquals("boring", result.get(0)); 
        assertEquals("careless", result.get(1)); 
        assertNotEquals(0,++passed);         
    } 
     
    @Test 
    public void testPositiveAnalysis(){ 
        String text = "This professor is really compassionate and nice. I think she's good!"; 
        ArrayList<String> result = Message.getPositiveKeywords(text); 
        assertEquals("compassionate", result.get(0)); 
        assertEquals("good", result.get(1));     
        assertEquals("nice", result.get(2)); 
        assertNotEquals(0,++passed);         
    }     
     
    @Test 
    public void testProfile1(){ 
        Profile p = new Profile("John Smith","male"); 
        assertEquals("John Smith", p.getUsername()); 
        assertEquals("male", p.getGender()); 
        assertNotEquals(0,++passed);         
    } 
     
    @Test 
    public void testProfile2(){ 
        Profile p = new Profile("John Smith","male");         
        String[] lines = {"http://www.cs.gmu.edu/~kdobolyi/headshot.jpg 223"}; 
        String exceptionType = "NONE";         
        try{ 
            p.parseDataDump(lines); 
        }catch (Exception e){ 
            exceptionType = e.getClass().toString(); 
        } 
        System.out.println(p.getPhotos());
        assertEquals(223, (p.getPhotos().get(0)).getSize()); 
        assertEquals("http://www.cs.gmu.edu/~kdobolyi/headshot.jpg", (p.getPhotos().get(0)).getLocation());     
        assertEquals("headshot.jpg", (p.getPhotos().get(0)).getName());     
        assertEquals(1,p.getPhotos().size()); 
        assertEquals("NONE",exceptionType);         
        assertNotEquals(0,++passed);                 
    } 
     
    @Test 
    public void testProfile3(){ 
        Profile p = new Profile("John Smith","male");         
        String[] lines = {"http://www.cs.gmu.edu/~kdobolyi/headshot.jpg223"}; 
        String exceptionType = "NONE"; 
        try{ 
            p.parseDataDump(lines); 
        }catch (Exception e){ 
            exceptionType = e.getClass().toString(); 
        } 
         
        assertNotEquals("NONE",exceptionType); 
        assertNotEquals(0,++passed);         
    }     

    @Test 
    public void testProfile4(){ 
        Profile p = new Profile("John Smith","male");         
        String[] lines = {"CONTACT Jane Doe"}; 
        String exceptionType = "NONE";         
        try{ 
            p.parseDataDump(lines); 
        }catch (Exception e){ 
            exceptionType = e.getClass().toString(); 
        } 
         
        assertEquals("Jane Doe", (p.getContacts().get(0))); 
        assertEquals("NONE",exceptionType);             
        assertNotEquals(0,++passed);         
    } 
     
    @Test 
    public void testProfile5(){ 
        Profile p = new Profile("John Smith","male");                 
        String[] lines = {"POST#12/23/15#my message#http://www.cs.gmu.edu/~kdobolyi/headshot.jpg 223"}; 
        String exceptionType = "NONE"; 
        try{ 
            p.parseDataDump(lines); 
        }catch (Exception e){ 
            exceptionType = e.getClass().toString(); 
        } 
     
        assertEquals("NONE",exceptionType);             
        assertEquals("12/23/15", ((Post)p.getPosts().get(0)).getDate()); 
        assertEquals("my message", ((Post)p.getPosts().get(0)).getText());         
        assertEquals("http://www.cs.gmu.edu/~kdobolyi/headshot.jpg", (p.getPosts().get(0)).getPhoto().getLocation());         
        assertEquals("http://www.cs.gmu.edu/~kdobolyi/headshot.jpg", (p.getPhotos().get(0)).getLocation());             
        assertNotEquals(0,++passed); 
    }         
     
    @Test 
    public void testProfile6(){ 
        Profile p = new Profile("John Smith","male");                 
        String[] lines = {"POST 12/23/15 my message http://www.cs.gmu.edu/~kdobolyi/headshot.jpg 223"}; 
        String exceptionType = "NONE"; 
        try{ 
            p.parseDataDump(lines); 
        }catch (Exception e){ 
            exceptionType = e.getClass().toString(); 
        } 
     
        assertNotEquals("NONE",exceptionType);                     
        assertNotEquals(0,++passed); 
    } 
     
    @Test 
    public void testProfile7(){ 
        Profile p = new Profile("John Smith","male");                 
        String[] lines = {"MESSAGE#Jane Doe#12/23/15#my message is short"}; 
        String exceptionType = "NONE"; 
        try{ 
            p.parseDataDump(lines); 
        }catch (Exception e){ 
            exceptionType = e.getClass().toString(); 
        } 
     
        assertEquals("NONE",exceptionType);             
        assertEquals("Jane Doe", (p.getMessages().get(0)).getContact());         
        assertEquals("12/23/15", ((p.getMessages().get(0)).getDate())); 
        assertEquals("my message is short", ((Message)p.getMessages().get(0)).getBody());         
        assertEquals("Jane Doe", ((String)p.getContacts().get(0)));         
        assertNotEquals(0,++passed); 
    }         
     
    @Test 
    public void testProfile8(){ 
        Profile p = new Profile("John Smith","male");     
        Photo photo = new Photo("kitten.jpg", 234, "C:\\MyPhotos\\kitten.jpg"); 
        p.addObject(photo); 
        assertEquals(photo,p.getPhotos().get(0)); 
        assertNotEquals(0,++passed);         
    } 
     
    @Test 
    public void testProfile9(){ 
        Profile p = new Profile("John Smith","male");     
        Post post = new Post("03/23/2016","It was as sunny day today!"); 
        p.addObject(post); 
        assertEquals(post,(Post)p.getPosts().get(0)); 
        assertNotEquals(0,++passed);         
    }     
     
    @Test 
    public void testProfile10(){ 
        Profile p = new Profile("John Smith","male");     
        Message m = new Message("Jane Smith", "03/23/2016", "How did your class go?"); 
        p.addObject(m); 
        assertEquals(m,(Message)p.getMessages().get(0)); 
        assertNotEquals(0,++passed);         
    }     
     
    @Test 
    public void testProfile11(){ 
        Profile p = new Profile("John Smith","male");     
        String contact = "Jane"; 
        p.addObject(contact); 
        assertEquals(contact,(String)p.getContacts().get(0)); 
        assertNotEquals(0,++passed);         
    }         
     
    @Test 
    public void testProfile12(){ 
        Profile p = new Profile("John Smith","male");     
        String[] lines = {"Nothing"}; 
        String exceptionType = "NONE"; 
        try{ 
            p.parseDataDump(lines); 
        }catch (Exception e){ 
            exceptionType = e.getClass().toString(); 
        } 
     
        assertEquals("class DataParseException",exceptionType);     
        assertNotEquals(0,++passed);         
    }         
     
    @Test 
    public void testProfile13(){ 
        Profile p = new Profile("John Smith","male");                 
        String[] lines = {"POST#12/23/15#my message#http://www.cs.gmu.edu/~kdobolyi/headshot.jpg 223", "POST#12/23/15#my message#http://www.cs.gmu.edu/~kdobolyi/headshot.jpg 223", "POST#12/23/15#my message#http://www.cs.gmu.edu/~kdobolyi/new.jpg 223"}; 
        String exceptionType = "NONE"; 
        try{ 
            p.parseDataDump(lines); 
        }catch (Exception e){ 
            exceptionType = e.getClass().toString(); 
        } 
     
        assertEquals("NONE",exceptionType);             
        assertEquals("12/23/15", (p.getPosts().get(0)).getDate()); 
        assertEquals("my message", (p.getPosts().get(0)).getText());         
        assertEquals("http://www.cs.gmu.edu/~kdobolyi/headshot.jpg", (p.getPosts().get(0)).getPhoto().getLocation());         
        assertEquals("http://www.cs.gmu.edu/~kdobolyi/headshot.jpg", (p.getPhotos().get(0)).getLocation());         
        assertEquals("http://www.cs.gmu.edu/~kdobolyi/new.jpg", (p.getPhotos().get(1)).getLocation()); 
        assertEquals(2, p.getPhotos().size());             
        assertNotEquals(0,++passed); 
    }     
     
    @Test 
    public void testAnalyzer(){ 
        Analyzer a = new Analyzer(); 
         
        assertEquals(100,a.getProfiles().length); 
        String[] lines = { 
                "PROFILE JaneSmith female", 
                "http://www.cs.gmu.edu/~kdobolyi/headshot.jpg 223", 
                "MESSAGE#Jane Doe#12/23/15#my message is short", 
                "POST#12/23/15#my message#http://www.cs.gmu.edu/~kdobolyi/headshot2.jpg 223", 
                "http://www.google.com/headshot.jpg 2456", 
                "CONTACT Joe Dupont", 
                "MESSAGE#Sam Smith#11/13/15#this is another message", 
                "POST#12/24/15#my message#http://www.cs.gmu.edu/~kdobolyi/headshot3.jpg 223", 
                 
                "PROFILE JohnDoe male", 
                "POST#12/24/15#my message#http://www.cs.gmu.edu/~kdobolyi/headshot4.jpg 223",                     
                "http://www.cs.gmu.edu/~kdobolyi/clouds.jpg 223", 
                "MESSAGE#Johhny Doe#12/23/15#my message is also short", 
                "POST#12/23/15#my message#http://www.cs.gmu.edu/~kdobolyi/headshot3.jpg 223", 
                "MESSAGE#Sally Fields#11/13/15#this is another message to someone else", 
                "http://www.google.com/headshot1.jpg 2456",         
                "http://www.google.com/headshot11.jpg 2456",                     
        }; 
        try { 
            a.parse(lines); 
        } catch (DataParseException e) { 
            fail("unexpected exception"); 
        } 
         
        Profile one = a.getProfiles()[0]; 
        Profile two = a.getProfiles()[1]; 
         
        assertEquals(100,a.getProfiles().length); 
        System.out.println(one); 
        assertEquals("JaneSmith", one.getUsername()); 
        assertEquals("female", one.getGender());         
        assertEquals("http://www.cs.gmu.edu/~kdobolyi/headshot.jpg", (one.getPhotos().get(0)).getLocation());         
        assertEquals("http://www.cs.gmu.edu/~kdobolyi/headshot2.jpg", (one.getPhotos().get(1)).getLocation()); 
        assertEquals("http://www.google.com/headshot.jpg", (one.getPhotos().get(2)).getLocation());         
        assertEquals("http://www.cs.gmu.edu/~kdobolyi/headshot3.jpg", (one.getPhotos().get(3)).getLocation()); 
        assertEquals(4,one.getPhotos().size());     
        assertEquals("my message is short", ((Message)one.getMessages().get(0)).getBody());             
        assertEquals("this is another message", ((Message)one.getMessages().get(1)).getBody());     
        assertEquals(2,one.getMessages().size());             
        assertEquals("Jane Doe", one.getContacts().get(0).toString());             
        assertEquals("Joe Dupont", one.getContacts().get(1).toString());     
        assertEquals("Sam Smith", one.getContacts().get(2).toString());             
        assertEquals("12/23/15", (one.getPosts().get(0)).getDate());     
        assertEquals("http://www.cs.gmu.edu/~kdobolyi/headshot2.jpg", (one.getPosts().get(0)).getPhoto().getLocation());     
        assertEquals("12/24/15", (one.getPosts().get(1)).getDate());     
        assertEquals("http://www.cs.gmu.edu/~kdobolyi/headshot3.jpg", (one.getPosts().get(1)).getPhoto().getLocation());     
         
        
        assertEquals("JohnDoe", two.getUsername()); 
        assertEquals("male", two.getGender());         
        assertEquals("http://www.cs.gmu.edu/~kdobolyi/headshot4.jpg", (two.getPhotos().get(0)).getLocation());         
        assertEquals("http://www.cs.gmu.edu/~kdobolyi/clouds.jpg", (two.getPhotos().get(1)).getLocation()); 
        assertEquals("http://www.cs.gmu.edu/~kdobolyi/headshot3.jpg", (two.getPhotos().get(2)).getLocation());         
        assertEquals("http://www.google.com/headshot1.jpg", ((Photo)two.getPhotos().get(3)).getLocation()); 
        assertEquals("http://www.google.com/headshot11.jpg", ((Photo)two.getPhotos().get(4)).getLocation());         
        assertEquals(5,two.getPhotos().size());     
        assertEquals("my message is also short", (two.getMessages().get(0)).getBody());             
        assertEquals("this is another message to someone else", (two.getMessages().get(1)).getBody());     
        assertEquals(2,one.getMessages().size());             
        assertEquals("Johhny Doe", two.getContacts().get(0).toString());             
        assertEquals("Sally Fields", two.getContacts().get(1).toString());         
        assertEquals("12/24/15", (two.getPosts().get(0)).getDate());     
        assertEquals("http://www.cs.gmu.edu/~kdobolyi/headshot4.jpg", (two.getPosts().get(0)).getPhoto().getLocation());     
        assertEquals("12/23/15", (two.getPosts().get(1)).getDate());     
        assertEquals("http://www.cs.gmu.edu/~kdobolyi/headshot3.jpg", (two.getPosts().get(1)).getPhoto().getLocation()); 
        assertNotEquals(0,++passed);         
    } 
     
    @Test 
    public void testPredictions(){ 
        Analyzer a = new Analyzer(); 
        List result; 
         
        String[] lines = { 
                "PROFILE Dostoevsky male", 
                "http://www.cs.gmu.edu/~kdobolyi/headshot.jpg 223", 
                "MESSAGE#Jane Doe#12/23/15#He was in the hospital from the middle of Lent till after Easter. When he was better, he remembered the dreams he had had while he was feverish and delirious. He dreamt that the whole world was condemned to a terrible new strange plague that had come to Europe from the depths of Asia. All were to be destroyed except a very few chosen. Some new sorts of microbes were attacking the bodies of men, but these microbes were endowed with intelligence and will. Men attacked by them became at once mad and furious. But never had men considered themselves so intellectual and so completely in possession of the truth as these sufferers, never had they considered their decisions, their scientific conclusions, their moral convictions so infallible. Whole villages, whole towns and peoples went mad from the infection. All were excited and did not understand one another. Each thought that he alone had the truth and was wretched looking at the others, beat himself on the breast, wept, and wrung his hands. They did not know how to judge and could not agree what to consider evil and what good; they did not know whom to blame, whom to justify. Men killed each other in a sort of senseless spite. They gathered together in armies against one another, but even on the march the armies would begin attacking each other, the ranks would be broken and the soldiers would fall on each other, stabbing and cutting, biting and devouring each other. The alarm bell was ringing all day long in the towns; men rushed together, but why they were summoned and who was summoning them no one knew. The most ordinary trades were abandoned, because everyone proposed his own ideas, his own improvements, and they could not agree. The land too was abandoned. Men met in groups, agreed on something, swore to keep together, but at once began on something quite different from what they had proposed. They accused one another, fought and killed each other. There were conflagrations and famine. All men and all things were involved in destruction. The plague spread and moved further and further. Only a few men could be saved in the whole world. They were a pure chosen people, destined to found a new race and a new life, to renew and purify the earth, but no one had seen these men, no one had heard their words and their voices. ",                
        }; 
        try { 
            a.parse(lines); 
        } catch (DataParseException e) { 
            fail("unexpected exception"); 
        } 
         
        result = a.analyzeProfiles();         
        assertEquals("Dostoevsky", result.get(0));     
        assertNotEquals(0,++passed);         
    }     
 
     
    @Test 
    public void testCrash(){ 
        Photo p1 = new Photo("kitten.jpg", 234, "C:\\MyPhotos\\kitten.jpg"); 
        Photo p2 = new Photo(null, -1, null); 
         
        assertEquals(false, p1.equals(p2)); 
        assertEquals(false, p1.equals("kitten")); 
        assertEquals(false, p1.equals(null)); 

    } 

     
} 
