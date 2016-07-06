package example.misc;

import org.json.JSONObject;

public class JsonCompare {

    public static void main(String[] args) {
        final JSONObject o1 = new JSONObject("{\'a\': 'hoge', b: 'bar', c: [1,3,5], bb: {e: 'あ'}}");
        final JSONObject o2 = new JSONObject("{b: 'bar',  \nc:[1,\n3,5], a: \n'hoge', \"bb\": {e: '\\u3042'}}");
        System.out.println(o1.toString());
        System.out.println(o2.toString());
        System.out.println(o1.toString().equals(o2.toString()));
    }
}
