package completely.data;

/**
 * Bare implementation of the {@link Record} interface.
 */
public class SimpleRecord implements Record
{
    private final String text;
    private final double score;

    public SimpleRecord(String text)
    {
        this(text, 0);
    }

    public SimpleRecord(String text, double score)
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
