package org.gui;

class GFG {

    // Function to return ASCII
// value of a character
    static int val(char c)
    {
        if (c >= '0' && c <= '9')
            return(int)c - '0';
        else
            return(int)c - 'A' + 10;
    }

    // Function to convert a number
// from given base to decimal number
    static int toDeci(String str, int base)
    {

        // Stores the length
        // of the String
        int len = str.length();

        // Initialize power of base
        int power = 1;

        // Initialize result
        int num = 0;

        // Decimal equivalent is str[len-1]*1
        // + str[len-2]*base + str[len-3]*(base^2) + ...
        for(int i = len - 1; i >= 0; i--)
        {

            // A digit in input number must
            // be less than number's base
            if (val(str.charAt(i)) >= base)
            {
                System.out.printf("Invalid Number");
                return -1;
            }

            // Update num
            num += val(str.charAt(i)) * power;

            // Update power
            power = power * base;
        }
        return num;
    }

    // Function to return equivalent
// character of a given value
    static char reVal(int num)
    {
        if (num >= 0 && num <= 9)
            return(char)(num + '0');
        else
            return(char)(num - 10 + 'A');
    }

    // Function to convert a given
// decimal number to a given base
    static String fromDeci(int base, int inputNum)
    {

        // Store the result
        String res = "";

        // Repeatedly divide inputNum
        // by base and take remainder
        while (inputNum > 0)
        {

            // Update res
            res += reVal(inputNum % base);

            // Update inputNum
            inputNum /= base;
        }

        // Reverse the result
        res = reverse(res);

        return res;
    }

    // Function to convert a given number
// from a base to another base
    static void convertBase(String s, int a, int b)
    {

        // Convert the number from
        // base A to decimal
        int num = toDeci(s, a);

        // Convert the number from
        // decimal to base B
        String ans = fromDeci(b, num);

        // Print the result
        System.out.print(ans);
    }

    static String reverse(String input)
    {
        char[] a = input.toCharArray();
        int l, r = a.length - 1;
        for(l = 0; l < r; l++, r--)
        {
            char temp = a[l];
            a[l] = a[r];
            a[r] = temp;
        }
        return String.valueOf(a);
    }

    // Driver Code
    public static void main(String[] args)
    {

        // Given input
        String s = "10B";
        int a = 16, b = 10;

        // Function Call
        convertBase(s, a, b);
    }
}

// This code is contributed by 29AjayKumar

