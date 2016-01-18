package isen.study.data;

public enum Sex {
	MALE,
	FEMALE;

	public static Sex parseSex(String input) {
		if (input.compareTo("Mr") == 0) {
			return Sex.MALE;
		}
		if (input.compareTo("Mrs") == 0) {
			return Sex.FEMALE;
		}
		return null;
	}
}
