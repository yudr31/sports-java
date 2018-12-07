package com.spring.boot.sports.util;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * @author yuderen
 * @version 2018/3/6 16:34
 */
public class Base64Util {

    private static final char[] CA = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
    public static final int[] IA = new int[256];
    static {
        Arrays.fill(IA, -1);
        for (int i = 0, iS = CA.length; i < iS; i++)
            IA[CA[i]] = i;
        IA['='] = 0;
    }

    private static char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".toCharArray();

    /***
     * 解Base64为Byte数组
     *
     * @param chars
     * @param offset
     * @param charsLen
     * @return
     */
    public final static byte[] decode2ByteArray(char[] chars, int offset, int charsLen) {
        // Check special case
        if (charsLen == 0) {
            return new byte[0];
        }
        int sIx = offset, eIx = offset + charsLen - 1; // Start and end index after trimming.
        // Trim illegal chars from start
        while (sIx < eIx && IA[chars[sIx]] < 0) {
            sIx++;
        }
        // Trim illegal chars from end
        while (eIx > 0 && IA[chars[eIx]] < 0) {
            eIx--;
        }
        // get the padding count (=) (0, 1 or 2)
        int pad = chars[eIx] == '=' ? (chars[eIx - 1] == '=' ? 2 : 1) : 0; // Count '=' at end.
        int cCnt = eIx - sIx + 1; // Content count including possible separators
        int sepCnt = charsLen > 76 ? (chars[76] == '\r' ? cCnt / 78 : 0) << 1 : 0;
        int len = ((cCnt - sepCnt) * 6 >> 3) - pad; // The number of decoded bytes
        byte[] bytes = new byte[len]; // Preallocate byte[] of exact length
        // Decode all but the last 0 - 2 bytes.
        int d = 0;
        for (int cc = 0, eLen = (len / 3) * 3; d < eLen;) {
            // Assemble three bytes into an int from four "valid" characters.
            int i = IA[chars[sIx++]] << 18 | IA[chars[sIx++]] << 12 | IA[chars[sIx++]] << 6 | IA[chars[sIx++]];
            // Add the bytes
            bytes[d++] = (byte) (i >> 16);
            bytes[d++] = (byte) (i >> 8);
            bytes[d++] = (byte) i;
            // If line separator, jump over it.
            if (sepCnt > 0 && ++cc == 19) {
                sIx += 2;
                cc = 0;
            }
        }
        if (d < len) {
            // Decode last 1-3 bytes (incl '=') into 1-3 bytes
            int i = 0;
            for (int j = 0; sIx <= eIx - pad; j++)
                i |= IA[chars[sIx++]] << (18 - j * 6);

            for (int r = 16; d < len; r -= 8)
                bytes[d++] = (byte) (i >> r);
        }
        return bytes;
    }

    /**
     * 解Base64为Byte数组
     *
     * @param s
     * @return byte[]
     */
    public final static byte[] decode2ByteArray(String s) {
        // Check special case
        int sLen = s.length();
        if (sLen == 0) {
            return new byte[0];
        }
        int sIx = 0, eIx = sLen - 1; // Start and end index after trimming.
        // Trim illegal chars from start
        while (sIx < eIx && IA[s.charAt(sIx) & 0xff] < 0) {
            sIx++;
        }
        // Trim illegal chars from end
        while (eIx > 0 && IA[s.charAt(eIx) & 0xff] < 0) {
            eIx--;
        }
        // get the padding count (=) (0, 1 or 2)
        int pad = s.charAt(eIx) == '=' ? (s.charAt(eIx - 1) == '=' ? 2 : 1) : 0; // Count '=' at end.
        int cCnt = eIx - sIx + 1; // Content count including possible separators
        int sepCnt = sLen > 76 ? (s.charAt(76) == '\r' ? cCnt / 78 : 0) << 1 : 0;
        int len = ((cCnt - sepCnt) * 6 >> 3) - pad; // The number of decoded bytes
        byte[] dArr = new byte[len]; // Preallocate byte[] of exact length
        // Decode all but the last 0 - 2 bytes.
        int d = 0;
        for (int cc = 0, eLen = (len / 3) * 3; d < eLen;) {
            // Assemble three bytes into an int from four "valid" characters.
            int i = IA[s.charAt(sIx++)] << 18 | IA[s.charAt(sIx++)] << 12 | IA[s.charAt(sIx++)] << 6 | IA[s.charAt(sIx++)];
            // Add the bytes
            dArr[d++] = (byte) (i >> 16);
            dArr[d++] = (byte) (i >> 8);
            dArr[d++] = (byte) i;
            // If line separator, jump over it.
            if (sepCnt > 0 && ++cc == 19) {
                sIx += 2;
                cc = 0;
            }
        }
        if (d < len) {
            // Decode last 1-3 bytes (incl '=') into 1-3 bytes
            int i = 0;
            for (int j = 0; sIx <= eIx - pad; j++) {
                i |= IA[s.charAt(sIx++)] << (18 - j * 6);
            }
            for (int r = 16; d < len; r -= 8) {
                dArr[d++] = (byte) (i >> r);
            }
        }
        return dArr;
    }

