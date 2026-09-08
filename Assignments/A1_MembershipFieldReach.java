import java.util.LinkedHashMap;
import java.util.Map;

public class A1_MembershipFieldReach {

    // LibraryMember demonstrating intentionally chosen access levels per field
    static class LibraryMember {
        private String membershipPin; // inaccessible outside this class
        String branchCode;            // default (package-private): accessible only within library branch package
        protected double finesOwed;   // protected: accessible in package and to cross-package subclasses
        public String displayName;    // public: accessible from anywhere (kiosks, apps, portals)

        public LibraryMember(String membershipPin, String branchCode, double finesOwed, String displayName) {
            this.membershipPin = membershipPin;
            this.branchCode = branchCode;
            this.finesOwed = finesOwed;
            this.displayName = displayName;
        }

        public String getMembershipPin() {
            return membershipPin;
        }
    }

    // AccessChecker linter component with per-modifier aggregation
    static class AccessChecker {
        public static String classifyAccess(String fieldModifier, String accessorContext) {
            if (fieldModifier == null || accessorContext == null) {
                return "DENIED";
            }

            switch (accessorContext) {
                case "SAME_CLASS":
                    return "ALLOWED";

                case "SAME_PACKAGE":
                    if ("default".equals(fieldModifier) || "protected".equals(fieldModifier) || "public".equals(fieldModifier)) {
                        return "ALLOWED";
                    }
                    return "DENIED";

                case "DIFFERENT_PACKAGE":
                    if ("public".equals(fieldModifier)) {
                        return "ALLOWED";
                    }
                    return "DENIED";

                default:
                    return "DENIED";
            }
        }

        public static String summarizeByModifier(String[][] attempts) {
            String[] modifiers = {"private", "default", "protected", "public"};
            Map<String, int[]> counts = new LinkedHashMap<>();
            for (String mod : modifiers) {
                counts.put(mod, new int[]{0, 0}); // [allowed, denied]
            }

            if (attempts != null) {
                for (String[] attempt : attempts) {
                    if (attempt != null && attempt.length >= 2) {
                        String mod = attempt[0];
                        String context = attempt[1];
                        if (counts.containsKey(mod)) {
                            String decision = classifyAccess(mod, context);
                            if ("ALLOWED".equals(decision)) {
                                counts.get(mod)[0]++;
                            } else {
                                counts.get(mod)[1]++;
                            }
                        }
                    }
                }
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < modifiers.length; i++) {
                String mod = modifiers[i];
                int[] c = counts.get(mod);
                sb.append(mod).append(": ").append(c[0]).append(" allowed / ").append(c[1]).append(" denied");
                if (i < modifiers.length - 1) {
                    sb.append(" | ");
                }
            }

            return sb.toString();
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Assignment 1: Membership Field Reach Checker ---");

        // Test single classification
        System.out.println("private in SAME_CLASS: " + AccessChecker.classifyAccess("private", "SAME_CLASS"));
        System.out.println("protected in DIFFERENT_PACKAGE: " + AccessChecker.classifyAccess("protected", "DIFFERENT_PACKAGE"));

        // Test batch grouping by modifier
        String[][] attempts = {
            {"private", "SAME_CLASS"},
            {"private", "SAME_PACKAGE"},
            {"default", "SAME_PACKAGE"},
            {"default", "DIFFERENT_PACKAGE"},
            {"protected", "SAME_PACKAGE"},
            {"protected", "SAME_CLASS"},
            {"public", "DIFFERENT_PACKAGE"}
        };

        String summary = AccessChecker.summarizeByModifier(attempts);
        System.out.println("Modifier Summary:\n" + summary);

        LibraryMember member = new LibraryMember("4819", "MAIN-01", 15.50, "Priya Nair");
        System.out.println("Member Display Name: " + member.displayName + " | Branch: " + member.branchCode);
    }
}