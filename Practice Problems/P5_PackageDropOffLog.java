public class P5_PackageDropOffLog {

    static abstract class DeliveryNote {
        String trackingId;

        public DeliveryNote(String trackingId) {
            this.trackingId = trackingId;
        }

        public abstract String confirmDelivery();

        // reuses the no-argument version and layers the signature on top - only one place holds the core message
        String confirmDelivery(String signature) {
            return confirmDelivery() + ", signed by " + signature;
        }
    }

    static class ParcelNote extends DeliveryNote {
        public ParcelNote(String trackingId) {
            super(trackingId);
        }

        @Override
        public String confirmDelivery() {
            return "Parcel " + trackingId + " delivered";
        }
    }

    static class LetterNote extends DeliveryNote {
        public LetterNote(String trackingId) {
            super(trackingId);
        }

        @Override
        public String confirmDelivery() {
            return "Letter " + trackingId + " delivered";
        }
    }

    static void logAll(DeliveryNote[] notes) {
        for (DeliveryNote note : notes) {
            System.out.println(note.confirmDelivery());
        }
    }

    public static void main(String[] args) {
        ParcelNote p = new ParcelNote("TRK-1");
        System.out.println(p.confirmDelivery());
        System.out.println(p.confirmDelivery("J. Smith"));

        DeliveryNote ref = p; // upcasting - ParcelNote stored as its parent type
        logAll(new DeliveryNote[]{ref, new LetterNote("TRK2")});
    }
}
