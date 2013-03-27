package completely.data;

/**
 * Bare implementation of the {@link Indexable} interface.
 */
public class Record implements Indexable
{
    private final String text;
    private final double score;

    public Record(String text)
    {
        this(text, 0);
    }

    public Record(String text, double score)
    {
        this.text = text;
        this.score = score;
    }

    @Override
    public String getText()
    {
        return text;
    }

    @Override
    public double getScore()
    {
        return score;
    }
}
