import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

class GUI extends JFrame{
    static final DefaultListModel genap = new DefaultListModel();
    static final JList list = new JList(genap);
    private final JButton btnUp = new JButton("UpLoad");
    private final JButton btnEnd = new JButton("END");
    public GUI(){
        setTitle("Client GUI");
        setSize(350,300);
        setLocation(new Point(250,250));
        setLayout(null);
        setResizable(false);
        initComponent();
        initEvent();
    }

    private void initComponent(){
        list.setBounds(0,0,350,250);
        btnEnd.setBounds(270, 250, 75, 23);
        btnUp.setBounds(185, 250, 85, 23);
        add(list);
        add(btnEnd);
        add(btnUp);
    }

    private void initEvent(){
        this.addWindowListener(new WindowAdapterImpl());
        btnEnd.addActionListener(this::btnEndClick);
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                System.out.println(list.getSelectedValue().toString());
            }
        });
        genap.addListDataListener(new ListDataListener() {
            @Override
            public void intervalAdded(ListDataEvent e) {

            }

            @Override
            public void intervalRemoved(ListDataEvent e) {

            }

            @Override
            public void contentsChanged(ListDataEvent e) {

            }
        });
    }
    private void btnEndClick(ActionEvent evt){
        System.exit(0);
    }

    private static class WindowAdapterImpl extends WindowAdapter {

        public WindowAdapterImpl() {
        }

        @Override
        public void windowClosing(WindowEvent e){
            System.exit(1);
        }
    }
}
