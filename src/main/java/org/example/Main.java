package org.example;

public class Main {
    public static void main(String[] args) {
        String text = "Alibaba or Alibubab? I do not know!";
        String pattern = "b*b";
        searchPattern(text, pattern);
    }

    public static void searchPattern(String source, String pattern) {
        int windowHash = 0;
        int patternHash = amountWithoutTheStar(pattern);
        int asterikPosition = starPosition(pattern);
        int s = source.length();
        int p = pattern.length();
        if (s < p) {
            System.out.println("Такой подстроки точно нет!");
        }
        int i;
        int hash;
        int diff = s - p + 1;
        for (int start = 0; start < diff; start++) {
            if (start == 0) {
                windowHash = sumOfTheCodes(source, pattern);
                hash = source.charAt(asterikPosition);
            } else {
                windowHash -= source.charAt(start - 1);
                windowHash += source.charAt(start + p - 1);
                hash = source.charAt(start + asterikPosition);
            }
            windowHash -= hash;
            if (windowHash == patternHash) {
                for (i = 0; i < p; i++) {
                    if (pattern.charAt(i) != '*' && source.charAt(start + i) != pattern.charAt(i)) {
                        System.out.println("Не подходит");
                    }
                }
                if (i == p) {
                    System.out.println("index = " + start);
                }
            }
            windowHash += hash;
        }
    }

    public static int starPosition(String pattern) {
        for (int i = 0; i < pattern.length(); i++) {
            if (pattern.charAt(i) == '*') {
                return i;
            }
        }
        return 0;
    }

    private static int sumOfTheCodes(String source, String pattern) {
        int result = 0;
        for (int i = 0; i < pattern.length(); i++) {
            result += source.charAt(i);
        }
        return result;
    }

    static int amountWithoutTheStar(String pattern) {
        int result = 0;
        for (int i = 0; i < pattern.length(); i++) {
            if (pattern.charAt(i) != '*') {
                result += pattern.charAt(i);
            }
        }
        return result;
    }
}