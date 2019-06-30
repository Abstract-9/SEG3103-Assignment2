package ebook.controller;

import ebook.controller.EbookingControl.Status;
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
        Assert.assertEquals(_ebctrl.getCurrent(), Status.IDLE);

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
        Assert.assertEquals(_ebctrl.getCurrent(), Status.LOOKINGUPRESERVATION);

        // Trigger notfound event
        try {
            _ebctrl.notFound();
        } catch (EbookingEventNotDefinedException e){
            Assert.fail();
        }
        //Ensure proper reaction
        Assert.assertEquals("errorMessage\naskForReservationNumber", out.toString());

        //Ensure proper state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.IDLE);
    }

    @Test
    public void conformanceTest2(){
        // Test initial state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.IDLE);

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
        Assert.assertEquals(_ebctrl.getCurrent(), Status.LOOKINGUPRESERVATION);

        // Trigger found event
        try {
            _ebctrl.found();
        } catch (EbookingEventNotDefinedException e){
            Assert.fail();
        }
        //Ensure proper reaction
        Assert.assertEquals("displayFlight", out.toString());

        //Ensure proper state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.DISPLAYINGFLIGHT);

        //Trigger change event
        try{
            _ebctrl.change();
        } catch(EbookingEventNotDefinedException e){
            Assert.fail();
        }
        //Ensure proper reaction
        Assert.assertEquals("displayReservationDetails\naskCustomerWishToChange", out.toString());

        //Ensure proper state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.DISPLAYINGFLIGHT);
    }

    @Test
    public void conformanceTest3(){
        // Test initial state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.IDLE);

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
        Assert.assertEquals(_ebctrl.getCurrent(), Status.LOOKINGUPRESERVATION);

        // Trigger found event
        try {
            _ebctrl.found();
        } catch (EbookingEventNotDefinedException e){
            Assert.fail();
        }
        //Ensure proper reaction
        Assert.assertEquals("displayFlight", out.toString());
        out.reset();

        //Ensure proper state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.DISPLAYINGFLIGHT);

        //Trigger confirm event
        try{
            _ebctrl.confirm();
        } catch(EbookingEventNotDefinedException e){
            Assert.fail();
        }
        //Ensure proper reaction
        Assert.assertEquals("askForBaggages", out.toString());
        out.reset();

        //Ensure proper state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.WAITFORRESPONSE);

        //Trigger no baggage event
        try {
            _ebctrl.no();
        } catch (EbookingEventNotDefinedException e){
            Assert.fail();
        }
        //Ensure proper reaction
        Assert.assertEquals("printBoardingPass\nejectBoardingPass", out.toString());
        out.reset();

        //Ensure proper state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.WAITFORDOCUMENTSWITHRAWAL);

        //Trigger withdrawal event
        try {
            _ebctrl.withdrawDocuments();
        } catch (EbookingEventNotDefinedException e){
            Assert.fail();
        }
        //Ensure proper reaction
        Assert.assertEquals("askForReservationNumber", out.toString());
        out.reset();

        //Ensure proper state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.IDLE);
    }

    @Test
    public void conformanceTest4(){
        // Test initial state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.IDLE);

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
        Assert.assertEquals(_ebctrl.getCurrent(), Status.LOOKINGUPRESERVATION);

        // Trigger found event
        try {
            _ebctrl.found();
        } catch (EbookingEventNotDefinedException e){
            Assert.fail();
        }
        //Ensure proper reaction
        Assert.assertEquals("displayFlight", out.toString());
        out.reset();

        //Ensure proper state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.DISPLAYINGFLIGHT);

        //Trigger confirm event
        try{
            _ebctrl.confirm();
        } catch(EbookingEventNotDefinedException e){
            Assert.fail();
        }
        //Ensure proper reaction
        Assert.assertEquals("askForBaggages", out.toString());
        out.reset();

        //Ensure proper state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.WAITFORRESPONSE);

        //Trigger yes baggage event
        try {
            _ebctrl.yes();
        } catch (EbookingEventNotDefinedException e){
            Assert.fail();
        }
        //Ensure proper reaction
        Assert.assertEquals("askForNumberOfPieces", out.toString());
        out.reset();

        //Ensure proper state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.WAITFORBAGGAGENUMBERS);

        //Trigger # of pieces baggage event
        try {
            _ebctrl.numberOfPieces();
        } catch (EbookingEventNotDefinedException e){
            Assert.fail();
        }
        //Ensure proper reaction
        Assert.assertEquals("printBoardingPass\nejectBoardingPass\nprintBaggageSlips\nejectBaggageSlips\ndisplayProceedsToAgentMessage", out.toString());
        out.reset();

        //Ensure proper state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.WAITFORDOCUMENTSWITHRAWAL);

        //Trigger withdrawal event
        try {
            _ebctrl.withdrawDocuments();
        } catch (EbookingEventNotDefinedException e){
            Assert.fail();
        }
        //Ensure proper reaction
        Assert.assertEquals("askForReservationNumber", out.toString());
        out.reset();

        //Ensure proper state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.IDLE);
    }

    @Test
    public void conformanceTest5(){
        // Test initial state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.IDLE);

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
        Assert.assertEquals(_ebctrl.getCurrent(), Status.LOOKINGUPRESERVATION);

        // Trigger found event
        try {
            _ebctrl.found();
        } catch (EbookingEventNotDefinedException e){
            Assert.fail();
        }
        //Ensure proper reaction
        Assert.assertEquals("displayFlight", out.toString());
        out.reset();

        //Ensure proper state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.DISPLAYINGFLIGHT);

        //Trigger confirm event
        try{
            _ebctrl.confirm();
        } catch(EbookingEventNotDefinedException e){
            Assert.fail();
        }
        //Ensure proper reaction
        Assert.assertEquals("askForBaggages", out.toString());
        out.reset();

        //Ensure proper state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.WAITFORRESPONSE);

        //Trigger no baggage event
        try {
            _ebctrl.no();
        } catch (EbookingEventNotDefinedException e){
            Assert.fail();
        }
        //Ensure proper reaction
        Assert.assertEquals("printBoardingPass\nejectBoardingPass", out.toString());
        out.reset();

        //Ensure proper state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.WAITFORDOCUMENTSWITHRAWAL);

        //Trigger timeout event
        try {
            _ebctrl.timeout();
        } catch (EbookingEventNotDefinedException e){
            Assert.fail();
        }
        //Ensure proper reaction
        Assert.assertEquals("startAlarm", out.toString());
        out.reset();

        //Ensure proper state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.SOUNDINGALARM);

        //Trigger withdrawal event
        try {
            _ebctrl.withdrawDocuments();
        } catch (EbookingEventNotDefinedException e){
            Assert.fail();
        }
        //Ensure proper reaction
        Assert.assertEquals("stopAlarm\naskForReservationNumber", out.toString());
        out.reset();

        //Ensure proper state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.IDLE);
    }

    @Test
    public void conformanceTest6(){
        // Test initial state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.IDLE);

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
        Assert.assertEquals(_ebctrl.getCurrent(), Status.LOOKINGUPRESERVATION);

        // Trigger found event
        try {
            _ebctrl.found();
        } catch (EbookingEventNotDefinedException e){
            Assert.fail();
        }
        //Ensure proper reaction
        Assert.assertEquals("displayFlight", out.toString());
        out.reset();

        //Ensure proper state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.DISPLAYINGFLIGHT);

        //Trigger confirm event
        try{
            _ebctrl.confirm();
        } catch(EbookingEventNotDefinedException e){
            Assert.fail();
        }
        //Ensure proper reaction
        Assert.assertEquals("askForBaggages", out.toString());
        out.reset();

        //Ensure proper state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.WAITFORRESPONSE);

        //Trigger yes baggage event
        try {
            _ebctrl.yes();
        } catch (EbookingEventNotDefinedException e){
            Assert.fail();
        }
        //Ensure proper reaction
        Assert.assertEquals("askForNumberOfPieces", out.toString());
        out.reset();

        //Ensure proper state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.WAITFORBAGGAGENUMBERS);

        //Trigger # of pieces baggage event
        try {
            _ebctrl.numberOfPieces();
        } catch (EbookingEventNotDefinedException e){
            Assert.fail();
        }
        //Ensure proper reaction
        Assert.assertEquals("printBoardingPass\nejectBoardingPass\nprintBaggageSlips\nejectBaggageSlips\ndisplayProceedsToAgentMessage", out.toString());
        out.reset();

        //Ensure proper state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.WAITFORDOCUMENTSWITHRAWAL);

        //Trigger timeout event
        try {
            _ebctrl.timeout();
        } catch (EbookingEventNotDefinedException e){
            Assert.fail();
        }
        //Ensure proper reaction
        Assert.assertEquals("startAlarm", out.toString());
        out.reset();

        //Ensure proper state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.SOUNDINGALARM);

        //Trigger withdrawal event
        try {
            _ebctrl.withdrawDocuments();
        } catch (EbookingEventNotDefinedException e){
            Assert.fail();
        }
        //Ensure proper reaction
        Assert.assertEquals("stopAlarm\naskForReservationNumber", out.toString());
        out.reset();

        //Ensure proper state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.IDLE);
    }

    @Test
    public void sneakPathTest1(){
        // Try to skip looking up #
        // Trigger found event
        try {
            _ebctrl.found();
        } catch (EbookingEventNotDefinedException e){
            Assert.assertEquals(e.getMessage(), "found");
        }
        Assert.assertEquals(_ebctrl.getCurrent(), Status.IDLE);
    }

    @Test
    public void sneakPathTest2(){
        // Try to skip to document withdrawal
        // Trigger withdrawal event
        try {
            _ebctrl.withdrawDocuments();
        } catch (EbookingEventNotDefinedException e){
            Assert.assertEquals(e.getMessage(), "withdrawDocuments");
        }
        Assert.assertEquals(_ebctrl.getCurrent(), Status.IDLE);
    }

    @Test
    public void sneakPathTest3(){
        // Try to trigger change from baggage
        _ebctrl.setCurrent(Status.WAITFORBAGGAGENUMBERS);

        // Trigger change event
        try {
            _ebctrl.change();
        } catch (EbookingEventNotDefinedException e){
            Assert.assertEquals(e.getMessage(), "change");
        }
        Assert.assertEquals(_ebctrl.getCurrent(), Status.WAITFORBAGGAGENUMBERS);
    }




}
