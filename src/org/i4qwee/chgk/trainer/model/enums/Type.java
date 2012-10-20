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

    private final int type;

    Type(int type)
    {
        this.type = type;
    }

    public short getShortType()
    {
        return (short) type;
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
