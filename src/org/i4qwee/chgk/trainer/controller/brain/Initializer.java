package org.i4qwee.chgk.trainer.controller.brain;

import org.i4qwee.chgk.trainer.controller.brain.controller.TimerController;
import org.i4qwee.chgk.trainer.view.dialogs.MessageDialog;

/**
 * User: 4qwee
 * Date: 06.01.12
 * Time: 13:17
 */
public class Initializer
{
    public Initializer()
    {
        TimerController.getInstance();

        MessageDialog.getInstance();
    }
}
