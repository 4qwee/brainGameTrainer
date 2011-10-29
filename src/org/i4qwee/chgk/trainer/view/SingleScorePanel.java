package org.i4qwee.chgk.trainer.view;

import javax.swing.*;

/**
 * User: 4qwee
 * Date: 29.10.11
 * Time: 13:14
 */
public class SingleScorePanel extends JPanel
{
    private int score = 0;
    private JLabel scoreLabel;

    public SingleScorePanel()
    {
        setBorder(DefaultUIProvider.getDefaultEmptyEtchedBorder());

        scoreLabel = new JLabel("0");
        scoreLabel.setFont(DefaultUIProvider.getDisplayFont());
        add(scoreLabel);
    }

    public void addScore(int score)
    {
        this.score += score;
        scoreLabel.setText(String.valueOf(score));
    }

    public void resetScore()
    {
        score = 0;
    }

    public int getScore()
    {
        return score;
    }
}
