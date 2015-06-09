import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
class GUI extends JFrame{
    static final DefaultListModel books = new DefaultListModel();
    static final JList list = new JList(books);
    private final JButton btnEnd = new JButton("END");
    static final JOptionPane msg = new JOptionPane();
    static  JFileChooser chooser;//for download
    static String dir ;
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
        add(list);
        add(btnEnd);
    }

    private void initEvent(){
        this.addWindowListener(new WindowAdapterImpl());
        btnEnd.addActionListener(this::btnEndClick);
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting())
                    return;
                System.out.println(list.getSelectedValue().toString());

                chooser = new JFileChooser();
                chooser.setCurrentDirectory(new java.io.File("."));
                chooser.setDialogTitle("Choose Destination folder");
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                // disable the "All files" option.
                chooser.setAcceptAllFileFilterUsed(false);
                //
                if (chooser.showOpenDialog(GUI.this) == JFileChooser.APPROVE_OPTION) {
                    System.out.println("getCurrentDirectory(): "
                            + chooser.getCurrentDirectory());
                    System.out.println("getSelectedFile() : "
                            + chooser.getSelectedFile());
                    dir = chooser.getSelectedFile().toString();
                    dir = dir +"\\"+ list.getSelectedValue().toString();
                    System.out.println(dir);
                    try {
                        Client.SendToServer(list.getSelectedValue().toString());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    System.out.println("No Selection ");
                }
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
