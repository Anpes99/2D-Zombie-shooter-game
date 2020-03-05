/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaFXApplication2;

import javafx.stage.Stage;
import junit.framework.TestCase;

/**
 *
 * @author myyra
 */
public class JavaFXApplication2Test extends TestCase {
    
    public JavaFXApplication2Test(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of spawnTrap method, of class JavaFXApplication2.
     */
    public void testSpawnTrap() {
        System.out.println("spawnTrap");
        JavaFXApplication2 instance = new JavaFXApplication2();
        instance.spawnTrap();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of start method, of class JavaFXApplication2.
     */
    public void testStart() {
        System.out.println("start");
        Stage stage = null;
        JavaFXApplication2 instance = new JavaFXApplication2();
        instance.start(stage);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of shoot method, of class JavaFXApplication2.
     */
    public void testShoot() {
        System.out.println("shoot");
        Character shooter = null;
        JavaFXApplication2 instance = new JavaFXApplication2();
        instance.shoot(shooter);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class JavaFXApplication2.
     */
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        JavaFXApplication2.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
