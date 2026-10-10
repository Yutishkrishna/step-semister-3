public class P1_LibraryCatalogLookup {
 static String findBook(String[][] catalog,String isbn){
  int low=0,high=catalog.length-1;
  while(low<=high){
   int mid=low+(high-low)/2;
   int cmp=catalog[mid][0].compareTo(isbn);
   if(cmp==0) return catalog[mid][1];
   if(cmp<0) low=mid+1; else high=mid-1;
  }
  return "Not Found";
 }
 public static void main(String[] args){
  String[][] books={{"0001112223","Introduction to Algebra"},{"0002223334","Beginning Python"},{"0003334445","Classic Mythology"},{"0004445556","Data and Society"},{"0005556667","European History"}};
  System.out.println(findBook(books,"0003334445"));
  System.out.println(findBook(books,"0009998887"));
 }
}