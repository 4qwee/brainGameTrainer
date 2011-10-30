package org.i4qwee.chgk.trainer.view;

import org.i4qwee.chgk.trainer.controller.brain.BrainMouseListener;
import org.i4qwee.chgk.trainer.controller.time.TimeButtonActionListener;

import javax.swing.*;
import java.awt.event.MouseListener;

/**
 * User: 4qwee
 * Date: 29.10.11
 * Time: 11:10
 */
public class BrainPanel extends JPanel
{
    private QuestionPanel questionPanel;
    private TimerButtonPanel timerButtonPanel;
    private ScorePanel scorePanel;

    public BrainPanel(JFrame parentFrame)
    {
        BoxLayout mainBoxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(mainBoxLayout);

        questionPanel = new QuestionPanel();

        timerButtonPanel = new TimerButtonPanel();
        timerButtonPanel.getTimeButton().addActionListener(new TimeButtonActionListener(questionPanel));

        scorePanel = new ScorePanel(timerButtonPanel);

        JScrollPane scrollPane = new JScrollPane(questionPanel);
        scrollPane.setFocusable(false);

        add(scrollPane);
        add(scorePanel);

        addMouseListener(new BrainMouseListener(parentFrame));

        new BrainConfirmationDialog(parentFrame);
    }

    public void addMouseListener(MouseListener mouseListener)
    {
        super.addMouseListener(mouseListener);

        questionPanel.addMouseListener(mouseListener);
        timerButtonPanel.addMouseListener(mouseListener);
        scorePanel.addMouseListener(mouseListener);
    }
}
