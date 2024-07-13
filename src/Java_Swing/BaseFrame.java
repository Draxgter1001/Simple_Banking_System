package Java_Swing;

import Main_App.Account;

import javax.swing.*;

public abstract class BaseFrame extends JFrame {

    protected Account account;

    public BaseFrame(String title) {
        initialize(title);
    }
    public BaseFrame(String title, Account account) {
        this.account = account;
        initialize(title);
    }

    private void initialize(String title) {
        setTitle(title);
        setSize(420, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);

        addGuiComponents();
    }

    protected abstract void addGuiComponents();

}
