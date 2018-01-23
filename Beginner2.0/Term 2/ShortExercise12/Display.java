//Display object class to initialise the GUI

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.text.DefaultCaret;

public class Display {
	
	private JSlider xSlider = new JSlider(0, 10, 5);
	private JSlider ySlider = new JSlider(JSlider.VERTICAL, 0, 10, 0);
	private JTextArea textBox = new JTextArea(20, 20);
	private JButton resetButton = new JButton("Reset");
	private Boolean buttonPressed = false;
	public void render(){
		JFrame frame = new JFrame("GUI");
		JPanel border = new JPanel();
		JScrollPane textContainer = new JScrollPane(textBox);
		
		DefaultCaret caret = (DefaultCaret)textBox.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(2, 1));
		border.setLayout(new BorderLayout());

		frame.add(textContainer);
		border.add(xSlider, BorderLayout.NORTH);
		border.add(ySlider, BorderLayout.CENTER);
		
		resetButton.addActionListener(new ButtonListener());
		border.add(resetButton, BorderLayout.SOUTH);
		frame.add(border);
		
		frame.setSize(300,300);
		frame.setVisible(true);
	}
	
	public int getXSlider(){
		return xSlider.getValue();
	}
	public int getYSlider(){
		return ySlider.getValue();
	}
	public void setString(String text){
		textBox.append(text);
	}
	
	public void resetSimulator(){
		textBox.setText(null);
		buttonPressed = false;
	}//END resetSimulator
	public boolean isPressed(){
		return buttonPressed;
	}//END isPressed

	
	class ButtonListener implements ActionListener {
		ButtonListener() {
			//EMPTY CONSTRUCTOR FOR BUTTONLISTENER
		}
		
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("Reset")) {
				buttonPressed = true;
				isPressed();
			}//END if
		}//END actionPerformed
	}//END class ButtonListener

}
