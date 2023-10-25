package com.view.course;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.dao.Dao;
import com.eltima.components.ui.DatePicker;
import com.entity.School_course_manage;

/**
 * @author HungryCats
 * @date 2023-10-23 01:35:09 
*/
public class CourseUpdate extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField teacherField;
	private JTextField classField;
	private JTextField subjectField;
	private JTextField startField;
	private JButton okButton;
	private JButton cancelButton;

	School_course_manage school_course_manage = new School_course_manage();

	Dao<School_course_manage> courseDao = new Dao<>(school_course_manage);

	/**
	 * Create the dialog.
	 */
	public CourseUpdate(Object[] objects) {
		setModal(true);
		setTitle("修改");
		setBounds(100, 100, 580, 380);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel teacherLabel = new JLabel("教师姓名");
		teacherLabel.setHorizontalAlignment(SwingConstants.CENTER);
		teacherLabel.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));
		teacherLabel.setBounds(95, 11, 100, 35);
		contentPanel.add(teacherLabel);
		{
			JLabel classLabel = new JLabel("班级");
			classLabel.setHorizontalAlignment(SwingConstants.CENTER);
			classLabel.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));
			classLabel.setBounds(95, 57, 100, 35);
			contentPanel.add(classLabel);
		}
		{
			JLabel subjectLabel = new JLabel("学科名称");
			subjectLabel.setHorizontalAlignment(SwingConstants.CENTER);
			subjectLabel.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));
			subjectLabel.setBounds(95, 103, 100, 35);
			contentPanel.add(subjectLabel);
		}
		{
			JLabel startLabel = new JLabel("授课开始时间");
			startLabel.setHorizontalAlignment(SwingConstants.CENTER);
			startLabel.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));
			startLabel.setBounds(95, 154, 100, 35);
			contentPanel.add(startLabel);
		}
		{
			JLabel endLabel = new JLabel("授课结束时间");
			endLabel.setHorizontalAlignment(SwingConstants.CENTER);
			endLabel.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));
			endLabel.setBounds(95, 200, 100, 35);
			contentPanel.add(endLabel);
		}
		{
			JLabel stateLabel = new JLabel("状态");
			stateLabel.setHorizontalAlignment(SwingConstants.CENTER);
			stateLabel.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));
			stateLabel.setBounds(95, 246, 100, 35);
			contentPanel.add(stateLabel);
		}

		teacherField = new JTextField();
		teacherField.setHorizontalAlignment(SwingConstants.LEFT);
		teacherField.setBounds(216, 20, 164, 25);
		contentPanel.add(teacherField);
		teacherField.setColumns(10);

		classField = new JTextField();
		classField.setHorizontalAlignment(SwingConstants.LEFT);
		classField.setColumns(10);
		classField.setBounds(216, 64, 164, 25);
		contentPanel.add(classField);

		subjectField = new JTextField();
		subjectField.setHorizontalAlignment(SwingConstants.LEFT);
		subjectField.setColumns(10);
		subjectField.setBounds(216, 112, 164, 25);
		contentPanel.add(subjectField);

		startField = new JTextField();
		startField.setHorizontalAlignment(SwingConstants.LEFT);
		startField.setColumns(10);
		startField.setBounds(216, 161, 164, 25);
		contentPanel.add(startField);

		DatePicker datepick = new DatePicker(this);
		datepick.setLocation(216, 207);
		datepick.setSize(164, 25);
		datepick.setPattern("yyyy-MM-dd hh:mm:ss");//设置日期格式化字符串
		SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		datepick.setEditorable(false);
		contentPanel.add(datepick);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "课程准备", "授课中", "已结束" }));
		comboBox.setBounds(216, 254, 164, 25);
		contentPanel.add(comboBox);
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("修改");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String teacherName = teacherField.getText();
						Integer className = Integer.parseInt(classField.getText());
						Integer subjectName = Integer.parseInt(subjectField.getText());
						String startTime = startField.getText();
						String endTime = dateFormat.format(datepick.getValue());
						if (comboBox.getSelectedItem() == "课程准备") {
							school_course_manage.setState(1);
						} else if (comboBox.getSelectedItem() == "授课中") {
							school_course_manage.setState(2);
						} else {
							school_course_manage.setState(3);
						}
						school_course_manage.setTeacher_name_id(teacherName);
						school_course_manage.setClasses(className);
						school_course_manage.setSubject_id(subjectName);
						school_course_manage.setTeaching_start_time(startTime);
						school_course_manage.setTeaching_end_time(endTime);
						courseDao.update(school_course_manage);
						dispose();
						CourseView courseView = new CourseView();
						courseView.setVisible(true);
					}
				});
				okButton.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
				okButton.setActionCommand("OK");
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("取消");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
				cancelButton.setActionCommand("Cancel");
			}
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			buttonPane.add(okButton);
			buttonPane.add(cancelButton);
		}
		school_course_manage.setId((Integer) objects[0]);
		teacherField.setText((String) objects[1]);
		classField.setText(objects[2].toString());
		subjectField.setText(objects[3].toString());
		startField.setText((String) objects[4]);
		datepick.setText((String) objects[5]);
		if(objects[6] == "课程准备") {
			comboBox.setSelectedIndex(0);
		}else if (objects[6] == "授课中") {
			comboBox.setSelectedIndex(1);
		}else if (objects[6] == "已结束"){
			comboBox.setSelectedIndex(2);
		}
		
		okButton.setFocusable(false);
		cancelButton.setFocusable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}

}
