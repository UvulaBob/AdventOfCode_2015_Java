public class Part1 {

    public static void main(String[] args) {
        String currentString = "3113322113";

        for (int cycle = 1; cycle <= 40; cycle++) {
            String[] splitCurrentString = currentString.split("");
            StringBuilder sb = new StringBuilder();
            for (int currentIndex = 0; currentIndex < splitCurrentString.length; currentIndex++) {
                String currentChar = splitCurrentString[currentIndex];
                int currentCharCount = 1;
                for (int lookAheadIndex = currentIndex + 1; lookAheadIndex < splitCurrentString.length; lookAheadIndex++) {
                    if (splitCurrentString[lookAheadIndex].equals(currentChar)) {
                        currentCharCount++;
                    } else {
                        break;
                    }
                }
                sb.append(currentCharCount);
                sb.append(currentChar);
                currentIndex += currentCharCount - 1;
            }
            currentString = sb.toString();
        }

        System.out.println("String Length: " + currentString.length());
        System.out.println("Done!");
    }
}
