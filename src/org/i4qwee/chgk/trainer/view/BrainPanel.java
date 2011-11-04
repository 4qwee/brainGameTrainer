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
public class BrainPanel extends AbstractPanel
{

    public BrainPanel(JFrame parentFrame)
    {
        BoxLayout mainBoxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(mainBoxLayout);

        TopPanel topPanel = new TopPanel(parentFrame);
        add(topPanel);

        QuestionPanel questionPanel = new QuestionPanel();

        TimerButtonPanel timerButtonPanel = new TimerButtonPanel();
        timerButtonPanel.getTimeButton().addActionListener(new TimeButtonActionListener(questionPanel));

        ScorePanel scorePanel = new ScorePanel(timerButtonPanel);

        JScrollPane scrollPane = new JScrollPane(questionPanel);
        scrollPane.setBorder(null);
        scrollPane.setFocusable(false);

        add(scrollPane);
        add(scorePanel);

        BrainMouseListener brainMouseListener = new BrainMouseListener();
        addMouseListener(brainMouseListener);
        questionPanel.addMouseListener(brainMouseListener);

        new BrainConfirmationDialog(parentFrame);
        SoundManagerSingleton.getInstance();
    }
}
