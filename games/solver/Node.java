import gineer.bogglesolver.util.Constants;

class Node
{
    Node[] children;
    boolean isKey;

    public Node()
    {
        isKey = false;
        children = new Node[Constants.NUMBER_LETTERS_IN_ALPHABET];
    }

    public Node(boolean key)
    {
        isKey = key;
        children = new Node[Constants.NUMBER_LETTERS_IN_ALPHABET];
    }

    /**
     Method to insert a string to Node and its children

     @param key the string to insert (the string is assumed to be uppercase)
     @return true if the node or one of its children is changed, false otherwise
     */
    public boolean insert(String key)
    {
        //If the key is empty, this node is a key
        if (key.length() == 0)
        {
            if (isKey)
                return false;
            else
            {
                isKey = true;
                return true;
            }
        }
        else
        {//otherwise, insert in one of its child

            int childNodePosition = key.charAt(0) - Constants.LETTER_A;
            if (children[childNodePosition] == null)
            {
                children[childNodePosition] = new Node();
                children[childNodePosition].insert(key.substring(1));
                return true;
            }
            else
            {
                return children[childNodePosition].insert(key.substring(1));
            }
        }
    }

    /**
     Returns whether key is a valid prefix for certain key in this trie.
     For example: if key "hello" is in this trie, tests with all prefixes "hel", "hell", "hello" return true

     @param prefix the prefix to check
     @return true if the prefix is valid, false otherwise
     */
    public boolean containPrefix(String prefix)
    {
        //If the prefix is empty, return true
        if (prefix.length() == 0)
        {
            return true;
        }
        else
        {//otherwise, check in one of its child
            int childNodePosition = prefix.charAt(0) - Constants.LETTER_A;
            return children[childNodePosition] != null && children[childNodePosition].containPrefix(prefix.substring(1));
        }
    }

    /**
     Returns whether key is a valid key in this trie.
     For example: if key "hello" is in this trie, tests with all prefixes "hel", "hell" return false

     @param key the key to check
     @return true if the key is valid, false otherwise
     */
    public boolean containKey(String key)
    {
        //If the prefix is empty, return true
        if (key.length() == 0)
        {
            return isKey;
        }
        else
        {//otherwise, check in one of its child
            int childNodePosition = key.charAt(0) - Constants.LETTER_A;
            return children[childNodePosition] != null && children[childNodePosition].containKey(key.substring(1));
        }
    }

    public boolean isKey()
    {
        return isKey;
    }

    public void setKey(boolean key)
    {
        isKey = key;
    }
}

