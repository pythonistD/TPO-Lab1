package cheboksarov;

import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

import static java.lang.Math.*;

@NoArgsConstructor
public class Task1 {
    public static BigDecimal factorialUsingRecursion(int n) throws IllegalArgumentException {
        if ((n == 0) || (n == 1)) {
            return BigDecimal.valueOf(1);
        }
        return BigDecimal.valueOf(n).multiply(factorialUsingRecursion(n-1));
    }

    public static BigDecimal factorialUsingIteration(int n) throws IllegalArgumentException {
        BigDecimal result = BigDecimal.valueOf(1);
        for (int i = 2; i <= n; i++) {
            result = result.multiply(BigDecimal.valueOf(i));
        }
        return result;
    }
    public static BigDecimal myArcsin(Double x, Integer n)throws IllegalArgumentException{
        if ((x < -1.0) || (x > 1.0)){
            throw new IllegalArgumentException("x must be between -1 and 1");
        }
        BigDecimal xBig = new BigDecimal(x);
        BigDecimal twoBig = new BigDecimal(2);
        BigDecimal fourBig = new BigDecimal(4);
        BigDecimal res = BigDecimal.ZERO;
       for(int i = 0; i < n; i++){
           BigDecimal iBig = new BigDecimal(i);
           BigDecimal numerator = factorialUsingIteration(2*i)
                   .multiply(xBig.pow(2*i+1));
           numerator = numerator.setScale(20, RoundingMode.HALF_UP);
           BigDecimal divider = fourBig.pow(i)
                   .multiply(factorialUsingIteration(i).pow(2))
                   .multiply((twoBig).multiply(iBig).add(BigDecimal.ONE));
           divider = divider.setScale(20, RoundingMode.HALF_UP);
           res = res.add(numerator.divide(divider, RoundingMode.FLOOR));
       }
       return res;
    }
}
