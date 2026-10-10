import java.util.*;
public class P3_PairWithTargetSum {
 static boolean hasPairWithSum(int[] nums,int target){
  Set<Integer> seen=new HashSet<>();
  for(int n:nums){
   long need=(long)target-n;
   if(need>=Integer.MIN_VALUE&&need<=Integer.MAX_VALUE&&seen.contains((int)need)) return true;
   seen.add(n);
  }
  return false;
 }
 public static void main(String[] args){
  System.out.println(hasPairWithSum(new int[]{2,7,11,15},9));
  System.out.println(hasPairWithSum(new int[]{3,4,6},20));
 }
}