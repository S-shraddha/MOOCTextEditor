package spelling;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AutoCompleteMatchCase implements Dictionary, AutoComplete {

    private TrieNode root;
    private int size;
    

    public AutoCompleteMatchCase()
	{
		root = new TrieNode();
	}
    public boolean addWord(String word)
	{
	    // Implement this method.
		TrieNode node = root;
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if (i > 0) {
				c = Character.toLowerCase(c);
			}
			if (node.getValidNextCharacters().contains(c)) {
				node = node.getChild(c);
			} else {
				node = node.insert(c);
			}
		}
		if (!node.endsWord()) {
			node.setEndsWord(true);
			size++;
			return true;
		}
	    return false;
	}
    public int size()
	{
	    // Implement this method
	    return size;
	}
	
	
	/** Returns whether the string is a word in the trie, using the algorithm
	 * described in the videos for this week. */
	@Override
	public boolean isWord(String s) 
	{
	    // Implement this method
		TrieNode node = root;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (i > 0) {
				c = Character.toLowerCase(c);
			}
			if (node.getValidNextCharacters().contains(c)) {
				node = node.getChild(c);
			} else {
				return false;
			}
		}
		if (node.endsWord()) {
			return true;
		}
		return false;
	}
	public List<String> predictCompletions(String prefix, int numCompletions) 
    {
   	 
   	 List<String> completions = new LinkedList<String>();
   	 TrieNode node = root;
   	 for (int i = 0; i < prefix.length(); i++) {
 			char c = prefix.charAt(i);
 			if (i > 0) {
 				c = Character.toLowerCase(c);
 			}
   		 if (node.getValidNextCharacters().contains(c)) {
				node = node.getChild(c);
			} else {
				return completions;
			}
   	 }
   	 if (node.endsWord()) {
   		 completions.add(node.getText());
   	 }
   	 
   	 Queue<TrieNode> nodeQueue = new LinkedList<TrieNode>();
   	 List<Character> children = new LinkedList<Character>(node.getValidNextCharacters());
   	 
   	 for (int i = 0; i < children.size(); i++) {
   		 char c = children.get(i);
   		 nodeQueue.add(node.getChild(c));
   	 }
   	 while (!nodeQueue.isEmpty() && completions.size() < numCompletions) {
   		 TrieNode firstNode = nodeQueue.poll();
   		 if (firstNode.endsWord()) {
   			 completions.add(firstNode.getText());
   		 }
   		 
   		 List<Character> childNodes = new LinkedList<Character>(firstNode.getValidNextCharacters());
       	 for (int i = 0; i < childNodes.size(); i++) {
       		 char c = childNodes.get(i);
       		 nodeQueue.add(firstNode.getChild(c));
       	 }
   	 }
        return completions;
    }

	// For debugging
	public void printTree()
	{
		printNode(root);
	}
	
	/** Do a pre-order traversal from this node down */
	public void printNode(TrieNode curr)
	{
		if (curr == null) 
			return;
		
		System.out.println(curr.getText());
		
		TrieNode next = null;
		for (Character c : curr.getValidNextCharacters()) {
			next = curr.getChild(c);
			printNode(next);
		}
	}
	

	
}