package kadai_021;

import java.util.Arrays;
import java.util.List;

public class DictionaryExec_Chapter21 {

	public static void main(String[] args) {
		List<String> searchWords = Arrays.asList("apple", "banana", "grape", "orange");
		
		Dictionary_Chapter21 dictionary = new Dictionary_Chapter21();
		dictionary.searchDictionary(searchWords);
	}

}
