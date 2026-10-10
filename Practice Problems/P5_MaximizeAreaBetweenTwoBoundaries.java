public class P5_MaximizeAreaBetweenTwoBoundaries {
 static long maxContainerArea(int[] heights){
  int left=0,right=heights.length-1;long best=0;
  while(left<right){
   long area=(long)Math.min(heights[left],heights[right])*(right-left);
   best=Math.max(best,area);
   if(heights[left]<=heights[right]) left++;else right--;
  }
  return best;
 }
 public static void main(String[] args){
  System.out.println(maxContainerArea(new int[]{1,8,6,2,5,4,8,3,7}));
 }
}