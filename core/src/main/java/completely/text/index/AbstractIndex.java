package completely.text.index;

import java.util.Arrays;

/**
 * Skeletal implementation of the {@link Index} interface.
 */
public abstract class AbstractIndex<V> implements Index<V>
{
    @Override
    public boolean put(String key, V value)
    {
        return putAll(key, Arrays.asList(value));
    }

    @Override
    public boolean remove(String key, V value)
    {
        return removeAll(key, Arrays.asList(value));
    }

    @Override
    public boolean remove(V value)
    {
        return removeAll(Arrays.asList(value));
    }
}
