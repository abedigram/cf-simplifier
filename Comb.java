import java.util.Arrays;
import java.util.Vector;

public class Comb

{
    Vector<String> list = new Vector<String>();

      Vector<String> printCombinations(String sequence, int N) {
          char[] seqChar = sequence.toCharArray();
          char[] data = new char[N];
        for (int r = 0; r < sequence.length(); r++)
            combinations(seqChar, data, 0, N - 1, 0, r);
        list.add(sequence);
        return list;
    }

     void combinations(char[] sequence, char[] data, int start, int end, int index, int r)
    {
        if (index == r)
        {
            String s = new String(Arrays.copyOfRange(data,0,r));
            list.add(s);
        }
        for (int i = start; i <= end && ((end - i + 1) >= (r - index)); i++)
        {
            data[index] = sequence[i];
            combinations(sequence, data, i + 1, end, index + 1, r);
        }
    }


}