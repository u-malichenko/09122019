package Lesson_4;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;

public class MyWindowsMetodichka extends JFrame {
    public MyWindowsMetodichka () {

        /**
         * BorderLayout
         */
//        setTitle ( "Test Window" );
//        setDefaultCloseOperation ( WindowConstants . EXIT_ON_CLOSE );
//        setBounds ( 300 , 300 , 400 , 400 );
//        JButton [] jbs = new JButton [ 5 ];
//        for ( int i = 0 ; i < 5 ; i ++) {
//            jbs [ i ] = new JButton ( "#" + i );
//        }
//        setLayout ( new BorderLayout ()); // выбор компоновщика элементов
//        add ( jbs [ 0 ], BorderLayout . EAST ); // добавление кнопки на форму
//        add ( jbs [ 1 ], BorderLayout . WEST );
//        add ( jbs [ 2 ], BorderLayout. SOUTH );
//        add ( jbs [ 3 ], BorderLayout . NORTH );
//        add ( jbs [ 4 ], BorderLayout . CENTER );
//        setVisible ( true );


//        setTitle ( "Test Window" );
//        setDefaultCloseOperation ( WindowConstants . EXIT_ON_CLOSE );
//        setBounds ( 300 , 300 , 400 , 400 );
//        JButton button = new JButton ( "Button 1 (PAGE_START)" );
//        add ( button , BorderLayout . PAGE_START );
//        button = new JButton ( "Button 2 (CENTER)" );
//        button . setPreferredSize ( new Dimension ( 200 , 100 ));
//        add ( button , BorderLayout . CENTER );
//        button = new JButton ( "Button 3 (LINE_START)" );
//        add ( button , BorderLayout . LINE_START );
//        button = new JButton ( "Long-Named Button 4 (PAGE_END)" );
//        add ( button , BorderLayout . PAGE_END );
//        button = new JButton ( "5 (LINE_END)" );
//        add ( button , BorderLayout . LINE_END );
//        setVisible ( true );
        /**
         * BoxLayout
         */
//        setBounds ( 500 , 500 , 500 , 300 );
//        setTitle ( "BoxLayoutDemo" );
//        setDefaultCloseOperation ( WindowConstants . EXIT_ON_CLOSE );
//        JButton [] jbs = new JButton [ 10 ];
//        //setLayout ( new BoxLayout ( getContentPane (), BoxLayout . Y_AXIS )); // одну из строк надо закомментировать
//        setLayout ( new BoxLayout ( getContentPane (), BoxLayout . X_AXIS )); // одну из строк надо закомментировать
//        for ( int i = 0 ; i < jbs . length ; i ++) {
//            jbs [ i ] = new JButton ( "#" + i );
//            jbs [ i ]. setAlignmentX ( CENTER_ALIGNMENT );
//            add ( jbs [ i ]);
//        }
//        setVisible ( true );
        /**
         * FlowLayout
         */
//        setBounds ( 500 , 500 , 400 , 300 );
//        setTitle ( "FlowLayoutDemo" );
//        setDefaultCloseOperation ( WindowConstants . EXIT_ON_CLOSE );
//        JButton [] jbs = new JButton [ 10 ];
//        setLayout ( new FlowLayout ());
//        for ( int i = 0 ; i < jbs . length ; i ++) {
//            jbs [ i ] = new JButton ( "#" + i );
//            add ( jbs [ i ]);
//        }
//        setVisible ( true );
        /**
         * GridLayout
         */
//        setBounds ( 500 , 500 , 400 , 300 );
//        setTitle ( "GridLayoutDemo" );
//        setDefaultCloseOperation ( WindowConstants . EXIT_ON_CLOSE );
//        JButton [] jbs = new JButton [ 10 ];
//        setLayout ( new GridLayout ( 4 , 3 ));
//        for ( int i = 0 ; i < jbs . length ; i ++) {
//            jbs [ i ] = new JButton ( "#" + i );
//            add ( jbs [ i ]);
//        }
//        setVisible ( true );
        /**
         * В первом блоке кода настраиваем параметры окна и создаём четыре панели для размещения
         * элементов, при этом задаём им отличный друг от друга цвет. Располагаем эти панели на форме в
         * виде таблицы (2, 2), с помощью GridLayout.
         */
        setBounds ( 500 , 200 , 800 , 600 );
        setTitle ( "GUI Demo" );
        setDefaultCloseOperation ( WindowConstants . EXIT_ON_CLOSE );
        setLayout ( new GridLayout ( 2 , 2 ));
        JPanel [] jp = new JPanel [ 4 ];
        for ( int i = 0 ; i < 4 ; i ++) {
            jp [ i ] = new JPanel ();
            add ( jp [ i ]);
            jp [ i ]. setBackground ( new Color ( 100 + i * 40 , 100 + i * 40 , 100 + i * 40 ));
        }
        /**
         * На первой панели расположено многострочное текстовое поле, которое находится внутри элемента
         * JScrollPane, что позволяет пролистывать контент этого поля.
         */
        jp [ 0 ]. setLayout ( new BorderLayout ());
        JTextArea jta = new JTextArea ();
        JScrollPane jsp = new JScrollPane ( jta );
        jp [ 0 ]. add ( jsp, BorderLayout . CENTER ); //на панель добавляем скролл в который завернуто текстовое поле

        /**
         * При работе с текстовым полем (TextField) ActionListener отлавливает нажатие кнопки Enter, конечно,
         * только в случае, если поле находится в фокусе. Поэтому нет необходимости отслеживать именно
         * нажатие кнопки Enter, например, через addKeyListener(…) с указанием кода этой клавиши.
         */
        JTextField field = new JTextField ();
        jp [ 0 ].add ( field , BorderLayout . PAGE_END);
        field . addActionListener ( new ActionListener () {
            @Override
            public void actionPerformed ( ActionEvent e ) {
                System . out . println ( "Your message: " + field . getText ());
                jta.append(field . getText ()+"\n");
                field . setText("");
            }
        });

        /**
         * Во второй панели содержится 2 типа элементов: JCheckBox и JRadioButton. JCheckBox предназначен
         * для вкл/выкл каких-либо опций (флажков), при этом одновременно может быть включено несколько
         * JCheckBox. JRadioButton предоставляет выбор только одного пункта из набора, т.е. может быть
         * выбрана только одна опция. Для корректной работы связанных RadioButton их необходимо заносить в
         * ButtonGroup.
         */
        jp [ 1 ]. setLayout ( new FlowLayout ());
        JRadioButton [] jrb = new JRadioButton [ 4 ];
        ButtonGroup bgr = new ButtonGroup ();
        for ( int i = 0 ; i < jrb . length ; i ++) {
            jrb [ i ] = new JRadioButton ( "Option #" + i );
            bgr . add ( jrb [ i ]); // добавить в группу кнопок
            jp [ 1 ]. add ( jrb [ i ]); //добавить на панель массив кнопок
        }
        JCheckBox [] jcb = new JCheckBox [ 4 ];
        for ( int i = 0 ; i < jcb . length ; i ++) {
            jcb [ i ] = new JCheckBox ( "Check #" + i );
            jp [ 1 ]. add ( jcb [ i ]);
        }

        /**
         * На третьей панели расположена пара элементов типа JComboBox, которые представляют собой
         * выпадающие списки. ActionListener для JComboBox проверяет событие выбора пользователем одного
         * из пунктов.
         */
        jp [ 2 ]. setLayout ( new BorderLayout());
        String [] comboStr = { "Item #1" , "Item #2" , "Item #3" , "Item #4" }; //масив элементов для списков
        JComboBox < String > jcombo1 = new JComboBox < String >( comboStr ); //создаем выпадающий список из масива стрингов comboStr
        JComboBox < String > jcombo2 = new JComboBox < String >( comboStr );
        JPanel jp1 = new JPanel();
        jp [ 2 ]. add(jp1, BorderLayout . NORTH);
        jp1.setLayout( new GridLayout ( 1 , 2 ));
        jp1. add ( jcombo1);
        jp1. add ( jcombo2);

        jcombo1 . addActionListener ( new ActionListener() {
            @Override
            public void actionPerformed ( ActionEvent e ) {
                System . out . println ( jcombo1 . getSelectedItem (). toString ());
                jta.append(jcombo1 . getSelectedItem (). toString ()+"\n");
            }
        });
        jcombo2 . addActionListener ( new ActionListener() {
            @Override
            public void actionPerformed ( ActionEvent e ) {
                System . out . println ( jcombo2 . getSelectedItem (). toString ());
                jta.append(jcombo2 . getSelectedItem (). toString ()+"\n");
            }
        });

        /**
         *В приведённом примере отслеживание отжатия кнопки мыши над объектом типа JPanel. В объект e
         * типа MouseEvent попадает вся информацию о произошедшем событии, в том числе, координатах
         * курсора в системе координат панели, кнопке, которая была нажата (левая или правая), количестве
         * кликов (одинарный, двойной) и т.д.
         */
        JPanel panel = new JPanel ();
        jp [ 2 ]. add ( panel, BorderLayout.CENTER );
        panel . addMouseListener ( new MouseAdapter() {
            @Override
            public void mouseReleased ( MouseEvent e ) {
                System . out . println ( "MousePos: " + e . getX () + " " + e . getY ());
                jta.append("MousePos: " + e . getX () + " " + e . getY ()+"\n");
            }
        });

        /**
         * Четвёртая панель представляет собой пример расстановки элементов с использованием абсолютных
         * координат. На ней расположено обычное нередактируемое текстовое поле, которое показывает
         * значение, выбранное на JSlider.
         */
        jp [ 3 ]. setLayout ( null );
        JSlider js = new JSlider ();
        JLabel jlab = new JLabel ( "Value: 50" );
        js . setMaximum ( 100 );
        js . setMinimum ( 0 );
        js . setValue ( 50 );
        jp [ 3 ]. add ( jlab );
        jp [ 3 ]. add ( js );

        js . addChangeListener ( new ChangeListener() {
            @Override
            public void stateChanged ( ChangeEvent e ) {
                jlab . setText ( "Value: " + js . getValue ());
                jta.append("Value: " + js . getValue ()+"\n");
            }
        });
        jlab . setBounds ( 10 , 10 , 100 , 20 );
        js . setBounds ( 20 , 40 , 300 , 100 );
        js . setBackground ( new Color ( 160 , 255 , 160 ));

        /**
         * Последним пунктом идёт создание верхнего меню окна. Для этого создаются элементы JMenuBar ->
         * JMenu -> JMenuItem. Как видно из кода ниже, для обработки нажатия на один из подпунктов меню
         * достаточно «повесить» на него ActionListener.
         */
        JMenuBar mainMenu = new JMenuBar ();
        JMenu mFile = new JMenu ( "File" );
        JMenu mEdit = new JMenu ( "Edit" );
        JMenuItem miFileNew = new JMenuItem ( "New" );
        JMenuItem miFileExit = new JMenuItem ( "Exit" );
        setJMenuBar ( mainMenu );
        mainMenu . add ( mFile );
        mainMenu . add ( mEdit );
        mFile . add ( miFileNew );
        mFile . addSeparator (); // разделительная линия в меню
        mFile . add ( miFileExit );

        miFileExit . addActionListener ( new ActionListener () { //навешиваем слушателя на меню экзит
            @Override
            public void actionPerformed ( ActionEvent e ) {
                System . exit ( 0 );
            }
        });

        /**
         * Чтобы выполнить какое-либо действие при закрытии окна, необходимо к объекту типа JFrame
         * добавить WindowListener. В примере ниже ссылка на объект отсутствует, так как метод прописан в
         * конструкторе класса, наследуемого от JFrame.
         */
        addWindowListener ( new WindowAdapter() { //лисенер кнопки закрытия окна
            @Override
            public void windowClosing ( WindowEvent e ) {
                System . out . println ( "BYE" );
            }
        });

        /**
         * Для отслеживания кликов по кнопке необходимо добавить ActionListener, как показано ниже. Как
         * только произойдет событие нажатия этой кнопки, выполнится метод actionPerformed().
         */
        JButton button = new JButton ( "Button" );
        jp [ 1 ]. add ( button );
        button . addActionListener ( new ActionListener () {
            @Override
            public void actionPerformed ( ActionEvent e ) {
                System . out . println ( "Button pressed..." );
                jta.append("Button pressed..."+"\n");
            }
        });




        setVisible ( true );
    }
}

class MainClassS {
    public static void main(String[] args) {
        new MyWindowsMetodichka();
    }
}