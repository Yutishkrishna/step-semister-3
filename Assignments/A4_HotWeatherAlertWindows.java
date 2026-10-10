public class A4_HotWeatherAlertWindows {
 static int countAlerts(int[] readings,int k,int threshold){
  long sum=0;
  for(int i=0;i<k;i++) sum+=readings[i];
  int count=sum>=(long)k*threshold?1:0;
  for(int i=k;i<readings.length;i++){
   sum+=readings[i]-readings[i-k];
   if(sum>=(long)k*threshold) count++;
  }
  return count;
 }
 public static void main(String[] args){
  System.out.println(countAlerts(new int[]{2,2,2,2,5,5,5,8},3,4));
 }
}