package org.i4qwee.chgk.trainer.model;

import java.sql.Date;

/**
 * User: 4qwee
 * Date: 05.09.11
 * Time: 0:25
 */
public class Tournament
{
    private int id;
    private int parentId;
    private String title;
    private short questionsNumber;
    private String copyright;
    private String info;
    private String url;
    private String filename;
    private String editors;
    private Date playedAt;

    public Tournament()
    {
    }

    public Tournament(String title, short questionsNumber, String copyright, String info, String url, String filename, String editors, Date playedAt)
    {
        this.title = title;
        this.questionsNumber = questionsNumber;
        this.copyright = copyright;
        this.info = info;
        this.url = url;
        this.filename = filename;
        this.editors = editors;
        this.playedAt = playedAt;
    }

    public String toString()
    {
        return "Id: " + getId() + "\n"
                + "Parent Id: " + getParentId() + "\n"
                + "Title: " + getTitle() + "\n"
                + "Questions Number: " + getQuestionsNumber() + "\n"
                + "Copyright: " + getCopyright() + "\n"
                + "Info: " + getInfo() + "\n"
                + "URL: " + getUrl() + "\n"
                + "Filename: " + getFilename() + "\n"
                + "Editors: " + getEditors() + "\n"
                + "Played At: " + getPlayedAt() + "\n";
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

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public short getQuestionsNumber()
    {
        return questionsNumber;
    }

    public void setQuestionsNumber(short questionsNumber)
    {
        this.questionsNumber = questionsNumber;
    }

    public String getCopyright()
    {
        return copyright;
    }

    public void setCopyright(String copyright)
    {
        this.copyright = copyright;
    }

    public String getInfo()
    {
        return info;
    }

    public void setInfo(String info)
    {
        this.info = info;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getFilename()
    {
        return filename;
    }

    public void setFilename(String filename)
    {
        this.filename = filename;
    }

    public String getEditors()
    {
        return editors;
    }

    public void setEditors(String editors)
    {
        this.editors = editors;
    }

    public Date getPlayedAt()
    {
        return playedAt;
    }

    public void setPlayedAt(Date playedAt)
    {
        this.playedAt = playedAt;
    }
}
