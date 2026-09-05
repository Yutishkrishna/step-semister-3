public class A2_TypingAccuracyChecker {

    static void checkTypingAccuracy(String original, String typed) {
        int matched = 0;
        int firstMismatch = -1;
        int total = original.length();

        for (int i = 0; i < total; i++) {
            if (original.charAt(i) == typed.charAt(i)) {
                matched++;
            } else if (firstMismatch == -1) {
                firstMismatch = i;
            }
        }

        double accuracy = (matched * 100.0) / total;
        String accuracyStr = String.format("%.2f", accuracy);

        if (firstMismatch == -1) {
            System.out.println("Matched: " + matched + "/" + total + " | Accuracy: " + accuracyStr + "% | No Mismatches");
        } else {
            System.out.println("Matched: " + matched + "/" + total + " | Accuracy: " + accuracyStr
                    + "% | First Mismatch at position " + (firstMismatch + 1)
                    + " ('" + original.charAt(firstMismatch) + "' vs '" + typed.charAt(firstMismatch) + "')");
        }
    }

    public static void main(String[] args) {
        checkTypingAccuracy("hello world", "hello worlt");
        checkTypingAccuracy("coding", "coding");
    }
}
