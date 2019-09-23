package gui;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import controller.Controller;


public class MainFrame extends JFrame{
	
	private TextPanel textPanel;
	private ToolBar toolBar;
	private FormPanel formPanel;
	private JMenuBar menuBar;
	private JFileChooser fileChooser;
	private Controller controller;
	private TablePanel tablePanel;
	
	public MainFrame() {
		super("Hello World");
		setLayout(new BorderLayout());
		setSize(600, 500);
		setMinimumSize(new Dimension(400, 400));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		textPanel = new TextPanel();
		toolBar = new ToolBar();
		formPanel = new FormPanel();
		menuBar = createMenuBar();
		fileChooser = new JFileChooser();
		
		controller = new Controller();
		tablePanel = new TablePanel();
		tablePanel.setData(controller.getPeople());

		tablePanel.setPersonTableListener(new PersonTableListener(){
			public void deleteRow(int row) {
				controller.deleteRow(row);
			}
		});
		
		//setting fileChooser
		fileChooser.addChoosableFileFilter(new PersonFileFilter());
		
		
		add(tablePanel, BorderLayout.CENTER);
		add(toolBar, BorderLayout.NORTH);
		add(formPanel, BorderLayout.WEST);
		setJMenuBar(menuBar);
	
		toolBar.setStringListener(new StringListener() {
			public void textEmitted(String text) {
				textPanel.appendText(text);			
			}
		});
		
		formPanel.setFormListener(new FormListener() {
			public void textFormListener(FormEvent ev) {
				controller.addPerson(ev);
				//textPanel.appendText(ev.toString());

				tablePanel.refresh();
			}
			
		});
}

	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		
		JMenu fileMenu = new JMenu("File");
		JMenuItem fileExportItem = new JMenuItem("Export Data ...");
		JMenuItem fileImportItem = new JMenuItem("Import Data ...");
		JMenuItem fileExitItem = new JMenuItem("Exit");
		fileMenu.add(fileExportItem);
		fileMenu.add(fileImportItem);
		fileMenu.add(fileExitItem);
		menuBar.add(fileMenu);
		
		JMenu windowMenu = new JMenu("Window");
		JMenu showItem = new JMenu("Show");
		JCheckBoxMenuItem showFormItem = new JCheckBoxMenuItem("Personal form");
		showFormItem.setSelected(true);
		showItem.add(showFormItem);
		windowMenu.add(showItem);
		menuBar.add(windowMenu);
		
		showFormItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem)e.getSource();
				
				formPanel.setVisible(menuItem.isSelected());
			}
			
		});
		
		//set mnemonics and accelerator
		fileMenu.setMnemonic(KeyEvent.VK_F);
		fileExitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		fileImportItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
		
		fileImportItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
					try {
						controller.loadFromFile(fileChooser.getSelectedFile());
						tablePanel.refresh();
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(MainFrame.this, "Could not load data from file" , "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}		
		});
		
		fileExportItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
					try {
						controller.saveToFile(fileChooser.getSelectedFile());
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(MainFrame.this, "Could not save data to file" , "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}		
		});
		
		fileExitItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				JOptionPane.showInputDialog(MainFrame.this, "Enter your username", 
						"Enter username" , JOptionPane.OK_OPTION|JOptionPane.PLAIN_MESSAGE);
				
				int actionExit = JOptionPane.showConfirmDialog(MainFrame.this, "Do you really want to exit", 
						"Confirm EXIT" , JOptionPane.OK_CANCEL_OPTION);
				
				if(actionExit == JOptionPane.OK_OPTION) {
					System.exit(0);
				}
			}
			
		});
		
		
		return menuBar;
	}
	
}

