package com.view.school;

import java.awt.Color;
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
 * @date 2023年10月24日
 */
public class SchoolUpdate extends JDialog {
	private JTextField idField;
	private JTextField nameField;
	private JTextField webField;
	private JTextField addressField;
	private JTextField typeField;

	School_information sInformation = new School_information();
	
	SchoolDao schoolDao = new SchoolDao();

	/**
	 * Create the dialog.
	 */
	public SchoolUpdate(Object[] objects) {
		getContentPane().setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
		setModal(true);
		setTitle("修改");
		setBounds(100, 100, 580, 380);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		JLabel idLabel = new JLabel("编号");
		idLabel.setHorizontalAlignment(SwingConstants.CENTER);
		idLabel.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 14));
		idLabel.setBounds(133, 11, 70, 30);
		getContentPane().add(idLabel);

		JLabel nameLabel = new JLabel("学校名称");
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nameLabel.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 14));
		nameLabel.setBounds(133, 68, 70, 30);
		getContentPane().add(nameLabel);

		JLabel webLabel = new JLabel("学校官网");
		webLabel.setHorizontalAlignment(SwingConstants.CENTER);
		webLabel.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 14));
		webLabel.setBounds(133, 120, 70, 30);
		getContentPane().add(webLabel);

		JLabel addressLabel = new JLabel("学校地址");
		addressLabel.setHorizontalAlignment(SwingConstants.CENTER);
		addressLabel.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 14));
		addressLabel.setBounds(133, 171, 70, 30);
		getContentPane().add(addressLabel);

		JLabel typeLabel = new JLabel("学校类型");
		typeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		typeLabel.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 14));
		typeLabel.setBounds(133, 224, 70, 30);
		getContentPane().add(typeLabel);

		idField = new JTextField();
		idField.setBackground(new Color(192, 192, 192));
		idField.setEditable(false);
		idField.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
		idField.setBounds(255, 13, 150, 30);
		getContentPane().add(idField);
		idField.setColumns(10);

		nameField = new JTextField();
		nameField.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
		nameField.setColumns(10);
		nameField.setBounds(255, 70, 150, 30);
		getContentPane().add(nameField);

		webField = new JTextField();
		webField.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
		webField.setColumns(10);
		webField.setBounds(255, 122, 150, 30);
		getContentPane().add(webField);

		addressField = new JTextField();
		addressField.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
		addressField.setColumns(10);
		addressField.setBounds(255, 173, 150, 30);
		getContentPane().add(addressField);

		typeField = new JTextField();
		typeField.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
		typeField.setColumns(10);
		typeField.setBounds(255, 226, 150, 30);
		getContentPane().add(typeField);

		JButton updateBtn = new JButton("修改");
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer id = Integer.parseInt(idField.getText());
				String name = nameField.getText();
				String web = webField.getText();
				String address = addressField.getText();
				String type = typeField.getText();
				sInformation.setId(id);
				sInformation.setSchool_name(name);
				sInformation.setSchool_web_address(web);
				sInformation.setSchool_area(address);
				if (type.equals("小学")) {
					sInformation.setSchool_type_id(1);
				} else if (type.equals("初中")) {
					sInformation.setSchool_type_id(2);
				} else if (type.equals("高中")) {
					sInformation.setSchool_type_id(3);
				} else if (type.equals("九年一贯制")) {
					sInformation.setSchool_type_id(4);
				} else if (type.equals("完全中学")) {
					sInformation.setSchool_type_id(5);
				} else if (type.equals("十二年一贯制")) {
					sInformation.setSchool_type_id(6);
				} else if (type.equals("幼儿园")) {
					sInformation.setSchool_type_id(7);
				} else if (type.equals("特殊教育")) {
					sInformation.setSchool_type_id(8);
				} else if (type.equals("职校中专校")) {
					sInformation.setSchool_type_id(9);
				} else if (type.equals("全日制本科")) {
					sInformation.setSchool_type_id(10);
				}
				schoolDao.updateData(sInformation);
				JOptionPane.showMessageDialog(null, "修改成功!");
				dispose();
				SchoolView schoolView = new SchoolView();
				schoolView.setVisible(true);
			}
		});
		updateBtn.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
		updateBtn.setBounds(144, 284, 89, 30);
		getContentPane().add(updateBtn);

		JButton cancelBtn = new JButton("取消");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				SchoolView schoolView = new SchoolView();
				schoolView.setVisible(true);
			}
		});
		
		idField.setText(objects[0].toString());
		nameField.setText((String) objects[1]);
		webField.setText((String) objects[2]);
		addressField.setText((String) objects[3]);
		typeField.setText((String) objects[4]);
		
		cancelBtn.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 12));
		cancelBtn.setBounds(297, 284, 89, 30);
		getContentPane().add(cancelBtn);
		setLocationRelativeTo(null);
	}
}
