package com.example.learndayandroid;

/**
 * @author By skytofire
 * @explain 创建说明
 * @date 创建时间:2020/5/10.
 */
import java.util.*;
public class Mains{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            int num = scanner.nextInt();
            int[] nums = new int[num];
            for(int i = 0;i<num;i++){
                nums[i]=scanner.nextInt();
            }
            int a=getCountright(nums,0);
            int b=getCountright(nums,0);
            for(int i = 1;i<num;i++){
                a = getCountleft(nums,i)+getCountright(nums,i);
                System.out.print(a+" ");
                //b=Math.min(a,b);
            }
            //System.out.println(b);
        }
    }
    public static int getCountleft(int[] nums,int num){
        int count = 0;
        if(num==0){
            return count;
        }
        int str = nums[num];
        for(int i=num;i>0;i--){
            if(str>nums[i-1]){
                str=nums[i-1];
            }else{
                count++;
            }
        }
        return count;
    }
    public static int getCountright(int[] nums,int num){
        int count = 0;
        if(num==nums.length-1){
            return count;
        }
        int str = nums[num];
        for(int i=num;i<nums.length-1;i++){
            if(str>nums[i+1]){
                str=nums[i+1];
            }else{
                count++;
            }
        }
        return count;
    }
}
