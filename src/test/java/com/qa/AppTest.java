package com.qa;

import static org.junit.Assert.assertTrue;

import com.qa.App;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest
{

    @Test
    public void shouldAnswerWithTrue()
    {
        App app = new App();
        String [] args = null;
        App.main(args);
        assertTrue( app instanceof App );
    }

}