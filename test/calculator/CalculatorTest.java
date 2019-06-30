package calculator;

import java.awt.event.ActionEvent;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

import javax.swing.*;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class CalculatorTest {
	/*
	 * These tests illustrate how to use Java reflection to access private members
	 */
//	@Test
//	public void test1() {
//		CalCFrame calf = new CalCFrame("Tester");
//		JButton but0 = getButton(calf,0); // get button
//		ActionEvent ev = new ActionEvent(but0, 0, null); // generate a click on button
//		calf.actionPerformed(ev);
//		Assert.assertEquals("1", calf.getResult().getText());
//	}
//
//	@Test
//	public void test2() {
//		CalCFrame calf = new CalCFrame("Tester");
//		JButton but1 = getButton(calf,1); // get button
//		ActionEvent ev = new ActionEvent(but1, 0, null); // generate a click on button
//		calf.actionPerformed(ev);
//		Assert.assertEquals("2", calf.getResult().getText());
//	}

//	@Test
//	public void test1(){
//		CalCFrame calf = new CalCFrame("Tester");
//	}

	int[] buttons;
	int exResult;
	boolean clearscreen;
	CalCFrame calf;

	public CalculatorTest(int[] buttons, int exResult, boolean clearscreen){
		this.buttons = buttons;
		calf = new CalCFrame("Tester");
		this.exResult = exResult;
		this.clearscreen = clearscreen;
	}

	// using Java reflection to access the calculator buttons
	private JButton getButton(CalCFrame calf,int b) {
		Field fb;
		JButton but = null;
		try {
			fb = calf.getClass().getDeclaredField("buttons");
			fb.setAccessible(true);
			but = ((JButton[]) fb.get(calf))[b];
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return but;
	}

	@Parameterized.Parameters
	public static List<Object[]> data(){
		List<Object[]> data = new LinkedList<>();

		data.add(new Object[]{new Integer[]{17, 1, 18}, "0.02", false});
		data.add(new Object[]{new Integer[]{15}, "0", false});
		data.add(new Object[]{new Integer[]{15}, "0", true});
		data.add(new Object[]{new Integer[]{1, 3, 18}, "4.0", false});
		data.add(new Object[]{new Integer[]{1, 3, 1, 18}, "4.0", true});
		return data();
	}


	@Test
	public void test(){
		if(clearscreen) calf.setClearscreen(true);
		for (int button : buttons){
			JButton but = getButton(calf, button);
			ActionEvent ev = new ActionEvent(but, 0, null);
			calf.actionPerformed(ev);
		}
		Assert.assertEquals(exResult, calf.getResult().getText());
	}

	/*@Test
	public void test1() {
		CalCFrame calf = new CalCFrame("Tester");

		JButton but1 = getButton(calf,17); // get button
		ActionEvent ev = new ActionEvent(but1, 0, null); // generate a click on button
		calf.actionPerformed(ev);

		JButton but2 = getButton(calf,1); // get button
		ActionEvent ev2 = new ActionEvent(but2, 0, null); // generate a click on button
		calf.actionPerformed(ev2);

		JButton but3 = getButton(calf,18); // get button
		ActionEvent ev3 = new ActionEvent(but3, 0, null); // generate a click on button
		calf.actionPerformed(ev3);

		Assert.assertEquals("0.02", calf.getResult().getText());
	}

	@Test
	public void test2() {
		CalCFrame calf = new CalCFrame("Tester");

		JButton but1 = getButton(calf,15); // get button
		ActionEvent ev = new ActionEvent(but1, 0, null); // generate a click on button
		calf.actionPerformed(ev);

		Assert.assertEquals("0", calf.getResult().getText());
	}

	@Test
	public void test3() {
		CalCFrame calf = new CalCFrame("Tester");

		calf.setClearscreen(true);

		JButton but1 = getButton(calf,15); // get button
		ActionEvent ev = new ActionEvent(but1, 0, null); // generate a click on button
		calf.actionPerformed(ev);

		Assert.assertEquals("0", calf.getResult().getText());
	}

	@Test
	public void test4() {
		CalCFrame calf = new CalCFrame("Tester");

		JButton but1 = getButton(calf,1); // get button
		ActionEvent ev = new ActionEvent(but1, 0, null); // generate a click on button
		calf.actionPerformed(ev);

		JButton but2 = getButton(calf,3); // get button
		ActionEvent ev2 = new ActionEvent(but2, 0, null); // generate a click on button
		calf.actionPerformed(ev2);

		calf.actionPerformed(ev);

		JButton but3 = getButton(calf,18); // get button
		ActionEvent ev3 = new ActionEvent(but3, 0, null); // generate a click on button
		calf.actionPerformed(ev3);

		Assert.assertEquals("4.0", calf.getResult().getText());
	}

	@Test
	public void test5() {
		CalCFrame calf = new CalCFrame("Tester");

		JButton but1 = getButton(calf,1); // get button
		ActionEvent ev = new ActionEvent(but1, 0, null); // generate a click on button
		calf.actionPerformed(ev);

		JButton but2 = getButton(calf,3); // get button
		ActionEvent ev2 = new ActionEvent(but2, 0, null); // generate a click on button
		calf.actionPerformed(ev2);

		calf.actionPerformed(ev);

		JButton but3 = getButton(calf,18); // get button
		ActionEvent ev3 = new ActionEvent(but3, 0, null); // generate a click on button
		calf.actionPerformed(ev3);

		Assert.assertEquals("4.0", calf.getResult().getText());
	}*/


}