package org.i4qwee.chgk.trainer.model.enums;

import org.i4qwee.chgk.trainer.model.UnknownTypeError;

/**
 * User: 4qwee
 * Date: 05.09.11
 * Time: 0:20
 */
public enum Type
{
    CHGK (1),
    BRAIN (2),
    SVOYAK (4),
    VERSE (8),
    INTERNET (16),
    ERUDITE(32),
    UNKNOWN(64);

    int type;

    Type(int type)
    {
        this.type = type;
    }

    public short getShortType()
    {
        return (short) type;
    }

    public static Type parseType(char source) throws UnknownTypeError
    {
        switch (source)
        {
            case 'Ч':
                return CHGK;
            case 'Б':
                return BRAIN;
            case 'Я':
                return SVOYAK;
            case 'Л':
                return VERSE;
            case 'И':
                return INTERNET;
            case 'Э':
                return ERUDITE;
            case 'Г':
            case 'Т':
                return UNKNOWN;
            default:
                throw new UnknownTypeError();
        }
    }

    public static Type parseType(short source) throws UnknownTypeError
    {
        switch (source)
        {
            case 1:
                return CHGK;
            case 2:
                return BRAIN;
            case 4:
                return SVOYAK;
            case 8:
                return VERSE;
            case 16:
                return INTERNET;
            case 32:
                return ERUDITE;
            case 64:
                return UNKNOWN;
            default:
                throw new UnknownTypeError();
        }
    }
}
