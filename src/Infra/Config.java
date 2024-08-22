package Infra;

import java.net.URL;

import javax.swing.JOptionPane;

public abstract class Config {
    public static final URL URL_LOGO = Config.class.getResource("/MDGUI/Resource/MDLogo.jpeg");
    public static final String DATAFILE = "Data\\MDHormiguero.csv";
    public static final String LOGFILE = "Data\\MDlog.txt";

    public static final void showMsg(String msg) {
        JOptionPane.showMessageDialog(null, msg, "EcuAnt", JOptionPane.INFORMATION_MESSAGE);
    }

    public static final void showMsgError(String msg) {
        JOptionPane.showMessageDialog(null, msg, "EcuAnt", JOptionPane.OK_OPTION);  
    }

    public static final boolean showConfirmYesNo(String msg) {
        return (JOptionPane.showConfirmDialog(null, msg, "EcuAnt", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION);
    }
}
