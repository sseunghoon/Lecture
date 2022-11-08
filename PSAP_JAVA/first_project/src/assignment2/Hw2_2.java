package assignment2;


abstract class PairMap {
	protected String keyArray [];
	protected String valueArray [];
	abstract String get(String key);
	abstract void put(String key, String value);
	abstract String delete(String key);
	abstract int length();
}

class Dictionary extends PairMap {
	int size;
	int len = 0;
	
	public Dictionary(int size) {
		this.size = size;
		keyArray = new String[size];
		valueArray = new String[size];
	}

	@Override
	protected String get(String key) {
		for (int i = 0; i < size; i++) {
			if (keyArray[i] != null && keyArray[i].equals(key))
				return valueArray[i];
		}
		return null;
	}

	@Override
	void put(String key, String value) {
		for (int i = 0; i < size; i++) {
			if (keyArray[i] != null && keyArray[i] == key) {
				valueArray[i] = value;
				return;
			}
		}
		
		for (int i = 0; i < size; i++) {
			if (keyArray[i] == null) {
				keyArray[i] = key;
				valueArray[i] = value;
				len++;
				return;
			}
		}
	}

	@Override
	String delete(String key) {
		String value;
		for (int i = 0; i < size; i++) {
			if (keyArray[i] != null && keyArray[i].equals(key)) {
				value = valueArray[i];
				keyArray[i] = null;
				valueArray[i] = null;
				len--;
				return value;
			}
		}
		return null;
	}

	@Override
	int length() {
		return len;
	}
}

public class Hw2_2 {
	
	public static void main(String args[]) {
		Dictionary dic = new Dictionary(10);
		dic.put("황기태", "자바");
		dic.put("이재문", "파이썬");
		dic.put("이재문", "C++");
		System.out.println("이재문의 값은 " + dic.get("이재문"));
		System.out.println("황기태의 값은 " + dic.get("황기태"));
		dic.delete("황기태");
		System.out.println("황기태의 값은 " + dic.get("황기태"));
			
	}
}
