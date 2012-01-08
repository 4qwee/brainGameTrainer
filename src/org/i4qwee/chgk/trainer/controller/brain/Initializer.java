package org.i4qwee.chgk.trainer.controller.brain;

import org.i4qwee.chgk.trainer.controller.brain.controller.MaxRoundController;
import org.i4qwee.chgk.trainer.controller.brain.controller.RoundsController;
import org.i4qwee.chgk.trainer.view.dialogs.BrainConfirmationDialog;

/**
 * User: 4qwee
 * Date: 06.01.12
 * Time: 13:17
 */
public class Initializer
{
    public Initializer()
    {
        MaxRoundController.getInstance();
        RoundsController.getInstance();
        new BrainConfirmationDialog();
    }
}
