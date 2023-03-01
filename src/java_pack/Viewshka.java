package java_pack;

import java_pack.Types.CartVector2D;
import java_pack.Types.Dble;
import java_pack.Types.UserType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.Vector;

public class Viewshka extends JFrame {
    private Vector<BigList> _vector = new Vector<>();
    private BigList BigList = new BigList();
    private double grade_of_two = 3;

    private Object[] userTypes = {"Double", "Vector2D"};
    private int choosen_userType; // выбор типа данных
    private JButton printList_btn;
    private JButton sortList_btn;
    private JButton pushInList_btn;
    private JButton getOnPosList_btn;
    private JButton insertOnPosList_btn;
    private JButton removeOnPosList_btn;
    private JButton balanceList_btn;
    private JButton exportList_btn;
    private JButton importList_btn;
    private JPanel mainPanel;
    private JTextArea out;

    public Viewshka()
    {
        super("Viewshka");

        getContentPane().setBackground(Color.GRAY);
        getContentPane().setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(600, 500));

        // для расположения основного окна
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int x = (screenSize.width - this.getWidth()) / 2;
        int y = (screenSize.height - this.getHeight()) / 2;
        setLocation(x, y);

        choosen_userType = JOptionPane.showOptionDialog(this,
                "Which user type do you want choose?",
                "Choose 'userType'",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                userTypes,
                null
        );

        generate_list(choosen_userType);



        mainPanel = new JPanel();
        mainPanel.setBounds(0, 0, 600, 500);
        mainPanel.setBackground(Color.GRAY);
        mainPanel.setLayout(null);

        getContentPane().add(mainPanel);

        printList_btn = new JButton("Print List");
        printList_btn.setBounds(200, 30, 200, 30);
        mainPanel.add(printList_btn);
        printList_btn.setFocusable(false);
        out = new JTextArea();
        out.setEditable(false);
        out.setFont(new Font("Consolas", Font.PLAIN, 12));

        printList_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                out.setText(BigList.print_list());

