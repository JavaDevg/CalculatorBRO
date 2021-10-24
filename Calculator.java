package com.CoolJavaDev;
// Package imports
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
//Main Class
public class CalcBRO implements ActionListener {

    // Variable Declarations

    String[] strings;
    int ind;
    double tmpRes;

    JFrame frame;
    JTextField textField;
    JButton[] numButtons = new JButton[10];
    JButton[] functionButtons = new JButton[8];
    JButton addButton, subButton, multiButton, divButton;
    JButton decButton, equButton, delButton, clrButton;
    JPanel panel;

    Font calcFont = new Font ("Calibri", Font.PLAIN, 30);

    double result = 0;
    char operator;
    ArrayList<Character> operatorsArr = new ArrayList<>();
// Constructor of the class
    CalcBRO() {

        // GUI components initialization

        frame = new JFrame("RKBRO CALCULATOR GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 550);
        frame.setLayout(null);

        textField = new JTextField();
        textField.setBounds(50, 25, 300, 50);
        textField.setFont(calcFont);
        textField.setEditable(false);
        textField.setBorder(BorderFactory.createBevelBorder(1));
        textField.setBackground(Color.yellow);

        addButton = new JButton("+");
        addButton.setBackground(Color.green);
        subButton = new JButton("-");
        subButton.setBackground(Color.green);
        multiButton = new JButton("*");
        multiButton.setBackground(Color.green);
        divButton = new JButton("/");
        divButton.setBackground(Color.green);
        decButton = new JButton(".");
        decButton.setBackground(Color.green);
        equButton = new JButton("=");
        equButton.setBackground(Color.green);
        delButton = new JButton("DEL");
        delButton.setBackground(Color.green);
        clrButton = new JButton("CLEAR");
        clrButton.setBackground(Color.green);

        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = multiButton;
        functionButtons[3] = divButton;
        functionButtons[4] = decButton;
        functionButtons[5] = equButton;
        functionButtons[6] = delButton;
        functionButtons[7] = clrButton;

        for (int i = 0; i < 8; i++) {
            functionButtons[i].setFocusable(false);
            functionButtons[i].addActionListener(this);
            functionButtons[i].setFont(calcFont);
        }

        for (int i = 0; i < 10; i++) {
            numButtons[i] = new JButton(String.valueOf(i));
            numButtons[i].setBackground(Color.red);
            numButtons[i].setFocusable(false);
            numButtons[i].addActionListener(this);
            numButtons[i].setFont(calcFont);
        }

        delButton.setBounds(50, 430, 145, 50);
        clrButton.setBounds(203, 430, 145, 50);

        panel = new JPanel();
        panel.setBounds(50, 100, 300, 300);
        panel.setLayout(new GridLayout(4, 4));
        panel.setBackground(Color.blue);

        panel.add(numButtons[1]);
        panel.add(numButtons[2]);
        panel.add(numButtons[3]);
        panel.add(addButton);

        panel.add(numButtons[4]);
        panel.add(numButtons[5]);
        panel.add(numButtons[6]);
        panel.add(subButton);

        panel.add(numButtons[7]);
        panel.add(numButtons[8]);
        panel.add(numButtons[9]);
        panel.add(multiButton);

        panel.add(decButton);
        panel.add(numButtons[0]);
        panel.add(equButton);
        panel.add(divButton);

        frame.add(panel);
        frame.add(delButton);
        frame.add(clrButton);
        frame.add(textField);
        frame.setVisible (true);

    }
// Main method
    public static void main(String[] args) {
        new CalcBRO();
    }

// Main application logic

    @Override
    public void actionPerformed(ActionEvent e) {

        // Logic For the buttons

        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numButtons[i]) {
                textField.setText(textField.getText().concat(String.valueOf(i)));
            }
        }

        if (e.getSource() == decButton) {
            textField.setText(textField.getText().concat("."));
        }

        if (e.getSource() == addButton) {


            String num = textField.getText();
            operator = '+';
            operatorsArr.add(operator);
            textField.setText(num + operator);
        }

        if (e.getSource() == subButton) {
            String num = textField.getText();
            operator = '-';
            operatorsArr.add(operator);
            textField.setText(num + operator);
        }

        if (e.getSource() == multiButton) {
            String num = textField.getText();
            operator = '*';
            // operatorsArr.add (operator);
            textField.setText(num + operator);
        }

        if (e.getSource() == divButton) {
            String num = textField.getText();
            operator = '/';
            // operatorsArr.add (operator);
            textField.setText(num + operator);
        }

        if (e.getSource() == clrButton) {
            textField.setText("");
        }

        if (e.getSource() == delButton) {

            String string = textField.getText();
            textField.setText("");

            operatorsArr.clear();

            for (int i = 0; i < string.length() - 1; i++) {
                char tmpChar = string.charAt(i);

                if ((tmpChar == '+') || (tmpChar == '-')) {
                    operatorsArr.add(tmpChar);
                }

                textField.setText(textField.getText() + tmpChar);
            }
        }

        if (e.getSource() == equButton) {
            String txt = textField.getText();
            ind = 0;

            strings = txt.split("[+\\-]");
            result = getMul(strings[ind]);

            // Add and Subtract
            for (char operate : operatorsArr) {
                ind++;
                operator = operate;
                switch (operator) {
                    case '+' -> result += getMul(strings[ind]);
                    case '-' -> result -= getMul(strings[ind]);
                    default -> textField.setText("NO NUM2");
                }

            }
            textField.setText(String.valueOf(result));
            operatorsArr.clear();
        }
    }
// For multiplying
    public double getMul(String min) {

        tmpRes = 1;
        int tmpInd = 0;
        if (min.contains("*")) {
            String[] mArr = min.split("\\*");

            for (String ignored : mArr) {
                if (mArr[tmpInd].contains("/")) {
                    tmpRes *= getDiv(mArr[tmpInd]);
                } else
                    tmpRes *= Double.parseDouble(mArr[tmpInd]);
                tmpInd++;
            }
        }else if (min.contains("/")) {
            tmpRes *= getDiv(min);
        }

        else {
            tmpRes = Double.parseDouble(min);
        }
        return tmpRes;
    }
// For Dividing
    public double getDiv(String din) {

        tmpRes = 1;
        int tmpInd = 0;

        if (din.contains("/")) {
            String[] dArr = din.split("/");
            tmpRes = Double.parseDouble(dArr[0]);
            for (int i = 1; i < dArr.length; i++) {
                tmpRes /=  Double.parseDouble(dArr[i]) ;
            }


        } else {
            tmpRes = Double.parseDouble(din);
        }
        return tmpRes;
    }
}
