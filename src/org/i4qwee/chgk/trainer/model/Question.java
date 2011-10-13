package org.i4qwee.chgk.trainer.model;

/**
 * User: 4qwee
 * Date: 03.09.11
 * Time: 17:23
 */
public class Question
{
    private int id;
    private int parentId;
    private short number;
    private Type type;
    private String question;
    private String answer;
    private String passCriteria;
    private String authors;
    private String sources;
    private String comments;

    public Question()
    {
    }

    public Question(short number, Type type, String question, String answer, String passCriteria, String authors, String sources, String comments)
    {
        this.number = number;
        this.type = type;
        this.question = question;
        this.answer = answer;
        this.passCriteria = passCriteria;
        this.authors = authors;
        this.sources = sources;
        this.comments = comments;
    }

    public String toString()
    {
        return "Id: " + getId() + "\n"
            +"Parent Id: " + getParentId() + "\n"
            +"Number: " + getNumber() + "\n"
            +"Type: " + getType() + "\n"
            +"Question: " + getQuestion() + "\n"
            +"Answer: " + getAnswer() + "\n"
            +"Pass Criteria: " + getPassCriteria() + "\n"
            +"Authors: " + getAuthors() + "\n"
            +"Sources: " + getSources() + "\n"
            +"Comments: " + getComments() + "\n";
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getParentId()
    {
        return parentId;
    }

    public void setParentId(int parentId)
    {
        this.parentId = parentId;
    }

    public short getNumber()
    {
        return number;
    }

    public void setNumber(short number)
    {
        this.number = number;
    }

    public Type getType()
    {
        return type;
    }

    public void setType(Type type)
    {
        this.type = type;
    }

    public String getQuestion()
    {
        return question;
    }

    public void setQuestion(String question)
    {
        this.question = question;
    }

    public String getAnswer()
    {
        return answer;
    }

    public void setAnswer(String answer)
    {
        this.answer = answer;
    }

    public String getPassCriteria()
    {
        return passCriteria;
    }

    public void setPassCriteria(String passCriteria)
    {
        this.passCriteria = passCriteria;
    }

    public String getAuthors()
    {
        return authors;
    }

    public void setAuthors(String authors)
    {
        this.authors = authors;
    }

    public String getSources()
    {
        return sources;
    }

    public void setSources(String sources)
    {
        this.sources = sources;
    }

    public String getComments()
    {
        return comments;
    }

    public void setComments(String comments)
    {
        this.comments = comments;
    }
}
