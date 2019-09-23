package gui;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class FormPanel extends JPanel {
	
	private JLabel nameLabel;
	private JLabel occupationLabel;
	private JTextField nameField;
	private JTextField occupationField;
	private JButton okBtn;
	private FormListener formListener;
	private JList ageList;
	private DefaultListModel ageModel;
	private JComboBox empCategory;
	private DefaultComboBoxModel empModel;
	private JCheckBox citizenCheck;
	private JTextField taxId;
	private JLabel taxLabel;
	private JRadioButton maleButton;
	private JRadioButton femaleButton;
	private ButtonGroup genderGroup;
	
	public FormPanel() {
		Dimension dim = getPreferredSize();
		dim.width = 250;
		setPreferredSize(dim);
		
		//Setting components
		nameLabel = new JLabel("Name:");
		occupationLabel = new JLabel("Occupation:");
		nameField = new JTextField(10);
		occupationField = new JTextField(10);
		okBtn = new JButton("OK");
		citizenCheck = new JCheckBox();
		taxId = new JTextField(10);
		taxLabel = new JLabel("Tax ID:");
		maleButton = new JRadioButton("male");
		femaleButton = new JRadioButton("female");
		genderGroup = new ButtonGroup();
		
		//Set mnemonics
		okBtn.setMnemonic(KeyEvent.VK_O);
		
		nameLabel.setDisplayedMnemonic(KeyEvent.VK_N);
		nameLabel.setLabelFor(nameField);
		
		//Set genderGroup
		genderGroup.add(maleButton);
		genderGroup.add(femaleButton);
		
		maleButton.setSelected(true);
		
		maleButton.setActionCommand("male");
		femaleButton.setActionCommand("female");
		
		//Set tax box
		taxId.setEnabled(false);
		taxLabel.setEnabled(false);
		
		citizenCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean isChecked = citizenCheck.isSelected();
				taxId.setEnabled(isChecked);
				taxLabel.setEnabled(isChecked);
			}	
		});
		
		///Set ageList
		ageModel = new DefaultListModel();
		ageModel.addElement(new AgeCategory(0, "under 18"));
		ageModel.addElement(new AgeCategory(1,"from 18 to 65"));
		ageModel.addElement(new AgeCategory(2,"65 or over"));
		
		ageList = new JList(ageModel);
		ageList.setBorder(BorderFactory.createEtchedBorder());
		ageList.setPreferredSize(new Dimension(105,70));
		ageList.setSelectedIndex(1);
		
		///Set Employment Category
		empModel = new DefaultComboBoxModel();
		empModel.addElement("employed");
		empModel.addElement("self-employed");
		empModel.addElement("unemployed");
		
		empCategory = new JComboBox();
		empCategory.setModel(empModel);
		empCategory.setSelectedIndex(0);
		
		
		okBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				FormEvent ev = new FormEvent(e);
				if(ev != null) {
					ev.setName(nameField.getText());
					ev.setOccupation(occupationField.getText());
					ev.setEmploymentCategory((String)empCategory.getSelectedItem());
					
					ev.setIsDanishCitizen(citizenCheck.isSelected());{
					if(ev.isDanishCitizen())
						ev.setTaxID(taxId.getText());
					else
						ev.setTaxID("");
					}
					
					AgeCategory ageCat = (AgeCategory)ageList.getSelectedValue();	
					ev.setAgeCategory(ageCat.getId());
					ev.setGender(genderGroup.getSelection().getActionCommand());
					
					formListener.textFormListener(ev);					
				}
			}
			
		});
		
		addComponents();
	}
	
	public void setFormListener(FormListener listener) {
		this.formListener = listener;
	}
	
	private void addComponents() {
		Border insideBorder = BorderFactory.createTitledBorder("Add Person");
		Border outsideBorder = BorderFactory.createEmptyBorder(5,5,5,5);
		
		setBorder(BorderFactory.createCompoundBorder(outsideBorder, insideBorder));
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		///////////////First Row///////////////
		gc.gridy = 0;
		
		gc.gridx = 0;
		gc.insets = new Insets(0,0,0,10);
		gc.anchor = GridBagConstraints.LINE_END;
		add(nameLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		add(nameField, gc);
		
		///////////////Next Row///////////////
		gc.gridy++;
		
		gc.gridx = 0;
		gc.insets = new Insets(0,0,0,10);
		gc.anchor = GridBagConstraints.LINE_END;
		add(occupationLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		add(occupationField, gc);
		
		///////////////Next Row///////////////
		gc.gridy++;
		gc.weighty = 0.2;
		
		gc.gridx = 0;
		gc.insets = new Insets(0,0,0,10);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(new JLabel("Age:"), gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(ageList, gc);		
		
		///////////////Next Row///////////////
		gc.gridy++;
		
		gc.gridx = 0;
		gc.insets = new Insets(0,0,0,10);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(new JLabel("Employment:"), gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(empCategory, gc);
		
		///////////////Next Row///////////////
		gc.gridy++;
		
		gc.gridx = 0;
		gc.insets = new Insets(0,0,0,10);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(new JLabel("Danish Citizen:"), gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(citizenCheck, gc);
		
		///////////////Next Row///////////////
		gc.gridy++;
		
		gc.gridx = 0;
		gc.insets = new Insets(0,0,0,10);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(taxLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(taxId, gc);
		
		///////////////Next Row///////////////
		gc.gridy++;
		gc.weighty = 0.01;
		
		gc.gridx = 0;
		gc.insets = new Insets(0,0,0,10);
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(new JLabel("Gender:"), gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(maleButton, gc);
		
		///////////////Next Row///////////////
		gc.gridy++;
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(femaleButton, gc);
		
		///////////////Next Row///////////////
		gc.gridy++;
		
		gc.weighty = 2;
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(okBtn, gc);		
	}
}

class AgeCategory{
	private String text;
	private int id;
	
	public AgeCategory(int id, String text) {
		this.text = text;
		this.id = id;
	}
	
	public String toString() {
		return text;
	}
	
	public int getId() {
		return id;
	}
	
}