package com.view.school;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.dao.SchoolDao;
import com.entity.School_information;

/**
 * @author HungryCats
 * @date 2023年10月23日
 */
public class SchoolAdd extends JDialog {
	private JTextField nameField;
	private JTextField webField;
	private JTextField addressField;
	private JTextField typeField;

	School_information sInformation = new School_information();

	SchoolDao schoolDao = new SchoolDao();

	/**
	 * Create the dialog.
	 */
	public SchoolAdd() {
		setModal(true);
		setTitle("添加");
		setBounds(100, 100, 570, 379);

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		JLabel nameLabel = new JLabel("学校名称");
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nameLabel.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 14));
		nameLabel.setBounds(124, 57, 70, 25);
		getContentPane().add(nameLabel);

		JLabel webLabel = new JLabel("学校官网");
		webLabel.setHorizontalAlignment(SwingConstants.CENTER);
		webLabel.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 14));
		webLabel.setBounds(124, 102, 70, 25);
		getContentPane().add(webLabel);

		JLabel addressLabel = new JLabel("学校地址");
		addressLabel.setHorizontalAlignment(SwingConstants.CENTER);
		addressLabel.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 14));
		addressLabel.setBounds(124, 156, 70, 25);
		getContentPane().add(addressLabel);

		JLabel typeLabel = new JLabel("学校类型");
		typeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		typeLabel.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 14));
		typeLabel.setBounds(124, 204, 70, 25);
		getContentPane().add(typeLabel);

		nameField = new JTextField();
		nameField.setColumns(10);
		nameField.setBounds(232, 59, 150, 30);
		getContentPane().add(nameField);

		webField = new JTextField();
		webField.setColumns(10);
		webField.setBounds(232, 106, 150, 30);
		getContentPane().add(webField);

		addressField = new JTextField();
		addressField.setColumns(10);
		addressField.setBounds(232, 158, 150, 30);
		getContentPane().add(addressField);

		typeField = new JTextField();
		typeField.setColumns(10);
		typeField.setBounds(232, 206, 150, 30);
		getContentPane().add(typeField);

		JButton addBtn = new JButton("添加");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String schoolName = nameField.getText();
				String schoolWeb = webField.getText();
				String schoolAddress = addressField.getText();
				String schoolType = typeField.getText().trim();
				sInformation.setSchool_name(schoolName);
				sInformation.setSchool_web_address(schoolWeb);
				sInformation.setSchool_area(schoolAddress);
				if (schoolType.equals("小学")) {
					sInformation.setSchool_type_id(1);
				} else if (schoolType.equals("初中")) {
					sInformation.setSchool_type_id(2);
				} else if (schoolType.equals("高中")) {
					sInformation.setSchool_type_id(3);
				} else if (schoolType.equals("九年一贯制")) {
					sInformation.setSchool_type_id(4);
				} else if (schoolType.equals("完全中学")) {
					sInformation.setSchool_type_id(5);
				} else if (schoolType.equals("十二年一贯制")) {
					sInformation.setSchool_type_id(6);
				} else if (schoolType.equals("幼儿园")) {
					sInformation.setSchool_type_id(7);
				} else if (schoolType.equals("特殊教育")) {
					sInformation.setSchool_type_id(8);
				} else if (schoolType.equals("职校中专校")) {
					sInformation.setSchool_type_id(9);
				} else {
					sInformation.setSchool_type_id(10);
				}
				schoolDao.addData(sInformation);
				JOptionPane.showMessageDialog(null, "添加成功!");
				dispose();
				SchoolView schoolView = new SchoolView();
				schoolView.setVisible(true);
			}
		});
		addBtn.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 12));
		addBtn.setBounds(136, 288, 89, 30);
		getContentPane().add(addBtn);

		JButton cancelBtn = new JButton("取消");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancelBtn.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
		cancelBtn.setBounds(283, 288, 89, 30);
		getContentPane().add(cancelBtn);

		addBtn.setFocusable(false);
		cancelBtn.setFocusable(false);
		setLocationRelativeTo(null);
	}
}
