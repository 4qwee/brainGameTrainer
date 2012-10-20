package org.i4qwee.chgk.trainer.new_brain.states;

import org.i4qwee.chgk.trainer.controller.brain.manager.QuestionManager;
import org.i4qwee.chgk.trainer.controller.questions.QuestionsCache;
import org.i4qwee.chgk.trainer.view.dialogs.NewGameDialog;

/**
 * Created with IntelliJ IDEA.
 * User: 4qwee
 * Date: 10/3/12
 * Time: 11:44 PM
 */
public class NoQuestionsLoaded extends State
{
    @Override
    public void doAction()
    {
        QuestionManager.getInstance().setQuestion(QuestionsCache.getNextQuestion());

        StateManager.getInstance().setState(new QuestionLoaded());
    }

    @Override
    public void doNewGame()
    {
        NewGameDialog.getInstance().showDialog();
    }
}
