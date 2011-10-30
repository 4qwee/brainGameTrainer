package org.i4qwee.chgk.trainer.view;

import javax.swing.*;
import java.util.Observer;

/**
 * User: 4qwee
 * Date: 29.10.11
 * Time: 13:14
 */
public class SingleScorePanel extends JPanel
{
    private JLabel scoreLabel;

    public SingleScorePanel()
    {
        setBorder(DefaultUIProvider.getDefaultEmptyEtchedBorder());

        scoreLabel = new JLabel("0");
        scoreLabel.setFont(DefaultUIProvider.getDisplayFont());
        add(scoreLabel);
    }

    public void setScore(int score)
    {
        scoreLabel.setText(String.valueOf(score));
    }

    public JLabel getScoreLabel()
    {
        return scoreLabel;
    }
}
