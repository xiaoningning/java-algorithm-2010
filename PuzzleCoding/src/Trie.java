import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Implements a trie. We store the input list of words in tries so
 * that we can efficiently find words with a given prefix.
 */
public class Trie {

    // Trie Node
    public static class Node {

        //the character is the value of node
        public char character;
        public ArrayList<Node> children;

        // end of a word
        public boolean terminated = false;

        public Node() {
            children = new ArrayList<Node>();

        }

        public Node(char c) {
            this();
            character = c;
        }

        public boolean addWord(String w) {
            if (w == null || w.length() == 0) {
                return false;
            }

            Node child;
            char firstChar = w.charAt(0);
            Node tmp = getChild(firstChar);
            if (tmp == null) {
                child = new Node(firstChar);
                children.add(child);
            } else {
                child = tmp;
            }

            if (w.length() > 1) {
                child.addWord(w.substring(1));
            } else {
                child.terminated = true;
            }
            return true;
        }

        public Node getChild(char c) {
            for (Node n : children) {
                if (n.character == c)
                    return n;
            }
            return null;
        }

        public Node getLeafNode(char c) {
            for (Node n : children) {
                if (n.children.size() != 0) {
                    n.getLeafNode(c);
                } else {
                    if (n.terminated) {
                        return n;
                    } else {
                        return null;
                    }
                }
            }
            return null;
        }

        public Node getParentLeafNode(char c) {
            for (Node n : children) {
                if (n.children.size() != 0) {
                    n.getLeafNode(c);
                } else {
                    if (n.terminated) {
                        return this;
                    } else {
                        return null;
                    }
                }
            }
            return null;
        }

        public boolean removeChild(Node child) {
            if (children.contains(child)) {
                children.remove(child);
                if (children.size() == 0) {
                    this.terminated = true;
                }
                return true;
            } else {
                return false;
            }
        }

        public boolean removeChild(char c) {
            Node child = new Node(c);
            return removeChild(child);
        }

        public void getAllWords(StringBuilder path, ArrayList<String> words) {
            for (Node c : children) {
                StringBuilder word = new StringBuilder(path);
                word.append(c.character);
                if (c.terminated) {
                    words.add(word.toString());
                } else {
                    c.getAllWords(word, words);
                }
            }

        }

    } // end of TrieNode class

    private Node root;

    public Trie(ArrayList<String> words) {
        root = new Node();
        for (String word : words) {
            root.addWord(word);
        }
    }

    public Trie(String[] words) {
        root = new Node();
        for (String word : words) {
            root.addWord(word);
        }
    }

    public boolean contains(String word, boolean exactMatch) {
        Node current = root;
        for (int i = 0; i < word.length(); i++) {
            current = current.getChild(word.charAt(i));
            if (current == null)
                return false;
        }

        return !exactMatch || current.terminated;
    }

    public boolean removeWord(String w) {
        if (contains(w, true)) {
            for (int i = w.length() - 1; i >= 0; i--) {
                char c = w.charAt(i);
                Node parent = root.getParentLeafNode(c);
                parent.removeChild(c);
                if (parent.children.size() != 0) {
                    break;
                }
            }

        } else {
            return false;
        }
        return true;
    }

    public ArrayList<String> getAllWords() {
        ArrayList<String> words = new ArrayList<String>();
        StringBuilder word = new StringBuilder();
        root.getAllWords(word, words);
        return words;
    }

    public void printTrie() {
        Queue<Node> queue = new LinkedList<Node>(root.children);
        Queue<Node> nextQueue = new LinkedList<Node>();

        while (!queue.isEmpty()) {

            Node n = queue.poll();
            nextQueue.addAll(n.children);
            System.out.print(n.character + " ");

            if (queue.isEmpty()) {
                queue.addAll(nextQueue);
                nextQueue.clear();
                System.out.println();
            }

        }
    }

    public static void main(String[] args) {
        String[] words = {"apple", "arm", "grape", "apc"};
        Trie root = new Trie(words);
        ArrayList<String> list = root.getAllWords();

        root.printTrie();
        System.out.println(list.toString());

        System.out.println(root.contains("ppl", false));
        System.out.println(root.contains("arm", true));
        System.out.println(root.contains("appl", false));


    }

}
