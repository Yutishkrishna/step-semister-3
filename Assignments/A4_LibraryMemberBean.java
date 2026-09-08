import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class A4_LibraryMemberBean {

    // Fully compliant JavaBean with write-once membershipId and write-only securityAnswer
    static class LibraryMember {
        private String membershipId;
        private String name;
        private boolean premiumMember;
        private String hashedSecurityAnswer; // one-way transformed, no getter anywhere

        // 1. Mandatory public no-argument constructor
        public LibraryMember() {
            this.membershipId = null;
            this.name = "";
            this.premiumMember = false;
            this.hashedSecurityAnswer = null;
        }

        // 2. Write-once property for membershipId
        public String getMembershipId() {
            return membershipId;
        }

        public void setMembershipId(String id) {
            // Only takes effect on the very first invocation; subsequent calls are silently ignored
            if (this.membershipId == null && id != null) {
                this.membershipId = id;
            }
        }

        // 3. Ordinary JavaBean property: name
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        // 4. Boolean property adhering to isX naming
        public boolean isPremiumMember() {
            return premiumMember;
        }

        public void setPremiumMember(boolean premiumMember) {
            this.premiumMember = premiumMember;
        }

        // 5. True write-only property: sets a deterministic one-way hash, NO getter exists
        public void setSecurityAnswer(String answer) {
            if (answer == null) {
                this.hashedSecurityAnswer = null;
                return;
            }
            try {
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                byte[] hash = md.digest(answer.trim().toLowerCase().getBytes(StandardCharsets.UTF_8));
                StringBuilder hex = new StringBuilder();
                for (byte b : hash) {
                    hex.append(String.format("%02x", b));
                }
                this.hashedSecurityAnswer = hex.toString();
            } catch (Exception e) {
                this.hashedSecurityAnswer = String.valueOf(answer.hashCode());
            }
        }

        // Verification method for staff check without exposing stored data
        public boolean verifySecurityAnswer(String candidate) {
            if (candidate == null || this.hashedSecurityAnswer == null) {
                return false;
            }
            try {
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                byte[] hash = md.digest(candidate.trim().toLowerCase().getBytes(StandardCharsets.UTF_8));
                StringBuilder hex = new StringBuilder();
                for (byte b : hash) {
                    hex.append(String.format("%02x", b));
                }
                return this.hashedSecurityAnswer.equals(hex.toString());
            } catch (Exception e) {
                return false;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Assignment 4: LibraryMember JavaBean & Security Answer Property ---");

        LibraryMember m = new LibraryMember();
        m.setMembershipId("LIB-8841");
        m.setName("Priya Nair");
        m.setPremiumMember(true);

        System.out.println("Membership ID: " + m.getMembershipId()); // "LIB-8841"
        System.out.println("Name: " + m.getName());                   // "Priya Nair"
        System.out.println("isPremiumMember(): " + m.isPremiumMember()); // true

        // Test write-once behavior
        m.setMembershipId("FAKE-0000");
        System.out.println("Membership ID after overwrite attempt: " + m.getMembershipId()); // "LIB-8841"

        // Test write-only security answer
        m.setSecurityAnswer("BlueMountain");
        System.out.println("Security answer set (no getter exists on the class).");
        System.out.println("Verify 'BlueMountain': " + m.verifySecurityAnswer("BlueMountain"));
        System.out.println("Verify 'WrongAnswer':  " + m.verifySecurityAnswer("WrongAnswer"));
    }
}