                JOptionPane.showOptionDialog(mainPanel,
                        out,
                        "BigList count: " + BigList.get_count(),
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        null,
                        null
                );
            }
        });

        balanceList_btn = new JButton("Balance List");
        balanceList_btn.setBounds(200, 70, 200, 30);
        mainPanel.add(balanceList_btn);
        balanceList_btn.setFocusable(false);

        balanceList_btn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String Balance_value = (String)JOptionPane.showInputDialog(
                        mainPanel,
                        "Enter size of sublists ",
                        "Balance size",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        null,
                        null
                );

                out.setText(BigList.print_list());

                JOptionPane.showOptionDialog(mainPanel,
                        out,
                        "BigList before balancing",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        null,
                        null
                );

                BigList.balance_list(Integer.parseInt(Balance_value));
                out.setText(BigList.print_list());

                JOptionPane.showOptionDialog(mainPanel,
                        out,
                        "BigList after balancing",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        null,
                        null
                );
            }
        });

        sortList_btn = new JButton("Sort List");
        sortList_btn.setBounds(200, 110, 200, 30);
        mainPanel.add(sortList_btn);
        sortList_btn.setFocusable(false);

        sortList_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                out.setText(BigList.print_list());

                JOptionPane.showOptionDialog(mainPanel,
                        out,
                        "BigList before sorting",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        null,
                        null
                );

                BigList.sort_list();
                out.setText(BigList.print_list());

                JOptionPane.showOptionDialog(mainPanel,
                        out,
                        "BigList after sorting",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        null,
                        null
                );
            }
        });

        pushInList_btn = new JButton("Push in List");
        pushInList_btn.setBounds(200, 150, 200, 30);
        mainPanel.add(pushInList_btn);
        pushInList_btn.setFocusable(false);

        pushInList_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (choosen_userType == 0)
                {
                    int value = ((int) (Math.random() * ((100 - 1) + 1)) + 1) + 1;
                    BigList.push(new Dble(value));
                }
                else if (choosen_userType == 1) {
                    int x = ((int) (Math.random() * ((100 - 1) + 1)) + 1) + 1;
                    int y = ((int) (Math.random() * ((100 - 1) + 1)) + 1) + 1;
                    BigList.push(new CartVector2D(x,y));
                }

                int onlyOneToPush = JOptionPane.showOptionDialog(mainPanel,
                        "Push only one item?",
                        "One or more",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        new String[]{"Yes", "More"},
                        null
                );

                if (onlyOneToPush == 1) {
                    pushInList_btn.doClick();
                }
            }
        });

        getOnPosList_btn = new JButton("Get on position");
        getOnPosList_btn.setBounds(200, 190, 200, 30);
        mainPanel.add(getOnPosList_btn);
        getOnPosList_btn.setFocusable(false);

        getOnPosList_btn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String choosenPosList = (String)JOptionPane.showInputDialog(
                        mainPanel,
                        "Choose position for get item",
                        "Count of List: " + BigList.inner_count(),
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        null,
                        "Position must have a value between 1 and " + BigList.inner_count()
                );

                out.setText(BigList.get_item_on_position(Integer.parseInt(choosenPosList)).toString());

                JOptionPane.showOptionDialog(mainPanel,
                        out,
                        "Item on position: " + choosenPosList,
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        null,
                        null
                );
            }
        });

        insertOnPosList_btn = new JButton("Insert on position");
        insertOnPosList_btn.setBounds(200, 230, 200, 30);
        mainPanel.add(insertOnPosList_btn);
        insertOnPosList_btn.setFocusable(false);

        insertOnPosList_btn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String choosenPosList = (String)JOptionPane.showInputDialog(
                        mainPanel,
                        "Choose position to insert item",
                        "Count of List: " + BigList.inner_count(),
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        null,
                        "Position must have a value between 1 and " + BigList.inner_count()
                );

                if (choosen_userType == 0)
                {
                    String data_for_insert = (String)JOptionPane.showInputDialog(
                            mainPanel,
                            "Enter data for insert item",
                            "Count of List: " + BigList.inner_count(),
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            null,
                            "Insert data in format (value) without '()'"
                    );

                    BigList.insert_item_on_position(Integer.parseInt(choosenPosList), (UserType) UserFactory.get_builder_by_name("Int").parse_value(data_for_insert));
                }
                else if (choosen_userType == 1) {
                    String data_for_insert = (String)JOptionPane.showInputDialog(
                            mainPanel,
                            "Enter data for insert item",
                            "Count of List: " + BigList.inner_count(),
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            null,
                            "Insert data in format (value;value) without '()'"
                    );

                    BigList.insert_item_on_position(Integer.parseInt(choosenPosList), (UserType) UserFactory.get_builder_by_name("Vector2D").parse_value(data_for_insert));
                }
            }
        });

        removeOnPosList_btn = new JButton("Remove on position");
        removeOnPosList_btn.setBounds(200, 270, 200, 30);
        mainPanel.add(removeOnPosList_btn);
        removeOnPosList_btn.setFocusable(false);

        removeOnPosList_btn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String choosenPosList = (String)JOptionPane.showInputDialog(
                        mainPanel,
                        "Choose position for insert item",
                        "Count of List: " + BigList.inner_count(),
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        null,
                        "Position must have a value between 1 and " + BigList.inner_count()
                );

                BigList.remove_item_on_position(Integer.parseInt(choosenPosList));
            }
        });

        exportList_btn = new JButton("To serialize");
        exportList_btn.setBounds(200, 310, 200, 30);
        mainPanel.add(exportList_btn);
        exportList_btn.setFocusable(false);

        exportList_btn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (choosen_userType == 0)
                {
                    try {
                        Serializator.saveToFile(BigList, "src/java_pack/test_double.txt", UserFactory.get_type_name_list().get(0));
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                else if (choosen_userType == 1) {

                    try {
                        Serializator.saveToFile(BigList, "src/java_pack/test_vector2d.txt", UserFactory.get_type_name_list().get(1));
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        importList_btn = new JButton("Serialize back");
        importList_btn.setBounds(200, 350, 200, 30);
        mainPanel.add(importList_btn);
        importList_btn.setFocusable(false);

        importList_btn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                BigList.remove_list();

                if (choosen_userType == 0)
                {
                    try {
                        BigList = Serializator.loadFile("src/java_pack/test_double.txt");
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
                else if (choosen_userType == 1)
                {
                    try {
                        BigList = Serializator.loadFile("src/java_pack/test_vector2d.txt");
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new Viewshka();
    }

    public void generate_list(int user_type) {
        System.out.println(user_type);

        if (UserFactory.get_type_name_list().get(user_type).equals("Double")) {
            System.out.println(UserFactory.get_type_name_list().get(user_type));

            for (int j = 0; j < 3; j++) {
                BigList.push(new SmallList());

                for (int i = 0; i < 3; i++) {
                    int value = ((int) (Math.random() * ((100 - 1) + 1)) + 1) + 1;
                    BigList.push(new Dble(value));
                }
            }
        } else if (UserFactory.get_type_name_list().get(user_type).equals("Vector2D")) {
            System.out.println(UserFactory.get_type_name_list().get(user_type));

            for (int j = 0; j < 3; j++) {
                BigList.push(new SmallList());

                for (int i = 0; i < 3; i++) {
                    int x = ((int) (Math.random() * ((100 - 1) + 1)) + 1) + 1;
                    int y = ((int) (Math.random() * ((100 - 1) + 1)) + 1) + 1;
                    BigList.push(new CartVector2D(x, y));
                }
            }
        } else {
            System.out.println("Not available type of data");
        }
//        _vector.add(scala_pack.BigList);
//
//        if (userType == 0)
//        {
//            for (int j = 0; j < 8; j++) //(int)Math.pow(2,((int)(Math.random()*((4-1)+1))+1))
//            {
//                _vector.add(new scala_pack.BigList<Integer>());
//
//                for (int i = 0; i < ((int)(Math.random()*((10-1)+1))+1); i++)
//                {
//                    _vector.lastElement().push(((int)(Math.random()*((10-1)+1))+1) + 1);
//                }
//
//                scala_pack.BigList.push(_vector.lastElement());
//            }
//        }
//
//        if (userType == 1)
//        {
//            for (int j = 0; j < 8; j++)
//            {
//                _vector.add(new scala_pack.BigList<Double>());
//
//                for (int i = 0; i < ((int)(Math.random()*((10-1)+1))+1); i++)
//                {
//                    _vector.lastElement().push((Math.random()*((10-1)+1))+1);
//                }
//
//                scala_pack.BigList.push(_vector.lastElement());
//            }
//        }
    }
}