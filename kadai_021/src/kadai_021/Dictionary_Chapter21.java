package kadai_021;

import java.util.HashMap;
import java.util.List;

public class Dictionary_Chapter21 {
	
	HashMap<String, String> dictionaryMap = new HashMap<String, String>();
	
	public void searchDictionary(List<String> words) {
		
		// 辞書の内容をセット
		dictionaryMap.put("apple", "りんご");
		dictionaryMap.put("peach", "桃");
		dictionaryMap.put("banana", "バナナ");
		dictionaryMap.put("lemon", "レモン");
		dictionaryMap.put("pear", "梨");
		dictionaryMap.put("kiwi", "キウィ");
		dictionaryMap.put("strawberry", "いちご");
		dictionaryMap.put("grape", "ぶどう");
		dictionaryMap.put("muscat", "マスカット");
		dictionaryMap.put("cherry", "さくらんぼ");
		
		for (String word : words) {
			if (dictionaryMap.get(word) == null) {
				System.out.println(word + "は辞書に存在しません");
			} else {
				System.out.println(word + "の意味は" + dictionaryMap.get(word));
			}
		}
	}
}
