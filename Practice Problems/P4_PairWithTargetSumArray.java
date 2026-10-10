import java.util.*;
public class P4_PairWithTargetSumArray {
 static boolean hasPairWithSum(int[] nums,int target){
  Set<Integer> seen=new HashSet<>();
  for(int n:nums){
   long need=(long)target-n;
   if(need>=Integer.MIN_VALUE&&need<=Integer.MAX_VALUE&&seen.contains((int)need)) return true;
   seen.add(n);
  }
  return false;
 }
 static boolean bruteForce(int[] nums,int target){
  for(int i=0;i<nums.length;i++) for(int j=i+1;j<nums.length;j++)
   if((long)nums[i]+nums[j]==target) return true;
  return false;
 }
 public static void main(String[] args){
  System.out.println(hasPairWithSum(new int[]{2,7,11,15},9));
  System.out.println(hasPairWithSum(new int[]{3,4,6},20));
 }
}