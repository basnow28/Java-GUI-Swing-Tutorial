package gui;
import java.awt.FlowLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ToolBar extends JPanel implements ActionListener {
	
	private JButton helloButton;
	private JButton goodbyeButton;
	private StringListener stringListener;
	
	public ToolBar() {
		helloButton = new JButton("Hello");
		goodbyeButton = new JButton("Goodbye");
		
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		add(helloButton);
		add(goodbyeButton);
		
		helloButton.addActionListener(this);
		goodbyeButton.addActionListener(this);
	}
	
	public void setStringListener(StringListener listener) {
		this.stringListener = listener;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton clicked = (JButton)e.getSource();
		
		if(clicked == helloButton) {
			
			if(stringListener != null) {
				stringListener.textEmitted("Hello \n");
			}
		}else if(clicked == goodbyeButton) {
			if(stringListener != null) {
				stringListener.textEmitted("GoodBye \n");
			}
		}
		
	}
}
