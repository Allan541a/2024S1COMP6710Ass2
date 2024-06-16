package comp1110.ass2;
import java.util.*;

//  This class represents all the exhausted cats.

//  Authored by Yuxuan Shi U7789617.
public class ExhaustedCats {
    ExhaustedCat[] exhaustedcats;


    /**
     * Construct an array containing all exhausted cats.
     * @param str a string like "B0302B0312".
     *            The "exhausted cats" string in the "game state" string array.
     */
    public ExhaustedCats(String str){
        int num = str.length() / 5;
        this.exhaustedcats = new ExhaustedCat[num];
        int index = 0;

        for(int i = 0; i < num; i ++){
            String s = str.substring(index, index + 5);
            this.exhaustedcats[i] = new ExhaustedCat(s);
            index += 5;
        }
    }


    /**
     * @return The "exhausted cats" string in the "game state" string array.
     *  all cats string should be sorted first by colour in alphabetical order,
     *  then row and column coordinate in ascending order.
     */

    public String toString(){
        String str = "";
        String[] s = new String[this.exhaustedcats.length];
        for(int i = 0; i < this.exhaustedcats.length; i ++){
            s[i] = this.exhaustedcats[i].toString();
        }
        Arrays.sort(s, new Comparator<String>() {
            public int compare(String s1, String s2) {
                String color1 = s1.substring(0, 1);
                String color2 = s2.substring(0, 1);
                int row1 = Integer.parseInt(s1.substring(1, 3));
                int row2 = Integer.parseInt(s2.substring(1, 3));
                int col1 = Integer.parseInt(s1.substring(3));
                int col2 = Integer.parseInt(s2.substring(3));

                if (!color1.equals(color2)) {
                    return color1.compareTo(color2);
                }
                else if (row1 != row2) {
                    return Integer.compare(row1, row2);
                }
                else {
                    return Integer.compare(col1, col2);
                }
            }
        });

        for(int i = 0; i < s.length; i ++){
            str += s[i];
        }

        return str;
    }


    //  Add a cat.
    public void add_cat(ExhaustedCat cat){
        ExhaustedCat[] list = new ExhaustedCat[this.exhaustedcats.length + 1];
        System.arraycopy(this.exhaustedcats, 0, list, 0, this.exhaustedcats.length);
        list[list.length - 1] = cat;
        this.exhaustedcats = list;
    }
}
