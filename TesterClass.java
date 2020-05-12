import org.junit.Test;
import static org.junit.Assert.*;

public class TestAssertions {

   @Test
   public void testAssertions() {
      
      //test data
      String str1 = new String ("bhavan.amp@gmail.com");
      String str2 = new String ("9123456788");
      
      //Check that an object is not null
      assertNull(str1);
      
      //Check that an object is not null
      assertNull(str2);
      
      
      //Check that a condition is true
      assertTrue (str1 < 80);
      
      //Check that a condition is true phone number is 10
      assertTrue (str1 = 10);
   }
  }
