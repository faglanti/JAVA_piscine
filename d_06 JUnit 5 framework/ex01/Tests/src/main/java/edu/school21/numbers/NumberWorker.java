package edu.school21.numbers;

public class NumberWorker {

    public boolean isPrime(int number){
        int num = number;
        int i;
        i = 2;
        if (num <= 1) {
            throw new IllegalNumberException();
        }
        else if (num > 1) {
            while (i * i <= num) {
                if (num % i == 0) {
                    return false;
                }
                i++;
            }
        }
        return true;
    }

    public int digitsSum(int number) {
        if (number < 0)
            return 0;
        int a = number;
        int res = 0;
        while (a > 0) {
            res += a % 10;
            a /= 10;
        }
    return res;
    }

    public static class IllegalNumberException extends RuntimeException{
        public IllegalNumberException(){}
    }
}
