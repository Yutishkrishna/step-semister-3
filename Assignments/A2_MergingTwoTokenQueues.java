import java.util.Arrays;
public class A2_MergingTwoTokenQueues {
 static int[] mergeTokens(int[] a,int[] b){
  int[] out=new int[a.length+b.length];
  int i=0,j=0,k=0;
  while(i<a.length&&j<b.length) out[k++]=a[i]<=b[j]?a[i++]:b[j++];
  while(i<a.length) out[k++]=a[i++];
  while(j<b.length) out[k++]=b[j++];
  return out;
 }
 public static void main(String[] args){
  System.out.println(Arrays.toString(mergeTokens(new int[]{3,8,15,20},new int[]{5,8,12})));
  System.out.println(Arrays.toString(mergeTokens(new int[]{},new int[]{4,9})));
 }
}