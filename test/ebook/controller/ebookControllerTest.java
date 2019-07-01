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
        //Setup the controller, step .1 for every test
        _ebctrl = new EbookingControl(new BasicReactions());

        //Redirect sysout to check reactions
        out = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(out));
    }

    @Test
    public void conformanceTest1(){
        //1.1 Test initial state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.IDLE);

        //1.2 Transition into flight lookup
        try {
            _ebctrl.reservationNumber();
        }catch (EbookingEventNotDefinedException e){
            Assert.fail();
        }
        // Ensure proper reaction
        Assert.assertEquals("lookupReservation", out.toString().trim());
        out.reset();

        //Ensure proper state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.LOOKINGUPRESERVATION);

        //1.3 Trigger notfound event
        try {
            _ebctrl.notFound();
        } catch (EbookingEventNotDefinedException e){
            Assert.fail();
        }
        //Ensure proper reaction
        Assert.assertEquals("errorMessage\r\naskForReservationNumber", out.toString().trim());

        //Ensure proper state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.IDLE);
    }

    @Test
    public void conformanceTest2(){
        //2.1 Test initial state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.IDLE);

        //2.2 Transition into flight lookup
        try {
            _ebctrl.reservationNumber();
        }catch (EbookingEventNotDefinedException e){
            Assert.fail();
        }
        // Ensure proper reaction
        Assert.assertEquals("lookupReservation", out.toString().trim());
        out.reset();

        //Ensure proper state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.LOOKINGUPRESERVATION);

        //2.3 Trigger found event
        try {
            _ebctrl.found();
        } catch (EbookingEventNotDefinedException e){
            Assert.fail();
        }
        //Ensure proper reaction
        Assert.assertEquals("displayFlight", out.toString().trim());

        //Ensure proper state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.DISPLAYINGFLIGHT);

        //2.4 Trigger change event
        try{
            _ebctrl.change();
        } catch(EbookingEventNotDefinedException e){
            Assert.fail();
        }
        //Ensure proper reaction
        Assert.assertEquals("displayReservationDetails\r\naskCustomerWishToChange", out.toString().trim());

        //Ensure proper state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.DISPLAYINGFLIGHT);
    }

    @Test
    public void conformanceTest3(){
        //3.1 Test initial state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.IDLE);

        //3.2 Transition into flight lookup
        try {
            _ebctrl.reservationNumber();
        }catch (EbookingEventNotDefinedException e){
            Assert.fail();
        }
        // Ensure proper reaction
        Assert.assertEquals("lookupReservation", out.toString().trim());
        out.reset();

        //Ensure proper state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.LOOKINGUPRESERVATION);

        //3.3 Trigger found event
        try {
            _ebctrl.found();
        } catch (EbookingEventNotDefinedException e){
            Assert.fail();
        }
        //Ensure proper reaction
        Assert.assertEquals("displayFlight", out.toString().trim());
        out.reset();

        //Ensure proper state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.DISPLAYINGFLIGHT);

        //3.4 Trigger confirm event
        try{
            _ebctrl.confirm();
        } catch(EbookingEventNotDefinedException e){
            Assert.fail();
        }
        //Ensure proper reaction
        Assert.assertEquals("askForBaggages", out.toString().trim());
        out.reset();

        //Ensure proper state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.WAITFORRESPONSE);

        //3.5 Trigger no baggage event
        try {
            _ebctrl.no();
        } catch (EbookingEventNotDefinedException e){
            Assert.fail();
        }
        //Ensure proper reaction
        Assert.assertEquals("printBoardingPass\r\nejectBoardingPass", out.toString().trim());
        out.reset();

        //Ensure proper state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.WAITFORDOCUMENTSWITHRAWAL);

        //3.6 Trigger withdrawal event
        try {
            _ebctrl.withdrawDocuments();
        } catch (EbookingEventNotDefinedException e){
            Assert.fail();
        }
        //Ensure proper reaction
        Assert.assertEquals("askForReservationNumber", out.toString().trim());
        out.reset();

        //Ensure proper state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.IDLE);
    }

    @Test
    public void conformanceTest4(){
        //4.1 Test initial state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.IDLE);

        //4.2 Transition into flight lookup
        try {
            _ebctrl.reservationNumber();
        }catch (EbookingEventNotDefinedException e){
            Assert.fail();
        }
        // Ensure proper reaction
        Assert.assertEquals("lookupReservation", out.toString().trim());
        out.reset();

        //Ensure proper state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.LOOKINGUPRESERVATION);

        //4.3 Trigger found event
        try {
            _ebctrl.found();
        } catch (EbookingEventNotDefinedException e){
            Assert.fail();
        }
        //Ensure proper reaction
        Assert.assertEquals("displayFlight", out.toString().trim());
        out.reset();

        //Ensure proper state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.DISPLAYINGFLIGHT);

        //4.4 Trigger confirm event
        try{
            _ebctrl.confirm();
        } catch(EbookingEventNotDefinedException e){
            Assert.fail();
        }
        //Ensure proper reaction
        Assert.assertEquals("askForBaggages", out.toString().trim());
        out.reset();

        //Ensure proper state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.WAITFORRESPONSE);

        //4.5 Trigger yes baggage event
        try {
            _ebctrl.yes();
        } catch (EbookingEventNotDefinedException e){
            Assert.fail();
        }
        //Ensure proper reaction
        Assert.assertEquals("askForNumberOfPieces", out.toString().trim());
        out.reset();

        //Ensure proper state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.WAITFORBAGGAGENUMBERS);

        //4.6 Trigger # of pieces baggage event
        try {
            _ebctrl.numberOfPieces();
        } catch (EbookingEventNotDefinedException e){
            Assert.fail();
        }
        //Ensure proper reaction
        Assert.assertEquals("printBoardingPass\r\nejectBoardingPass\r\nprintBaggageSlips\r\nejectBaggageSlips\r\ndisplayProceedsToAgentMessage", out.toString().trim());
        out.reset();

        //Ensure proper state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.WAITFORDOCUMENTSWITHRAWAL);

        //4.7 Trigger withdrawal event
        try {
            _ebctrl.withdrawDocuments();
        } catch (EbookingEventNotDefinedException e){
            Assert.fail();
        }
        //Ensure proper reaction
        Assert.assertEquals("askForReservationNumber", out.toString().trim());
        out.reset();

        //Ensure proper state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.IDLE);
    }

    @Test
    public void conformanceTest5(){
        //5.1 Test initial state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.IDLE);

        //5.2 Transition into flight lookup
        try {
            _ebctrl.reservationNumber();
        }catch (EbookingEventNotDefinedException e){
            Assert.fail();
        }
        // Ensure proper reaction
        Assert.assertEquals("lookupReservation", out.toString().trim());
        out.reset();

        //Ensure proper state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.LOOKINGUPRESERVATION);

        //5.3 Trigger found event
        try {
            _ebctrl.found();
        } catch (EbookingEventNotDefinedException e){
            Assert.fail();
        }
        //Ensure proper reaction
        Assert.assertEquals("displayFlight", out.toString().trim());
        out.reset();

        //Ensure proper state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.DISPLAYINGFLIGHT);

        //5.4 Trigger confirm event
        try{
            _ebctrl.confirm();
        } catch(EbookingEventNotDefinedException e){
            Assert.fail();
        }
        //Ensure proper reaction
        Assert.assertEquals("askForBaggages", out.toString().trim());
        out.reset();

        //Ensure proper state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.WAITFORRESPONSE);

        //5.5 Trigger no baggage event
        try {
            _ebctrl.no();
        } catch (EbookingEventNotDefinedException e){
            Assert.fail();
        }
        //Ensure proper reaction
        Assert.assertEquals("printBoardingPass\r\nejectBoardingPass", out.toString().trim());
        out.reset();

        //Ensure proper state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.WAITFORDOCUMENTSWITHRAWAL);

        //5.6 Trigger timeout event
        try {
            _ebctrl.timeout();
        } catch (EbookingEventNotDefinedException e){
            Assert.fail();
        }
        //Ensure proper reaction
        Assert.assertEquals("startAlarm", out.toString().trim());
        out.reset();

        //Ensure proper state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.SOUNDINGALARM);

        //5.7 Trigger withdrawal event
        try {
            _ebctrl.withdrawDocuments();
        } catch (EbookingEventNotDefinedException e){
            Assert.fail();
        }
        //Ensure proper reaction
        Assert.assertEquals("stopAlarm\r\naskForReservationNumber", out.toString().trim());
        out.reset();

        //Ensure proper state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.IDLE);
    }

    @Test
    public void conformanceTest6(){
        //6.1 Test initial state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.IDLE);

        //6.2 Transition into flight lookup
        try {
            _ebctrl.reservationNumber();
        }catch (EbookingEventNotDefinedException e){
            Assert.fail();
        }
        // Ensure proper reaction
        Assert.assertEquals("lookupReservation", out.toString().trim());
        out.reset();

        //Ensure proper state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.LOOKINGUPRESERVATION);

        //6.3 Trigger found event
        try {
            _ebctrl.found();
        } catch (EbookingEventNotDefinedException e){
            Assert.fail();
        }
        //Ensure proper reaction
        Assert.assertEquals("displayFlight", out.toString().trim());
        out.reset();

        //Ensure proper state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.DISPLAYINGFLIGHT);

        //6.4 Trigger confirm event
        try{
            _ebctrl.confirm();
        } catch(EbookingEventNotDefinedException e){
            Assert.fail();
        }
        //Ensure proper reaction
        Assert.assertEquals("askForBaggages", out.toString().trim());
        out.reset();

        //Ensure proper state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.WAITFORRESPONSE);

        //6.5 Trigger yes baggage event
        try {
            _ebctrl.yes();
        } catch (EbookingEventNotDefinedException e){
            Assert.fail();
        }
        //Ensure proper reaction
        Assert.assertEquals("askForNumberOfPieces", out.toString().trim());
        out.reset();

        //Ensure proper state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.WAITFORBAGGAGENUMBERS);

        //6.6 Trigger # of pieces baggage event
        try {
            _ebctrl.numberOfPieces();
        } catch (EbookingEventNotDefinedException e){
            Assert.fail();
        }
        //Ensure proper reaction
        Assert.assertEquals("printBoardingPass\r\nejectBoardingPass\r\nprintBaggageSlips\r\nejectBaggageSlips\r\ndisplayProceedsToAgentMessage", out.toString().trim());
        out.reset();

        //Ensure proper state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.WAITFORDOCUMENTSWITHRAWAL);

        //6.7 Trigger timeout event
        try {
            _ebctrl.timeout();
        } catch (EbookingEventNotDefinedException e){
            Assert.fail();
        }
        //Ensure proper reaction
        Assert.assertEquals("startAlarm", out.toString().trim());
        out.reset();

        //Ensure proper state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.SOUNDINGALARM);

        //6.8 Trigger withdrawal event
        try {
            _ebctrl.withdrawDocuments();
        } catch (EbookingEventNotDefinedException e){
            Assert.fail();
        }
        //Ensure proper reaction
        Assert.assertEquals("stopAlarm\r\naskForReservationNumber", out.toString().trim());
        out.reset();

        //Ensure proper state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.IDLE);
    }

    @Test
    public void conformanceTest7(){
        //7.1 Test initial state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.IDLE);

        //7.2 Transition into flight lookup
        try {
            _ebctrl.reservationNumber();
        }catch (EbookingEventNotDefinedException e){
            Assert.fail();
        }
        // Ensure proper reaction
        Assert.assertEquals("lookupReservation", out.toString().trim());
        out.reset();

        //Ensure proper state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.LOOKINGUPRESERVATION);

        //7.3 Trigger found event
        try {
            _ebctrl.found();
        } catch (EbookingEventNotDefinedException e){
            Assert.fail();
        }
        //Ensure proper reaction
        Assert.assertEquals("displayFlight", out.toString().trim());
        out.reset();

        //Ensure proper state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.DISPLAYINGFLIGHT);

        //7.4 Trigger cancel event
        try{
            _ebctrl.cancel();
        } catch(EbookingEventNotDefinedException e){
            Assert.fail();
        }
        //Ensure proper reaction
        Assert.assertEquals("askForReservationNumber", out.toString().trim());

        //Ensure proper state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.IDLE);
    }

    @Test
    public void conformanceTest8(){
        // Test initial state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.IDLE);

        // Transition into flight lookup
        try {
            _ebctrl.reservationNumber();
        }catch (EbookingEventNotDefinedException e){
            Assert.fail();
        }
        // Ensure proper reaction
        Assert.assertEquals("lookupReservation", out.toString().trim());
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
        Assert.assertEquals("displayFlight", out.toString().trim());
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
        Assert.assertEquals("askForBaggages", out.toString().trim());
        out.reset();

        //Ensure proper state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.WAITFORRESPONSE);

        //Trigger cancel event
        try{
            _ebctrl.cancel();
        } catch(EbookingEventNotDefinedException e){
            Assert.fail();
        }
        //Ensure proper reaction
        Assert.assertEquals("askForReservationNumber", out.toString().trim());

        //Ensure proper state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.IDLE);
    }

    @Test
    public void conformanceTest9(){
        // Test initial state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.IDLE);

        // Transition into flight lookup
        try {
            _ebctrl.reservationNumber();
        }catch (EbookingEventNotDefinedException e){
            Assert.fail();
        }
        // Ensure proper reaction
        Assert.assertEquals("lookupReservation", out.toString().trim());
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
        Assert.assertEquals("displayFlight", out.toString().trim());
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
        Assert.assertEquals("askForBaggages", out.toString().trim());
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
        Assert.assertEquals("askForNumberOfPieces", out.toString().trim());
        out.reset();

        //Ensure proper state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.WAITFORBAGGAGENUMBERS);

        //Trigger cancel event
        try{
            _ebctrl.cancel();
        } catch(EbookingEventNotDefinedException e){
            Assert.fail();
        }
        //Ensure proper reaction
        Assert.assertEquals("askForReservationNumber", out.toString().trim());

        //Ensure proper state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.IDLE);
    }

    /**
     * SNEAK PATHS
     *
     * Counting every state and transition, there are approximately 63 sneak paths available in the
     * current state diagram. As making this many tests would be redundant and excessive, I will be making
     * 2 per state.
     */


    // Testing Idle state, found transition
    @Test
    public void sneakPathTest1(){
        //10.1 Test initial state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.IDLE);

        //10.2 Try to skip looking up #
        // Trigger found event
        try {
            _ebctrl.found();
        } catch (EbookingEventNotDefinedException e){
            Assert.assertEquals(e.getMessage(), "found");
        }
        Assert.assertEquals(_ebctrl.getCurrent(), Status.IDLE);
    }

    // Testing Idle state, withdrawDocuments transition
    @Test
    public void sneakPathTest2(){
        //11.1 Test initial state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.IDLE);
        // Try to skip to document withdrawal
        //11.2 Trigger withdrawal event
        try {
            _ebctrl.withdrawDocuments();
        } catch (EbookingEventNotDefinedException e){
            Assert.assertEquals(e.getMessage(), "withdrawDocuments");
        }
        Assert.assertEquals(_ebctrl.getCurrent(), Status.IDLE);
    }

    // Testing lookingUpReservation state, change transition
    @Test
    public void sneakPathTest3(){
        //12.1 Test initial state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.IDLE);
        //12.2 Set state
        _ebctrl.setCurrent(Status.LOOKINGUPRESERVATION);

        //12.3 Trigger change event
        try {
            _ebctrl.change();
        } catch (EbookingEventNotDefinedException e){
            Assert.assertEquals(e.getMessage(), "change");
        }
        Assert.assertEquals(_ebctrl.getCurrent(), Status.LOOKINGUPRESERVATION);
    }

    // Testing lookingUpReservation state, confirm transition
    @Test
    public void sneakPathTest4(){
        //13.1 Test initial state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.IDLE);
        //13.2 Set state
        _ebctrl.setCurrent(Status.LOOKINGUPRESERVATION);

        //13.3 Trigger confirm event
        try {
            _ebctrl.confirm();
        } catch (EbookingEventNotDefinedException e){
            Assert.assertEquals(e.getMessage(), "confirm");
        }
        Assert.assertEquals(_ebctrl.getCurrent(), Status.LOOKINGUPRESERVATION);
    }

    // Testing displayingFlight# state, not found transition
    @Test
    public void sneakPathTest5(){
        //14.1 Test initial state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.IDLE);
        //14.2 Set state
        _ebctrl.setCurrent(Status.DISPLAYINGFLIGHT);

        //14.3 Trigger not found event
        try {
            _ebctrl.notFound();
        } catch (EbookingEventNotDefinedException e){
            Assert.assertEquals(e.getMessage(), "notFound");
        }
        Assert.assertEquals(_ebctrl.getCurrent(), Status.DISPLAYINGFLIGHT);
    }

    // Testing displayingFlight# state, no transition
    @Test
    public void sneakPathTest6(){
        //15.1 Test initial state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.IDLE);
        //15.2 Set state
        _ebctrl.setCurrent(Status.DISPLAYINGFLIGHT);

        //15.3 Trigger not found event
        try {
            _ebctrl.no();
        } catch (EbookingEventNotDefinedException e){
            Assert.assertEquals(e.getMessage(), "no");
        }
        Assert.assertEquals(_ebctrl.getCurrent(), Status.DISPLAYINGFLIGHT);
    }


    // Testing waitForBaggageResponse state, # pieces transition
    @Test
    public void sneakPathTest7(){
        // Test initial state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.IDLE);
        // Set state
        _ebctrl.setCurrent(Status.WAITFORRESPONSE);

        // Trigger not found event
        try {
            _ebctrl.numberOfPieces();
        } catch (EbookingEventNotDefinedException e){
            Assert.assertEquals(e.getMessage(), "numberOfPieces");
        }
        Assert.assertEquals(_ebctrl.getCurrent(), Status.WAITFORRESPONSE);
    }

    // Testing waitForBaggageResponse state, timeout transition
    @Test
    public void sneakPathTest8(){
        // Test initial state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.IDLE);
        // Set state
        _ebctrl.setCurrent(Status.WAITFORRESPONSE);

        // Trigger not found event
        try {
            _ebctrl.timeout();
        } catch (EbookingEventNotDefinedException e){
            Assert.assertEquals(e.getMessage(), "timeout");
        }
        Assert.assertEquals(_ebctrl.getCurrent(), Status.WAITFORRESPONSE);
    }

    // Testing waitForBaggage# state, yes transition
    @Test
    public void sneakPathTest9(){
        // Test initial state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.IDLE);
        // Set state
        _ebctrl.setCurrent(Status.WAITFORBAGGAGENUMBERS);

        // Trigger not found event
        try {
            _ebctrl.yes();
        } catch (EbookingEventNotDefinedException e){
            Assert.assertEquals(e.getMessage(), "yes");
        }
        Assert.assertEquals(_ebctrl.getCurrent(), Status.WAITFORBAGGAGENUMBERS);
    }

    // Testing waitForBaggage# state, reservation# transition
    @Test
    public void sneakPathTest10(){
        // Test initial state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.IDLE);
        // Set state
        _ebctrl.setCurrent(Status.WAITFORBAGGAGENUMBERS);

        // Trigger event
        try {
            _ebctrl.reservationNumber();
        } catch (EbookingEventNotDefinedException e){
            Assert.assertEquals(e.getMessage(), "reservationNumber");
        }
        Assert.assertEquals(_ebctrl.getCurrent(), Status.WAITFORBAGGAGENUMBERS);
    }

    // Testing waitForDocWithdraw state, notFound transition
    @Test
    public void sneakPathTest11(){
        // Test initial state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.IDLE);
        // Set state
        _ebctrl.setCurrent(Status.WAITFORDOCUMENTSWITHRAWAL);

        // Trigger event
        try {
            _ebctrl.notFound();
        } catch (EbookingEventNotDefinedException e){
            Assert.assertEquals(e.getMessage(), "notFound");
        }
        Assert.assertEquals(_ebctrl.getCurrent(), Status.WAITFORDOCUMENTSWITHRAWAL);
    }

    // Testing waitForDocWithdraw state, change transition
    @Test
    public void sneakPathTest12(){
        // Test initial state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.IDLE);
        // Set state
        _ebctrl.setCurrent(Status.WAITFORDOCUMENTSWITHRAWAL);

        // Trigger event
        try {
            _ebctrl.change();
        } catch (EbookingEventNotDefinedException e){
            Assert.assertEquals(e.getMessage(), "change");
        }
        Assert.assertEquals(_ebctrl.getCurrent(), Status.WAITFORDOCUMENTSWITHRAWAL);
    }

    // Testing soundingAlarm state, found transition
    @Test
    public void sneakPathTest13(){
        // Test initial state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.IDLE);
        // Set state
        _ebctrl.setCurrent(Status.SOUNDINGALARM);

        // Trigger event
        try {
            _ebctrl.found();
        } catch (EbookingEventNotDefinedException e){
            Assert.assertEquals(e.getMessage(), "found");
        }
        Assert.assertEquals(_ebctrl.getCurrent(), Status.SOUNDINGALARM);
    }

    // Testing soundingAlarm state, timeout transition
    @Test
    public void sneakPathTest14(){
        // Test initial state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.IDLE);
        // Set state
        _ebctrl.setCurrent(Status.SOUNDINGALARM);

        // Trigger event
        try {
            _ebctrl.timeout();
        } catch (EbookingEventNotDefinedException e){
            Assert.assertEquals(e.getMessage(), "timeout");
        }
        Assert.assertEquals(_ebctrl.getCurrent(), Status.SOUNDINGALARM);
    }

    // Testing LookingUpReservation state, cancel transition
    @Test
    public void sneakPathTest15(){
        // Test initial state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.IDLE);
        // Set state
        _ebctrl.setCurrent(Status.LOOKINGUPRESERVATION);

        // Trigger event
        try {
            _ebctrl.cancel();
        } catch (EbookingEventNotDefinedException e){
            Assert.assertEquals(e.getMessage(), "cancel");
        }
        Assert.assertEquals(_ebctrl.getCurrent(), Status.LOOKINGUPRESERVATION);
    }

    // Testing WaitingForWithdraw state, cancel transition
    @Test
    public void sneakPathTest16(){
        // Test initial state
        Assert.assertEquals(_ebctrl.getCurrent(), Status.IDLE);
        // Set state
        _ebctrl.setCurrent(Status.WAITFORDOCUMENTSWITHRAWAL);

        // Trigger event
        try {
            _ebctrl.cancel();
        } catch (EbookingEventNotDefinedException e){
            Assert.assertEquals(e.getMessage(), "cancel");
        }
        Assert.assertEquals(_ebctrl.getCurrent(), Status.WAITFORDOCUMENTSWITHRAWAL);
    }










}
