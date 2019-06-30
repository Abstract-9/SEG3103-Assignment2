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

//@RunWith(Parameterized.class)
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

	/*
	public CalculatorTest(int[] buttons, int exResult, boolean clearscreen){
		this.buttons = buttons;
		calf = new CalCFrame("Tester");
		this.exResult = exResult;
		this.clearscreen = clearscreen;
	}
	*/

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
	/*
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
	*/

	@Test
	public void test1() {
		CalCFrame calf = new CalCFrame("Tester");

		calf.setClearscreen(false);

		JButton but1 = getButton(calf,0); // get button
		ActionEvent ev = new ActionEvent(but1, 0, null); // generate a click on button
		calf.actionPerformed(ev);

		JButton but2 = getButton(calf,3); // get button
		ActionEvent ev2 = new ActionEvent(but2, 0, null); // generate a click on button
		calf.actionPerformed(ev2);

		JButton but3 = getButton(calf,1); // get button
		ActionEvent ev3 = new ActionEvent(but3, 0, null); // generate a click on button
		calf.actionPerformed(ev3);

		JButton but4 = getButton(calf,18); // get button
		ActionEvent ev4 = new ActionEvent(but4, 0, null); // generate a click on button
		calf.actionPerformed(ev4);

		Assert.assertEquals("3.0", calf.getResult().getText());
	}

	@Test
	public void test2() {
		CalCFrame calf = new CalCFrame("Tester");

		calf.setClearscreen(false);

		JButton but1 = getButton(calf,2); // get button
		ActionEvent ev = new ActionEvent(but1, 0, null); // generate a click on button
		calf.actionPerformed(ev);

		JButton but2 = getButton(calf,4); // get button
		ActionEvent ev2 = new ActionEvent(but2, 0, null); // generate a click on button
		calf.actionPerformed(ev2);

		JButton but3 = getButton(calf,5); // get button
		ActionEvent ev3 = new ActionEvent(but3, 0, null); // generate a click on button
		calf.actionPerformed(ev3);

		JButton but4 = getButton(calf,18); // get button
		ActionEvent ev4 = new ActionEvent(but4, 0, null); // generate a click on button
		calf.actionPerformed(ev4);

		Assert.assertEquals("-1.0", calf.getResult().getText());
	}

	@Test
	public void test3() {
		CalCFrame calf = new CalCFrame("Tester");

		calf.setClearscreen(false);

		JButton but1 = getButton(calf,6); // get button
		ActionEvent ev = new ActionEvent(but1, 0, null); // generate a click on button
		calf.actionPerformed(ev);

		JButton but2 = getButton(calf,8); // get button
		ActionEvent ev2 = new ActionEvent(but2, 0, null); // generate a click on button
		calf.actionPerformed(ev2);

		JButton but3 = getButton(calf,7); // get button
		ActionEvent ev3 = new ActionEvent(but3, 0, null); // generate a click on button
		calf.actionPerformed(ev3);

		JButton but4 = getButton(calf,18); // get button
		ActionEvent ev4 = new ActionEvent(but4, 0, null); // generate a click on button
		calf.actionPerformed(ev4);

		Assert.assertEquals("30.0", calf.getResult().getText());
	}

	@Test
	public void test4() {
		CalCFrame calf = new CalCFrame("Tester");

		calf.setClearscreen(false);

		JButton but1 = getButton(calf,10); // get button
		ActionEvent ev = new ActionEvent(but1, 0, null); // generate a click on button
		calf.actionPerformed(ev);

		JButton but2 = getButton(calf,9); // get button
		ActionEvent ev2 = new ActionEvent(but2, 0, null); // generate a click on button
		calf.actionPerformed(ev2);

		JButton but3 = getButton(calf,11); // get button
		ActionEvent ev3 = new ActionEvent(but3, 0, null); // generate a click on button
		calf.actionPerformed(ev3);

		JButton but4 = getButton(calf,18); // get button
		ActionEvent ev4 = new ActionEvent(but4, 0, null); // generate a click on button
		calf.actionPerformed(ev4);

		Assert.assertEquals("0.875", calf.getResult().getText());
	}

	@Test
	public void test5() {
		CalCFrame calf = new CalCFrame("Tester");

		calf.setClearscreen(false);

		JButton but1 = getButton(calf,12); // get button
		ActionEvent ev = new ActionEvent(but1, 0, null); // generate a click on button
		calf.actionPerformed(ev);

		JButton but2 = getButton(calf,13); // get button
		ActionEvent ev2 = new ActionEvent(but2, 0, null); // generate a click on button
		calf.actionPerformed(ev2);

		JButton but3 = getButton(calf,1); // get button
		ActionEvent ev3 = new ActionEvent(but3, 0, null); // generate a click on button
		calf.actionPerformed(ev3);

		JButton but4 = getButton(calf,18); // get button
		ActionEvent ev4 = new ActionEvent(but4, 0, null); // generate a click on button
		calf.actionPerformed(ev4);

		Assert.assertEquals("81.0", calf.getResult().getText());
	}

	@Test
	public void test6() {
		CalCFrame calf = new CalCFrame("Tester");

		calf.setClearscreen(false);

		JButton but1 = getButton(calf,5); // get button
		ActionEvent ev = new ActionEvent(but1, 0, null); // generate a click on button
		calf.actionPerformed(ev);

		JButton but2 = getButton(calf,14); // get button
		ActionEvent ev2 = new ActionEvent(but2, 0, null); // generate a click on button
		calf.actionPerformed(ev2);

		Assert.assertEquals("2.0", calf.getResult().getText());
	}

	@Test
	public void test7() {
		CalCFrame calf = new CalCFrame("Tester");

		calf.setClearscreen(true);

		JButton but1 = getButton(calf,15); // get button
		ActionEvent ev = new ActionEvent(but1, 0, null); // generate a click on button
		calf.actionPerformed(ev);

		Assert.assertEquals("0", calf.getResult().getText());
	}

	@Test
	public void test8() {
		CalCFrame calf = new CalCFrame("Tester");

		calf.setClearscreen(false);

		JButton but2 = getButton(calf,17); // get button
		ActionEvent ev2 = new ActionEvent(but2, 0, null); // generate a click on button
		calf.actionPerformed(ev2);

		JButton but3 = getButton(calf,1); // get button
		ActionEvent ev3 = new ActionEvent(but3, 0, null); // generate a click on button
		calf.actionPerformed(ev3);

		JButton but4 = getButton(calf,18); // get button
		ActionEvent ev4 = new ActionEvent(but4, 0, null); // generate a click on button
		calf.actionPerformed(ev4);

		Assert.assertEquals("0.02", calf.getResult().getText());
	}

}