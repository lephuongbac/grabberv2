package com.ketnoiso.core.helper;


/**
 * Reads file in UTF-8 encoding and output to STDOUT in ASCII with unicode
 * escaped sequence for characters outside of ASCII.
 */
public class UTF8ToAscii {
	
	/**
	 * Convert.
	 * 
	 * @param str
	 *            the str
	 * @throws Exception
	 *             the exception
	 */
	public static void convert(String str) throws Exception {
		System.out.println(unicodeEscape(str));
	}

	/** The Constant hexChar. */
	private static final char[] hexChar = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	/**
	 * Unicode escape.
	 * 
	 * @param s
	 *            the s
	 * @return the string
	 */
	private static String unicodeEscape(String s) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if ((c >> 7) > 0) {
				sb.append("\\u");
				sb.append(hexChar[(c >> 12) & 0xF]); // append the hex character
														// for the left-most
														// 4-bits
				sb.append(hexChar[(c >> 8) & 0xF]); // hex for the second group
													// of 4-bits from the left
				sb.append(hexChar[(c >> 4) & 0xF]); // hex for the third group
				sb.append(hexChar[c & 0xF]); // hex for the last group, e.g.,
												// the right most 4-bits
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}
	
	/**
	 * Rename.
	 * 
	 * @param file
	 *            the file
	 * @return the string
	 */
	public static String rename(String file)
    {
        file = file.replace('\340', 'a');
        file = file.replace('\341', 'a');
        file = file.replace('\u1EA3', 'a');
        file = file.replace('\343', 'a');
        file = file.replace('\u1EA1', 'a');
        file = file.replace('\u0103', 'a');
        file = file.replace('\u1EB1', 'a');
        file = file.replace('\u1EAF', 'a');
        file = file.replace('\u1EB3', 'a');
        file = file.replace('\u1EB5', 'a');
        file = file.replace('\u1EB7', 'a');
        file = file.replace('\342', 'a');
        file = file.replace('\u1EA7', 'a');
        file = file.replace('\u1EA5', 'a');
        file = file.replace('\u1EA9', 'a');
        file = file.replace('\u1EAB', 'a');
        file = file.replace('\u1EAD', 'a');
        file = file.replace('\300', 'a');
        file = file.replace('\301', 'a');
        file = file.replace('\u1EA2', 'a');
        file = file.replace('\303', 'a');
        file = file.replace('\u1EA0', 'a');
        file = file.replace('\u0102', 'a');
        file = file.replace('\u1EB0', 'a');
        file = file.replace('\u1EAE', 'a');
        file = file.replace('\u1EB2', 'a');
        file = file.replace('\u1EB4', 'a');
        file = file.replace('\u1EB6', 'a');
        file = file.replace('\302', 'a');
        file = file.replace('\u1EA6', 'a');
        file = file.replace('\u1EA4', 'a');
        file = file.replace('\u1EA8', 'a');
        file = file.replace('\u1EAA', 'a');
        file = file.replace('\u1EAC', 'a');
        file = file.replace('\u0111', 'd');
        file = file.replace('\u0110', 'd');
        file = file.replace('\350', 'e');
        file = file.replace('\351', 'e');
        file = file.replace('\u1EBB', 'e');
        file = file.replace('\u1EBD', 'e');
        file = file.replace('\u1EB9', 'e');
        file = file.replace('\352', 'e');
        file = file.replace('\u1EC1', 'e');
        file = file.replace('\u1EBF', 'e');
        file = file.replace('\u1EC3', 'e');
        file = file.replace('\u1EC5', 'e');
        file = file.replace('\u1EC7', 'e');
        file = file.replace('\310', 'e');
        file = file.replace('\311', 'e');
        file = file.replace('\u1EBA', 'e');
        file = file.replace('\u1EBC', 'e');
        file = file.replace('\u1EB8', 'e');
        file = file.replace('\312', 'e');
        file = file.replace('\u1EC0', 'e');
        file = file.replace('\u1EBE', 'e');
        file = file.replace('\u1EC2', 'e');
        file = file.replace('\u1EC4', 'e');
        file = file.replace('\u1EC6', 'e');
        file = file.replace('\354', 'i');
        file = file.replace('\355', 'i');
        file = file.replace('\u1EC9', 'i');
        file = file.replace('\u0129', 'i');
        file = file.replace('\u1ECB', 'i');
        file = file.replace('\314', 'i');
        file = file.replace('\315', 'i');
        file = file.replace('\u1EC8', 'i');
        file = file.replace('\u0128', 'i');
        file = file.replace('\u1ECA', 'i');
        file = file.replace('\362', 'o');
        file = file.replace('\363', 'o');
        file = file.replace('\u1ECF', 'o');
        file = file.replace('\365', 'o');
        file = file.replace('\u1ECD', 'o');
        file = file.replace('\364', 'o');
        file = file.replace('\u1ED3', 'o');
        file = file.replace('\u1ED1', 'o');
        file = file.replace('\u1ED5', 'o');
        file = file.replace('\u1ED7', 'o');
        file = file.replace('\u1ED9', 'o');
        file = file.replace('\u01A1', 'o');
        file = file.replace('\u1EDD', 'o');
        file = file.replace('\u1EDB', 'o');
        file = file.replace('\u1EDF', 'o');
        file = file.replace('\u1EE1', 'o');
        file = file.replace('\u1EE3', 'o');
        file = file.replace('\322', 'o');
        file = file.replace('\323', 'o');
        file = file.replace('\u1ECE', 'o');
        file = file.replace('\325', 'o');
        file = file.replace('\u1ECC', 'o');
        file = file.replace('\324', 'o');
        file = file.replace('\u1ED2', 'o');
        file = file.replace('\u1ED0', 'o');
        file = file.replace('\u1ED4', 'o');
        file = file.replace('\u1ED6', 'o');
        file = file.replace('\u1ED8', 'o');
        file = file.replace('\u01A0', 'o');
        file = file.replace('\u1EDC', 'o');
        file = file.replace('\u1EDA', 'o');
        file = file.replace('\u1EDE', 'o');
        file = file.replace('\u1EE0', 'o');
        file = file.replace('\u1EE2', 'o');
        file = file.replace('\371', 'u');
        file = file.replace('\372', 'u');
        file = file.replace('\u1EE7', 'u');
        file = file.replace('\u0169', 'u');
        file = file.replace('\u1EE5', 'u');
        file = file.replace('\u01B0', 'u');
        file = file.replace('\u1EEB', 'u');
        file = file.replace('\u1EE9', 'u');
        file = file.replace('\u1EED', 'u');
        file = file.replace('\u1EEF', 'u');
        file = file.replace('\u1EF1', 'u');
        file = file.replace('\331', 'u');
        file = file.replace('\332', 'u');
        file = file.replace('\u1EE6', 'u');
        file = file.replace('\u0168', 'u');
        file = file.replace('\u1EE4', 'u');
        file = file.replace('\u01AF', 'u');
        file = file.replace('\u1EEA', 'u');
        file = file.replace('\u1EE8', 'u');
        file = file.replace('\u1EEC', 'u');
        file = file.replace('\u1EEE', 'u');
        file = file.replace('\u1EF0', 'u');
        file = file.replace('\u1EF3', 'y');
        file = file.replace('\375', 'y');
        file = file.replace('\u1EF7', 'y');
        file = file.replace('\u1EF9', 'y');
        file = file.replace('\u1EF5', 'y');
        file = file.replace('Y', 'y');
        file = file.replace('\u1EF2', 'y');
        file = file.replace('\335', 'y');
        file = file.replace('\u1EF6', 'y');
        file = file.replace('\u1EF8', 'y');
        file = file.replace('\u1EF4', 'y');
        file = file.replace('(', '-');
        file = file.replace(')', '-');
        file = file.replace('.', ' ');
        file = file.replace(' ', '-');
        file = file.replace('\'', '-');
        return file;
    }
}
