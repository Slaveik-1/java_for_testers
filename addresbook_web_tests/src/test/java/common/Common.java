package common;

import java.util.Random;

public class Common {


    public static String randomString(int n){
        var r = new Random();
        var result = "";
        for (int i =0;i<n;i++){
            result = result+(char)('a'+r.nextInt(26/*33*/));
        }
//        if (n<5){
//            result = result+ '\'';
//        }
        return result;
    }
}
