package ua.kateros.sybd.gui;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.awt.EventQueue;

@Component
public class MainForm {

    private MyFrame frame;

    public MainForm() { }

    public MainForm(MyFrame mf) { frame = mf; }

    public MyFrame getFrame() {
        return frame;
    }

    public void setFrame(MyFrame frame) {
        this.frame = frame;
    }

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("ua/kateros/sybd/gui/beans.xml");
        final MainForm mainForm = (MainForm) ctx.getBean("mainForm");
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    mainForm.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
