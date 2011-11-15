package org.i4qwee.chgk.trainer.view;

import org.i4qwee.chgk.trainer.model.enums.AnswerSide;

import javax.swing.*;
import java.awt.*;

/**
 * User: 4qwee
 * Date: 29.10.11
 * Time: 13:14
 */
public class SingleScorePanel extends AbstractPanel
{
    private JLabel scoreLabel;
    private JLabel nameLabel;
    private boolean selected;
    private AnswerSide answerSide;

    public SingleScorePanel(AnswerSide answerSide)
    {
        this.answerSide = answerSide;

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

    public void setSelected(boolean selected)
    {
        this.selected = selected;
        repaint();
    }

    public void paintComponent(Graphics graphics)
    {
        if (selected)
        {
            Graphics2D graphics2D = (Graphics2D) graphics;

            graphics2D.setPaint(Color.white);
            graphics2D.fillRect(0, 0, getWidth(), getHeight());

            GradientPaint gradientPaint;

            if (answerSide == AnswerSide.LEFT)
                gradientPaint = new GradientPaint(0, 0, Color.white, getWidth(), 0, new Color(68, 2, 4));
            else
                gradientPaint = new GradientPaint(0, 0, new Color(68, 2, 4), getWidth(), 0, Color.white);

            graphics2D.setPaint(gradientPaint);
            graphics2D.fillRect(5, 5, getWidth() - 10, getHeight() - 10);
        }
        else
            super.paintComponent(graphics);
    }
}
