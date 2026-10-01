public class A4_FitZoneMembershipDesk {
    interface MembershipPlan {
        int months();
        double calculateFee();
        String name();
    }
    static class Monthly implements MembershipPlan {
        public int months(){return 1;}
        public double calculateFee(){return 1000;}
        public String name(){return "Monthly";}
    }
    static class Quarterly implements MembershipPlan {
        public int months(){return 3;}
        public double calculateFee(){return 1000*3*0.90;}
        public String name(){return "Quarterly";}
    }
    static class Annual implements MembershipPlan {
        public int months(){return 12;}
        public double calculateFee(){return 1000*12*0.75;}
        public String name(){return "Annual";}
    }
    static class Member {
        String name;
        Member(String n){name=n;}
        Membership buy(MembershipPlan p){ return new Membership(this,p); }
    }
    static class Membership {
        enum Status { ACTIVE, FROZEN, EXPIRED }
        Member member;
        MembershipPlan plan;
        private Status status=Status.ACTIVE;

        Membership(Member m, MembershipPlan p){
            member=m; plan=p;
            System.out.printf("%s membership created for %s. Fee: ₹%.2f. Status: Active.%n",
                    p.name(),m.name,p.calculateFee());
        }
        void checkIn(){
            if(status==Status.ACTIVE)
                System.out.println(member.name+" checked in successfully.");
            else
                System.out.println("Check-in denied: "+member.name+"'s membership is "+status+".");
        }
        void freeze(){
            if(status==Status.EXPIRED){
                System.out.println("Cannot freeze an Expired membership.");
                return;
            }
            if(status==Status.ACTIVE){
                status=Status.FROZEN;
                System.out.println(member.name+"'s membership frozen. Status: Frozen.");
            }
        }
        void unfreeze(){
            if(status==Status.EXPIRED){
                System.out.println("Cannot unfreeze an Expired membership.");
                return;
            }
            if(status==Status.FROZEN) status=Status.ACTIVE;
        }
        void expire(){
            status=Status.EXPIRED;
            System.out.println(member.name+"'s membership expired. Status: Expired.");
        }
    }
    public static void main(String[] args){
        Member asha=new Member("Asha"), ravi=new Member("Ravi");
        Membership a=asha.buy(new Quarterly());
        Membership r=ravi.buy(new Monthly());
        a.checkIn();
        a.freeze();
        a.checkIn();
        r.expire();
        r.freeze();
    }
}