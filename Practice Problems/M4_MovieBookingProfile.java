public class M4_MovieBookingProfile {

    // Fully compliant JavaBean with a write-only OTP property
    static class MovieBookingProfile {
        private String name;
        private boolean confirmed;
        private String otp; // write-only field, no getter anywhere on class

        // 1. Mandatory public no-argument constructor for JavaBean compliance
        public MovieBookingProfile() {
            this.name = "";
            this.confirmed = false;
            this.otp = null;
        }

        // 2. Convenience constructor chained via this()
        public MovieBookingProfile(String name) {
            this(); // chains to no-arg constructor first
            this.name = name;
        }

        // 3. JavaBean getX/setX pair for name
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        // 4. JavaBean isX/setX pair for boolean property confirmed
        public boolean isConfirmed() {
            return confirmed;
        }

        public void setConfirmed(boolean confirmed) {
            this.confirmed = confirmed;
        }

        // 5. True write-only property: setter exists for form binding, but NO getter exists anywhere
        public void setOtp(String otp) {
            if (otp != null && otp.matches("\\d{4,6}")) {
                this.otp = otp;
            } else {
                this.otp = null; // or reject invalid format
            }
        }

        // Method to verify OTP without exposing the stored OTP via any getter
        public boolean verifyOtp(String enteredOtp) {
            return this.otp != null && this.otp.equals(enteredOtp);
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Problem 4: MovieBookingProfile JavaBean & OTP Property ---");

        // Example 1: Constructor chaining and getter
        MovieBookingProfile p1 = new MovieBookingProfile("Rahul Dev");
        System.out.println("Profile Name: " + p1.getName()); // Output: Rahul Dev

        // Example 2: Boolean property getter (isConfirmed) and setter
        p1.setConfirmed(true);
        System.out.println("isConfirmed(): " + p1.isConfirmed()); // Output: true

        // Example 3: Write-only OTP property
        p1.setOtp("4471");
        System.out.println("OTP set successfully (no getOtp() method exists).");
        System.out.println("OTP Verification check for '4471': " + p1.verifyOtp("4471"));
        System.out.println("OTP Verification check for '0000': " + p1.verifyOtp("0000"));
    }
}