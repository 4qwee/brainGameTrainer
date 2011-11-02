package org.i4qwee.chgk.trainer.view;

import org.i4qwee.chgk.trainer.controller.brain.BrainMouseListener;
import org.i4qwee.chgk.trainer.controller.brain.SoundManagerSingleton;
import org.i4qwee.chgk.trainer.controller.time.TimeButtonActionListener;
import org.i4qwee.chgk.trainer.view.dialogs.BrainConfirmationDialog;

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
    private TopPanel topPanel;

    public BrainPanel(JFrame parentFrame)
    {
        BoxLayout mainBoxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(mainBoxLayout);

        topPanel = new TopPanel(parentFrame);
        add(topPanel);

        questionPanel = new QuestionPanel();

        timerButtonPanel = new TimerButtonPanel();
        timerButtonPanel.getTimeButton().addActionListener(new TimeButtonActionListener(questionPanel));

        scorePanel = new ScorePanel(timerButtonPanel);

        JScrollPane scrollPane = new JScrollPane(questionPanel);
        scrollPane.setBorder(null);
        scrollPane.setFocusable(false);

        add(scrollPane);
        add(scorePanel);

        addMouseListener(new BrainMouseListener(parentFrame));

        new BrainConfirmationDialog(parentFrame);
        SoundManagerSingleton.getInstance();
    }

    public void addMouseListener(MouseListener mouseListener)
    {
        super.addMouseListener(mouseListener);

        questionPanel.addMouseListener(mouseListener);
        timerButtonPanel.addMouseListener(mouseListener);
        scorePanel.addMouseListener(mouseListener);
    }
}
