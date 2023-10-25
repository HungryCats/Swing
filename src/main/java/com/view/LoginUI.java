package com.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginUI extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    private JPasswordField textField_1;
    private JComboBox comboBox;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                for (UIManager.LookAndFeelInfo info : UIManager
                        .getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
                LoginUI frame = new LoginUI();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public LoginUI() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 650, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setTitle("欢迎登陆");
        JLabel label = new JLabel("用户名：");
        label.setFont(new Font("黑体", Font.PLAIN, 20));
        label.setBounds(134, 125, 110, 32);
        contentPane.add(label);

        textField = new JTextField();
        textField.setBackground(new Color(192, 192, 192));
        textField.setEditable(false);
        textField.setText("admin");
        textField.setBounds(224, 119, 227, 38);
        contentPane.add(textField);
        textField.setColumns(10);

        JLabel label_1 = new JLabel("密  码：");
        label_1.setFont(new Font("黑体", Font.PLAIN, 20));
        label_1.setBounds(140, 210, 110, 32);
        contentPane.add(label_1);

        textField_1 = new JPasswordField();
        textField_1.setBackground(new Color(192, 192, 192));
        textField_1.setEditable(false);
        textField_1.setText("12345678");
        textField_1.setColumns(10);
        textField_1.setBounds(224, 204, 227, 38);
        contentPane.add(textField_1);

        JButton button = new JButton("登录");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainView mainView = new MainView();
                mainView.setVisible(true);
                dispose();
            }
        });
        button.setFont(new Font("黑体", Font.PLAIN, 20));
        button.setBounds(120, 371, 105, 38);
        contentPane.add(button);

        JButton button_1 = new JButton("注册");
        button_1.setFont(new Font("黑体", Font.PLAIN, 20));
        button_1.setBounds(270, 371, 105, 38);
        contentPane.add(button_1);
        JLabel lblNewLabel = new JLabel("智慧校园管理系统");
        lblNewLabel.setFont(new Font("黑体", Font.PLAIN, 28));
        lblNewLabel.setBounds(208, 28, 497, 55);
        contentPane.add(lblNewLabel);

        JLabel label_2 = new JLabel("角  色：");
        label_2.setFont(new Font("黑体", Font.PLAIN, 20));
        label_2.setBounds(140, 291, 110, 32);
        contentPane.add(label_2);

        comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel(new String[]{"用户", "管理员", "教师"}));
        comboBox.setBounds(224, 291, 227, 32);
        contentPane.add(comboBox);

        JButton button_2 = new JButton("重置");
        button_2.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                textField.setText("");
                textField_1.setText("");
                comboBox.setSelectedIndex(0);
            }
        });
        button_2.setFont(new Font("黑体", Font.PLAIN, 20));
        button_2.setBounds(410, 371, 105, 38);
        contentPane.add(button_2);

        this.setLocationRelativeTo(null);
    }
}
