package org.i4qwee.chgk.trainer.view;

import javax.swing.*;
import java.awt.*;
import java.util.Observer;

/**
 * User: 4qwee
 * Date: 29.10.11
 * Time: 13:14
 */
public class SingleScorePanel extends AbstractPanel
{
    private JLabel scoreLabel;
    private JLabel nameLabel;

    public SingleScorePanel()
    {
        setBorder(DefaultUIProvider.getDefaultEmptyEtchedBorder());
        setPreferredSize(new Dimension(150, ScorePanel.MAX_HEIGHT));

        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(boxLayout);

        scoreLabel = new JLabel("0");
        scoreLabel.setFont(DefaultUIProvider.getDisplayFont());
        scoreLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        add(scoreLabel);

        nameLabel = new JLabel();
        nameLabel.setFont(DefaultUIProvider.getQuestionPriceFont());
        nameLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        add(nameLabel);
    }

    public void setScore(int score)
    {
        scoreLabel.setText(String.valueOf(score));
    }

    public void setName(String name)
    {
        nameLabel.setText(name);
    }

    public String getName()
    {
        return nameLabel.getText();
    }
}
