
class HashTableCalculator {
    
    public static int[][] hash(String typeOfHash, int[][] arr) {
        int n = arr.length;
        int m = arr[0].length;
        int[][] res = new int[n][m];
        for (int i = 0; i <n; i++) {
            for (int j = 0; j < m; j++) {
                if (typeOfHash.equals("A")) {
                    res[i][j] = partA(i, j);
                }
                else if (typeOfHash.equals("B")) {
                    res[i][j] = partB(i, j);
                }
                else {
                    res[i][j] = 0;
                }
            }
        }
        return res;
    }
    
    public static int partA(int r, int c) {
        return c + (r % 11);
    }
    
    public static int partB(int r, int c) {
        return c * (r % 11);
    }
    
    public static void printArr(int[][] arr) {
        String res = "\t";
        for (int i = 0; i <= arr.length; i++) {
            if (i == 0) {
                for (int c = 0; c < arr[i].length; c++) {
                    res += c + "\t";
                }
            }
            else{
                for (int j = 0; j <= arr[i - 1].length; j++) {
                    if (j == 0) {
                        res += (i - 1) + "\t";
                    }
                    else {
                        res += arr[i - 1][j - 1] + "\t";
                    }
                }
            }
            res += "\n";
        }
        System.out.println(res);
    }
    
	public static void main (String[] args) {
	    int n = 11;
	    int m = 11;
	    int[][] hashTable = new int[n][m];
	    int[][] newTableA = hash("A", hashTable);
	    int[][] newTableB = hash("B", hashTable);
	    System.out.println("\n\tA\n");
	    printArr(newTableA);
	    System.out.println("\n\tB\n");
	    printArr(newTableB);
	}
}