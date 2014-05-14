public class Trie
{
    Node root;

    public Trie()
    {
        this.root = new Node();
    }

    /**
     Method to insert a string to Node and its children

     @param key the string to insert (the string is assumed to be uppercase)
     @return true if the node or one of its children is changed, false otherwise
     */
    public boolean insert(String key)
    {
        return root.insert(key.toUpperCase());
    }

    /**
     Returns whether key is a valid prefix for certain key in this trie.
     For example: if key "hello" is in this trie, tests with all prefixes "hel", "hell", "hello" return true

     @param prefix the prefix to check
     @return true if the prefix is valid, false otherwise
     */
    public boolean containPrefix(String prefix)
    {
        return root.containPrefix(prefix.toUpperCase());
    }

    /**
     Returns whether key is a valid key in this trie.
     For example: if key "hello" is in this trie, tests with all prefixes "hel", "hell" return false

     @param key the key to check
     @return true if the key is valid, false otherwise
     */
    public boolean containKey(String key)
    {
        return root.containKey(key.toUpperCase());
    }


}
