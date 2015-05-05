package org.dosomething.hashery;

import java.util.ArrayList;

/**
 * Convert integers to a string representation.
 *
 * Caveats/Limitations:
 *  - # of words to use in a code is dictated by # of arrays provided. It's basically array.length ^ (# of arrays).
 *  - Word arrays provided must be the same length
 *
 * @todo Throw exceptions instead of `assert false`
 */
public class Hashery {

    private int mBase;
    private int mMaxValue;
    private int mCodeLength;
    private ArrayList<ArrayList<String>> mWords;

    private Hashery() {}

    /**
     * Constructor
     *
     * @param words Nested ArrayList of words to decode values with
     */
    public Hashery(ArrayList<ArrayList<String>> words) {

        if (words.isEmpty()) {
            assert false;
        }

        int testSize = 0;
        for (int i = 0; i < words.size(); i++) {
            if (i == 0) {
                testSize = words.get(i).size();
                if (testSize == 0) {
                    assert false;
                }
            }
            else if (testSize != words.get(i).size()) {
                assert false;
            }
        }

        mWords = words;
        mCodeLength = mWords.size();
        mBase = mWords.get(0).size();

        int base = mBase;
        for (int i = 1; i < mWords.size(); i++) {
            base *= mBase;
        }
        mMaxValue = base - 1; // - 1 because we start converting at 0
    }

    /**
     * Returns max possible value that can be decoded given the nested list of words provided in the ctor.
     *
     * @return int
     */
    public int getMaxValue() {
        return mMaxValue;
    }

    /**
     * Encode a base-10 number to a unique string representation.
     *
     * @param val Number to encode
     * @return String
     */
    public String encode(int val) {
        if (val > mMaxValue) {
            assert false;
        }

        ArrayList<Integer> baseConverted = baseConversion(val);

        return convertToWord(baseConverted);
    }

    /**
     * This is the first step in the process of converting a number to its String representation. The
     * number gets converted from base-10 to base-A, where A is the length of an item in mWords.
     *
     * For example, if mWords[0] is an array of length 40, then we're converting to base-40.
     *
     * This then gives us an array of integers where the length of the resulting array is equal to
     * the length of mWords.
     *
     * @param val Base-10 number to convert
     * @return ArrayList representation of the base-A conversion
     */
    private ArrayList<Integer> baseConversion(int val) {
        ArrayList<Integer> result = new ArrayList<Integer>();

        int quotient = val;
        while (quotient != 0) {
            int dividend = quotient;
            quotient = dividend / mBase;
            int remainder = dividend % mBase;

            result.add(0, new Integer(remainder));
        }

        return result;
    }

    /**
     * This is the second step of the conversion. Maps the integer values of the base-A conversion
     * to corresponding strings in the mWords array of arrays.
     *
     * @param vals ArrayList representation of the base-A converted number
     * @return String
     */
    private String convertToWord(ArrayList<Integer> vals) {
        String result = "";
        int valPos = vals.size() - 1;
        for (int i = mCodeLength - 1; i >= 0; i--) {
            int val = 0;
            if (valPos >= 0) {
                val = vals.get(valPos).intValue();
                valPos--;
            }

            ArrayList<String> words = mWords.get(i);
            result = words.get(val) + result;
        }

        return result;
    }

    /**
     * @todo IMPLEMENT ME
     *
     *
     *
     * @param val String to decode
     * @return int
     */
    public int decode(String val) {
        return 0;
    }

}