    /**
     * 将Byte数组进行Base64编码
     *
     * @param data
     * @return String
     */
    public final static String encode(byte[] data) {
        char[] out = new char[((data.length + 2) / 3) * 4];
        for (int i = 0, index = 0; i < data.length; i += 3, index += 4) {
            boolean quad = false;
            boolean trip = false;
            int val = (0xFF & (int) data[i]);
            val <<= 8;
            if ((i + 1) < data.length) {
                val |= (0xFF & (int) data[i + 1]);
                trip = true;
            }
            val <<= 8;
            if ((i + 2) < data.length) {
                val |= (0xFF & (int) data[i + 2]);
                quad = true;
            }
            out[index + 3] = alphabet[(quad ? (val & 0x3F) : 64)];
            val >>= 6;
            out[index + 2] = alphabet[(trip ? (val & 0x3F) : 64)];
            val >>= 6;
            out[index + 1] = alphabet[val & 0x3F];
            val >>= 6;
            out[index + 0] = alphabet[val & 0x3F];
        }
        return new String(out);
    }

    public static void main(String[] args) {
        System.out.println(decode("c2VsZWN0IGlkLHN1cHBsaWVyX2lkLGNhcmRfdHlwZSxjYXJkbm8sY2RrZXksdmFsdWUsb3BlcmF0b3IscHJpY2Usc2FsZV9wcmljZSxsb2NhdGlvbixwcm92aW5jZSxpbnRpbWUsb3V0dGltZSxzdGF0ZSxvdXRfYmF0Y2hubyxpbl9iYXRjaG5vLHZhbGlkX2VuZCxvcGVyX3BlcnNvbixsaW1pdF9ydWxlLGNhcmRfYXJlYSxleHRlbmRlZF9wZXJpb2QscmVjaGFyZ2VfbnVtYmVyLHpvbmVfZGVzY3JpcHRpb24sc3BlY2lhbF9jaGFubmVsLHJlbWFyayxkZXB0X2FyZWEsc29ydERhdGUgZnJvbSBjZGtleSB3aGVyZSBzdGF0ZSA9IDEgYW5kIGluX2JhdGNobm8gPSAnMjAxMzEyMjRfMDAxMV9DTTUwXzIwMTMxMjI2MTMwNjAwXzM2XzEzMTI2MTAwMTc1MDQ5OTAzXzEzMTQ2MTAwMTc2MDE3OTAyJyBvcmRlciBieSBjYXJkbm8gbGltaXQgMCAsIDE="));
    }

    /**
     * 默认以GBK转化为Byte数组后，再Base64编码
     *
     * @param string
     * @return String
     */
    public static String encode(String string) {
        return encode(string, "GBK");
    }

    /**
     * 按照传入的编码解码后Base64
     *
     * @param string
     * @return String
     */
    public static String encode(String string, String charset) {
        try {
            return encode(string.getBytes(charset));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("charset [" + charset + "] is wrong", e);
        }
    }

    /***
     * 解Base64后默认以GBK编码转化为String类型值
     *
     * @param string
     * @return String
     */
    public static String decode(String string) {
        return decode(string, "GBK");
    }

    /***
     * 解Base64后默认以传入编码转化为String类型值
     *
     * @param string
     * @return String
     */
    public static String decode(String string, String charset) {
        try {
            return new String(decode2ByteArray(string), charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("charset [" + charset + "] is wrong", e);
        }
    }

}
