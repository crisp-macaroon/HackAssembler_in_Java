package src;

class Bin {              // converts a base-10 number of string type to base-2 of string type (15-bit)
    public static String d2b (String a) {
        int x = Integer.parseInt(a);
        int m = 1;
        int[] bit = new int[15];
        String str = "";
        for (int i = 14;i >= 0;i--) {
            int temp = x>>i;
            bit[i] = temp & m;
            str = str + (bit[i]);
        }
        return str;
    }
}