package org.i4qwee.chgk.trainer.test;

import javax.swing.*;
import javax.swing.text.html.HTMLEditorKit;
import java.awt.*;

/**
 * User: 4qwee
 * Date: 16.11.11
 * Time: 15:31
 */
public class JEditorTest extends JFrame
{
    public JEditorTest() throws HeadlessException
    {
        super();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JEditorPane jEditorPane = new JEditorPane();
        HTMLEditorKit editorKit = new HTMLEditorKit();

        jEditorPane.setEditorKit(editorKit);
        jEditorPane.setText("1234asdf asdf asdf asdfhdt siftoqwuyef egfuqywegf gwefygqwey fqywegf qywegf 8qywegf qywegf yqwgef yq8gwefqywgeufhwguhgeuhfg");

        JPanel panel = new JPanel();
        panel.add(jEditorPane);
        add(panel);

        setSize(300, 300);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new JEditorTest();
    }
}
