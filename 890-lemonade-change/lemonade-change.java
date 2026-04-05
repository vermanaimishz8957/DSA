class Solution {
    public boolean lemonadeChange(int[] bills) {
        int five_count = 0, ten_count=0, twenty_count=0; //Initially everything 0 
        for(int i=0;i<bills.length;i++){ // Iterate with Array
            if(bills[i]==5){ // if gives 5$ no problem 
                five_count++; // +1 (5$ Note)

            }else if(bills[i]==10){ // if he gives first time 10$ 
                if(five_count==0){  // if don't have 5$ note to change 
                    return false; // return false: beacuse you have nothing to return chnage
                }
                five_count--;
                ten_count++;
            } else{
                if(five_count > 0 && ten_count>0){ // both are available(5$ note and 10 $ note) 
                    five_count--;
                    ten_count--;
                    twenty_count++;
                }else if(five_count >=3){
                    five_count-=3;
                    twenty_count++;
                } else {
                    return false;
                }
            }
        }
        return true;
        
    }
}