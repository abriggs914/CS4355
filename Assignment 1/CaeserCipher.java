
class CaeserCipher {
    
    public static String cipherShift(int shift, String origText) {
        String res = shift + ":\t";
        for (int i = 0; i < origText.length(); i++) {
            char letter = origText.charAt(i);
            if (letter == ' ') {
                res += letter;
            }   
            else {
                res += getCharForNumber((int) letter + shift);
            }
        } 
        return res;
    }
    
    public static String getCharForNumber(int i) {
        // return i > 0 && i < 27 ? String.valueOf((char)(i + 64)) : null;
        if (i > 'Z') {
            i -= 26;
        }
        return String.valueOf((char) i);
    }
    
	public static void main (String[] args) {
	    String text = "DRO MSDI LBSWC GSDR CEWWOBC NOVSQRDC, GSDR MYVYBPEV ZBYNEMO SX DRO WKBUOD CDKXNC KXN RKGKSSKX WECSM CZSVVSXQ YXDY LOKMROC";
		for (int i = 0; i < 26; i++){
		    System.out.println(cipherShift(i, text));
		}
	}
}