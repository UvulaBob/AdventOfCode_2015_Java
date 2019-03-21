public class Test {

    public static void main(String[] args) {
        System.out.println(String.format("%32s", Integer.toBinaryString(123)).replace(' ', '0') + "    123");
        System.out.println(String.format("%32s", Integer.toBinaryString(-124)).replace(' ', '0') + "   -124");
        System.out.println(String.format("%32s", Integer.toBinaryString(65535)).replace(' ', '0') + "  65535");
        System.out.println(String.format("%32s", Integer.toBinaryString(~123 & 65535)).replace(' ', '0') + "  65412");
        
        System.out.println((byte) 123);
        System.out.println((byte) ~123);
        System.out.println((byte) ~123 & 65535);
        
        System.out.println("Done!");
        
        
    }
}
