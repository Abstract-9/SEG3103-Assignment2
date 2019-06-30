package ebook.controller;

import ebook.simulator.BasicReactions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.util.Scanner;

public class ebookControllerTest {

    private EbookingControl _ebctrl;
    private Scanner scan;
    private ByteArrayOutputStream out;

    @Before
    public void setup(){
        //Setup the controller
        _ebctrl = new EbookingControl(new BasicReactions());

        //Redirect sysout to check reactions
        out = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(out));
    }

    @Test
    public void conformanceTest1(){
        // Test initial state
        Assert.assertEquals(_ebctrl.getCurrent(), EbookingControl.Status.IDLE);

        // Transition into flight lookup
        try {
            _ebctrl.reservationNumber();
        }catch (EbookingEventNotDefinedException e){
            Assert.fail();
        }
        // Ensure proper reaction
        Assert.assertEquals("lookupReservation", out.toString());
        out.reset();

        //Ensure proper state
        Assert.assertEquals(_ebctrl.getCurrent(), EbookingControl.Status.LOOKINGUPRESERVATION);

        // Trigger notfound event
        try {
            _ebctrl.notFound();
        } catch (EbookingEventNotDefinedException e){
            Assert.fail();
        }
        //Ensure proper reaction
        Assert.assertEquals("errorMessage\naskForReservationNumber", out.toString());

        //Ensure proper state
        Assert.assertEquals(_ebctrl.getCurrent(), EbookingControl.Status.IDLE);
    }
}
