package completely.text.index;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Trie based implementation of the {@link PrefixIndex} interface.
 *
 * <p>Note that this implementation is not synchronized.
 */
public class HashTrie<V> extends AbstractIndex<V> implements PrefixIndex<V>
{
    private Node root;

    public HashTrie()
    {
        root = new Node();
    }

    @Override
    public void clear()
    {
        root = new Node();
    }

    @Override
    public Set<V> getAll(String key)
    {
        Node node = key != null ? find(root, key) : null;
        return node != null ? Collections.unmodifiableSet(node.values) : Collections.<V>emptySet();
    }

    @Override
    public Set<V> getAny(String fragment)
    {
        Node node = fragment != null ? find(root, fragment) : null;
        return node != null ? Collections.unmodifiableSet(values(node)) : Collections.<V>emptySet();
    }

    @Override
    public boolean isEmpty()
    {
        return root.isEmpty();
    }

    @Override
    public boolean put(String key, Collection<V> values)
    {
        return key != null ? put(root, key, values) : false;
    }

    @Override
    public Set<V> remove(String key)
    {
        return key != null ? remove(root, key) : Collections.<V>emptySet();
    }

    @Override
    public boolean remove(String key, Collection<V> values)
    {
        return key != null ? remove(root, key, values) : false;
    }

    @Override
    public int size()
    {
        return size(root);
    }

    private Node find(Node node, String key)
    {
        if (key.length() <= 0)
        {
            return node;
        }
        else
        {
            char character = key.charAt(0);
            Node child = node.children.get(character);
            if (child != null)
            {
                return find(child, key.substring(1));
            }
        }
        return null;
    }

    private boolean put(Node node, String key, Collection<V> values)
    {
        if (key.length() <= 0)
        {
            return node.values.addAll(values);
        }
        else
        {
            char character = key.charAt(0);
            Node child = node.children.get(character);
            if (child == null)
            {
                child = new Node();
                node.children.put(character, child);
            }
            return put(child, key.substring(1), values);
        }
    }

    private Set<V> remove(Node node, String key)
    {
        if (key.length() <= 0)
        {
            Set<V> result = node.values;
            node.values = new HashSet<V>();
            return result;
        }
        else
        {
            char character = key.charAt(0);
            Node child = node.children.get(character);
            if (child != null)
            {
                Set<V> result = remove(child, key.substring(1));
                if (!result.isEmpty() && child.isEmpty())
                {
                    node.children.remove(character);
                }
                return result;
            }
        }
        return Collections.<V>emptySet();
    }

    private boolean remove(Node node, String key, Collection<V> values)
    {
        if (key.length() <= 0)
        {
            return node.values.removeAll(values);
        }
        else
        {
            char character = key.charAt(0);
            Node child = node.children.get(character);
            if (child != null)
            {
                boolean result = remove(child, key.substring(1), values);
                if (result && child.isEmpty())
                {
                    node.children.remove(character);
                }
                return result;
            }
        }
        return false;
    }

    private int size(Node node)
    {
        int result = node.values.size();
        for (Node child : node.children.values())
        {
            result += size(child);
        }
        return result;
    }

    private Set<V> values(Node node)
    {
        Set<V> result = new HashSet<V>(node.values);
        for (Node child : node.children.values())
        {
            result.addAll(values(child));
        }
        return result;
    }

    private class Node
    {
        Map<Character, Node> children;
        Set<V> values;

        Node()
        {
            children = new HashMap<Character, Node>();
            values = new HashSet<V>();
        }

        boolean isEmpty()
        {
            return children.isEmpty() && values.isEmpty();
        }
    }
}
