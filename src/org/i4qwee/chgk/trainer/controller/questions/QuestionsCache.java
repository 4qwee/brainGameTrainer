package org.i4qwee.chgk.trainer.controller.questions;

import org.apache.log4j.Logger;
import org.i4qwee.chgk.trainer.controller.database.DatabaseManager;
import org.i4qwee.chgk.trainer.model.Question;
import org.i4qwee.chgk.trainer.model.enums.Type;
import org.i4qwee.chgk.trainer.view.dialogs.LoadingDialog;

import javax.swing.*;
import java.util.List;

/**
 * User: 4qwee
 * Date: 29.10.11
 * Time: 11:43
 */
public class QuestionsCache
{
    private static int position;
    private static List<Question> questions;
    private static final LoadingDialog loadingDialog = new LoadingDialog();

    public static Question getNextQuestion()
    {
        if (questions == null || ++position >= questions.size())
        {
            Runnable getQuestionRunnable = new Runnable()
            {
                public void run()
                {
                    position = 0;
                    questions = DatabaseManager.getRandomQuestions(50, Type.BRAIN); //todo remove this hardcode

                    loadingDialog.dispose();
                }
            };

            Thread getQuestionThread = new Thread(getQuestionRunnable);
            getQuestionThread.start();
            
            loadingDialog.showDialog();
        }

        return questions.get(position);
    }
}
