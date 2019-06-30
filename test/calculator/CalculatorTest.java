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

	int[] buttons;
	String exResult;
	boolean clearscreen;
	CalCFrame calf;

	public CalculatorTest(int[] buttons, String exResult, boolean clearscreen){
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

		data.add(new Object[]{new Integer[]{0, 3, 1}, "3.0", false});
		data.add(new Object[]{new Integer[]{2, 4, 5}, "-1.0", false});
		data.add(new Object[]{new Integer[]{6, 8, 7}, "30.0", true});
		data.add(new Object[]{new Integer[]{10, 9, 11}, "0.875", false});
		data.add(new Object[]{new Integer[]{12, 15}, "0.0", false});
		data.add(new Object[]{new Integer[]{12, 15}, "0.0", false});
		return data;
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


}