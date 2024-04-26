import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.json.JSONObject;

public class CSVHeaderToJson {

    public static JSONObject getCSVHeaderAsJson(String filePath) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String headerLine = br.readLine();
//            if (headerLine != null) {
//                String[] headers = headerLine.split(",");
//                JSONObject json = new JSONObject();
//                for (int i = 0; i < headers.length; i++) {
//                    json.put("column" + (i + 1), headers[i]);
//                }
            if (headerLine != null) {
                // Convertendo o cabeçalho em uma lista e ordenando
                String[] headersArray = headerLine.split(",");
                Arrays.sort(headersArray);
                List<String> sortedHeaders = Arrays.asList(headersArray);
                Collections.sort(sortedHeaders);

                // Criando o objeto JSON
                JSONObject json = new JSONObject();
                for (int i = 0; i < sortedHeaders.size(); i++) {
                    json.put("column" + (i + 1), sortedHeaders.get(i));
                }
                return json;
            }
            throw new IOException("O arquivo CSV está vazio ou não possui um cabeçalho.");
        }
    }

    public static void main(String[] args) {
        try {
            JSONObject headerJson = getCSVHeaderAsJson("C:/learning/contacts.csv");
            System.out.println("Cabeçalho do CSV em JSON: " + headerJson.toString());
            // Aqui você pode adicionar o código para enviar o JSON para o Postman
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}