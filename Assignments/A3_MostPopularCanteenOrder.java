import java.util.*;
public class A3_MostPopularCanteenOrder {
 static String mostPopular(String[] orders){
  Map<String,Integer> counts=new HashMap<>();
  for(String item:orders) counts.put(item,counts.getOrDefault(item,0)+1);
  String best=orders[0];
  for(String item:orders) if(counts.get(item)>counts.get(best)) best=item;
  return "(\""+best+"\", "+counts.get(best)+")";
 }
 public static void main(String[] args){
  System.out.println(mostPopular(new String[]{"dosa","idli","vada","dosa","idli","dosa","tea"}));
  System.out.println(mostPopular(new String[]{"tea","coffee","coffee","tea"}));
 }
}