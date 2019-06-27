

import org.json.JSONObject;

import java.io.*;
import java.io.FileReader;
import java.text.ParseException;
import java.util.*;

public class Question2 {
    // key: product_id, value: Set<user_id>
    static Map<String, Set<String>> productUser = new HashMap<>();
    // key: product_id, value: quantity
    static Map<String, Integer> quantityCount = new HashMap<>();

    public static void main(String[] args) throws IOException {
        ProductsRank("/Users/leocai/Study/Q2_data.csv");
    }

    // time complexity: O(N + P)
    public static void ProductsRank(String path) throws IOException {
        readJson(path);
        rankBasedOnUser();
        rankBasedOnQuantity();
    }

    // time complexity: O(P)
    public static void rankBasedOnUser() {
        List<String> res = new ArrayList<>();
        int count = 0;
        // find the maximum count
        for (Map.Entry<String, Set<String>> entry : productUser.entrySet()) {
            int size = entry.getValue().size();
//            System.out.println(entry.getKey() + " " + size);
            if (count < size) {
                count = size;
            }
        }
        // add the product_id with the maximum count into list
        for (Map.Entry<String, Set<String>> entry : productUser.entrySet()) {
            int size = entry.getValue().size();
            if (count == size) {
                res.add(entry.getKey());
            }
        }
        System.out.println("Most popular product(s) based on the number of purchasers: " + res);
    }

    // time complexity: O(P)
    public static void rankBasedOnQuantity() {
        List<String> res = new ArrayList<>();
        int count = 0;
        // find the maximum count
        for (Map.Entry<String, Integer> entry : quantityCount.entrySet()) {
//            System.out.println(entry.getKey() + " " + entry.getValue());
            if (count < entry.getValue()) {
                count = entry.getValue();
            }
        }
        // add the product_id with the maximum count into list
        for (Map.Entry<String, Integer> entry : quantityCount.entrySet()) {
            if (count == entry.getValue()) {
                res.add(entry.getKey());
            }
        }
        System.out.println("Most popular product(s) based on the quantity of goods sold: " + res);
    }

    // time complexity: O(N)
    public static void readJson(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        StringBuilder sb = new StringBuilder();

        String line = null;

        while ((line = reader.readLine()) != null) {
            line = line.replaceAll("\"\"", "\"");
            line = line.substring(1, line.length() - 1);
//            System.out.println(line);
            JSONObject info = new JSONObject(line);
            String uId = (String) info.get("user_id");
            String pId = (String) info.get("product_id");
            int q = (int) info.get("quantity");

            productUser.putIfAbsent(pId, new HashSet<>());
            productUser.get(pId).add(uId);

            quantityCount.put(pId, quantityCount.getOrDefault(pId, 0) + q);
        }
    }
}
