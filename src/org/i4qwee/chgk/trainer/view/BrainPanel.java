package org.i4qwee.chgk.trainer.view;

import org.i4qwee.chgk.trainer.controller.brain.BrainMouseListener;
import org.i4qwee.chgk.trainer.controller.brain.SoundManagerSingleton;
import org.i4qwee.chgk.trainer.controller.time.TimeButtonActionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

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

        final QuestionPanel questionPanel = new QuestionPanel();

        TimerButtonPanel timerButtonPanel = new TimerButtonPanel();
        timerButtonPanel.getTimeButton().addActionListener(new TimeButtonActionListener(questionPanel));

        ScorePanel scorePanel = new ScorePanel(timerButtonPanel);

        JScrollPane scrollPane = new JScrollPane(questionPanel);
        scrollPane.setBorder(null);
        scrollPane.setFocusable(false);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        add(scrollPane);
        add(scorePanel);

        BrainMouseListener brainMouseListener = new BrainMouseListener();
        addMouseListener(brainMouseListener);
        questionPanel.addMouseListener(brainMouseListener);

        SoundManagerSingleton.getInstance();

        addComponentListener(new ComponentAdapter()
        {
            @Override
            public void componentResized(ComponentEvent e)
            {
                questionPanel.setMaximumSize(new Dimension(getWidth(), Integer.MAX_VALUE));
                questionPanel.invalidate();
            }
        });
    }
}
