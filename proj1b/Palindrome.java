public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> wordlist = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i += 1){
            wordlist.addLast(word.charAt(i));
        }
        return wordlist;
    }

    public boolean isPalindromehelper(Deque<Character> m) {
        if (m.size() == 0 || m.size() == 1) {
            return true;
        }
        if (m.removeFirst() != m.removeLast()) {
            return false;
        }
        return isPalindromehelper(m);
    }

    public boolean isPalindrome(String word) {
        if (word == null) {
            return false;
        }
        Deque<Character> wordlist = wordToDeque(word);
        return isPalindromehelper(wordlist);
    }

    public boolean isPalindromehelper(Deque<Character> m, CharacterComparator cc) {
        if (m.size() == 0 || m.size() == 1) {
            return true;
        }
        if (!cc.equalChars(m.removeFirst(), m.removeLast())) {
            return false;
        }
        return isPalindromehelper(m, cc);
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word == null) {
            return false;
        }
        Deque<Character> wordlist = wordToDeque(word);
        return isPalindromehelper(wordlist, cc);
    }
}
