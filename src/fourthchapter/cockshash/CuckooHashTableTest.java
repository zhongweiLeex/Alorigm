package fourthchapter.cockshash;

public class CuckooHashTableTest {
    private static HashFamily<String> hashFamily = new HashFamily<String>() {
        @Override
        public int hash(String x, int which) {
            int hashVal = 0;
            switch (which){
                case 0:{
                    for (int i = 0; i < x.length(); i++) {
                        hashVal += x.charAt(i);
                    }
                    break;
                }
                case 1:
                    for (int i = 0; i < x.length(); i++) {
                        hashVal = 37*hashVal + x.charAt(i);
                    }
                    break;
            }
            return hashVal;
        }

        @Override
        public int getNumberOfFunctions() {
            return 2;
        }

        @Override
        public void generateNewFunctions() {
        }
    };

    public static void main(String[] args) {
        CuckooHashTable<String> cuckooHashTable = new CuckooHashTable<>(hashFamily,5);

        String[] strs = {"abc","aba","abcc","abca"};

        for (int i = 0; i < strs.length; i++) {
            cuckooHashTable.insert(strs[i]);
        }

        cuckooHashTable.printArray();
    }
}
