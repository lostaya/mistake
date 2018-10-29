package test;

import java.util.*;

/**
 * Created by yp on 18-10-8.
 */
public class Fun {

    //MIN
    public static String[] findRestaurant(String[] list1, String[] list2) {
        List<String> bbq = new ArrayList<String>();
        int minsolo=0;

        for(int i=0;i<list1.length;i++){
            for(int j=0;j<list2.length;j++){
                if(list1[i].equals(list2[j])){
                    if(bbq.size()==0){
                        minsolo = i+j;
                        bbq.add(list1[i]);
                    }else if(i+j<minsolo){
                        minsolo = i+j;
                        bbq.add(list1[i]);
                    }else if(i+j==minsolo){
                        bbq.add(list1[i]);
                    }
                }
            }
        }
        String[] strings = new String[bbq.size()];

        bbq.toArray(strings);
        return strings;
    }


    public  static int largestRectangleArea(int[] heights) {
        int maxsize = 0;

        //start
        for(int i=0;i<heights.length;i++){
            int minttall= 0;
            int slen=0;
            //end
            for(int j=i;j<heights.length;j++){

                //初始化
                if(i==j){
                    //maxsize = heights[i];
                    minttall = heights[i];
                    slen = 1;
                    if(slen*minttall>=maxsize){
                        maxsize = slen*minttall;
                    }
                }else if(heights[j]<=minttall){
                    slen +=1;
                    minttall=heights[j];
                    //面积计算
                    if(slen*minttall>maxsize){
                        maxsize = slen*minttall;
                    }

                }else if(heights[j]>minttall){
                    slen +=1;
                    //面积计算
                    if(slen*minttall>maxsize){
                        maxsize = slen*minttall;
                    }

                }
            }

        }
        return maxsize;
    }


    public static int[] findErrorNums(int[] nums) {

        int[] miss=new int[]{};
        List<Integer> lol = new ArrayList<Integer>();
        int count=0;
        int old=0;
        lol.add(0);
        for(int i=0;i<nums.length;i++){

            if(i>=1) {
                count=nums[i]-nums[0];
                Collections.sort(lol);
                for(int j=0;j<lol.size();j++){
                    if(lol.get(j).equals(count)){
                        miss=new int[]{nums[i],(i+nums[0])};
                        break;
                    }
                }
                lol.add(count);
            }
        }


        return miss;
    }




    public static int minDistance(String word1, String word2) {
        int ans=0;
        int len1=word1.length();
        int len2=word2.length();
        String largestr;
        String minstr;
        if(len1>=len2){
            minstr=word2;
            largestr=word1;
        }else{
            minstr=word1;
            largestr=word2;
        }

        for(int i=0;i<largestr.length();i++){
            for(int j=minstr.length();j>0;j--){
                //get_Subset(minstr,0,j,b);
                //char ws=mstr.charAt(i);


            }
        }


        return ans;
    }



    private static void get_Subset(String str, int start, int end, boolean[] b) {
        if(start==end){//当start==end时，即集合中的所有元素都已经在或者不在该子集中，输出该子集后，return跳出该层递归。
        int i = 0;
        for(;i<end;i++){
            if(b[i]){
                System.out.print(str.charAt(i));
            }
        }

        System.out.print(" ");
        return ;
        }else{
            b[start] = false;//代表数组中索引为start的元素不在该子集中
            get_Subset(str, start+1, end, b);//而后进入递归，元素索引向后加一，同样索引为start+1的元素也有在或者不在该子集中两种可能性
            b[start] = true;
            get_Subset(str, start+1, end, b);
        }
    }



    //10 converter 7
    public static String convertToBase7(int num) {

        int fenm=-1;
        int yu=0;
        String str="";

        int number = Math.abs(num);
        while (fenm!=0){
            yu   = number%7;
            fenm = number/7;
            number  = fenm;
            str = Integer.toString(yu)+str;
        }
        if(num<0){
            str="-"+str;
        }
        if (num==0){
            str = "0";
        }
        return str;
    }






    public static String[] findWords(String[] words) {

        String[] str = new String[]{};
        String[] str1 = new String[]{"q","w","e","r","t","y","u","i","o","p"};
        String[] str2 = new String[]{};
        String[] str3 = new String[]{};
        for (int i=0;i<words.length;i++){



        }


        return str;
    }
































    public static void main(String[] args) {


        System.out.println(convertToBase7(-7));
    }
}
