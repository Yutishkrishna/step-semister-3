public class A5_TicketPriceSlotFinder {
 static int findSlot(int[] prices,int newPrice){
  int low=0,high=prices.length;
  while(low<high){
   int mid=low+(high-low)/2;
   if(prices[mid]<newPrice) low=mid+1;
   else high=mid;
  }
  return low;
 }
 public static void main(String[] args){
  int[] p={120,150,200,260};
  System.out.println(findSlot(p,150));
  System.out.println(findSlot(p,210));
  System.out.println(findSlot(p,300));
 }
}