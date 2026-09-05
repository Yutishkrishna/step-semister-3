public class A3_TrafficSignalStreakAnalyzer {

    static void findLongestStreak(String signalLog) {
        char longestChar = signalLog.charAt(0);
        int longestLen = 1;
        char currentChar = signalLog.charAt(0);
        int currentLen = 1;

        for (int i = 1; i < signalLog.length(); i++) {
            if (signalLog.charAt(i) == currentChar) {
                currentLen++;
            } else {
                currentChar = signalLog.charAt(i);
                currentLen = 1;
            }
            if (currentLen > longestLen) {
                longestLen = currentLen;
                longestChar = currentChar;
            }
        }

        System.out.println("Longest Streak: '" + longestChar + "' repeated " + longestLen + " times");
    }

    public static void main(String[] args) {
        findLongestStreak("RRGGGYRR");
        findLongestStreak("RRRRYYGG");
    }
}
