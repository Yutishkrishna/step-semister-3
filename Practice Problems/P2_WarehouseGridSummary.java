public class P2_WarehouseGridSummary {
 static String warehouseSummary(int[][] grid){
  long total=0;int max=-1,row=-1,col=-1;
  for(int i=0;i<grid.length;i++) for(int j=0;j<grid[i].length;j++){
   int v=grid[i][j];total+=v;
   if(v>max){max=v;row=i;col=j;}
  }
  return "("+total+", ("+row+", "+col+"))";
 }
 public static void main(String[] args){
  System.out.println(warehouseSummary(new int[][]{{4,9,2},{7,1,6},{3,12,5}}));
 }
}