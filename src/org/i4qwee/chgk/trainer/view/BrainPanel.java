package org.i4qwee.chgk.trainer.view;

import org.i4qwee.chgk.trainer.controller.brain.BrainMouseListener;
import org.i4qwee.chgk.trainer.controller.brain.ScoreManager;
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
    private JFrame parentFrame;
    private QuestionPanel questionPanel;
    private TimerButtonPanel timerButtonPanel;
    private ScorePanel scorePanel;
    private ScoreManager scoreManager;

    public BrainPanel(JFrame parentFrame)
    {
        this.parentFrame = parentFrame;

        BoxLayout mainBoxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(mainBoxLayout);

        questionPanel = new QuestionPanel();

        timerButtonPanel = new TimerButtonPanel();
        timerButtonPanel.getTimeButton().addActionListener(new TimeButtonActionListener(questionPanel));

        scorePanel = new ScorePanel(timerButtonPanel);

        scoreManager = new ScoreManager(scorePanel);

        JScrollPane scrollPane = new JScrollPane(questionPanel);
        scrollPane.setFocusable(false);

        add(scrollPane);
        add(scorePanel);

        addMouseListener(new BrainMouseListener(parentFrame, scoreManager));
    }

    public void addMouseListener(MouseListener mouseListener)
    {
        super.addMouseListener(mouseListener);

        questionPanel.addMouseListener(mouseListener);
        timerButtonPanel.addMouseListener(mouseListener);
        scorePanel.addMouseListener(mouseListener);
    }
}
