package tests;

import base.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

public class FirstTest extends BaseTest {

    /* here you can create the object of the pages
        Example:
        private Page page;
     */

    @BeforeMethod
    private void beforeMethod() {
        /* this before is for pages creating an instance of the pages
            page = new Page(driver);
         */

    }


    @Test
    public void first_test(){
        // here you can write the tests
    }
}
