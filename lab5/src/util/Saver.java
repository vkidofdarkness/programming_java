package util;

import classes.*;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.PriorityQueue;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * класс, используемый для сериализации и десериализации в json
 */
public class Saver {

    /**
     * сериализация в json
     * @param organization
     * @param filename
     */
    public void serializeOrganization(Organization organization, String filename) {
        try {
            FileOutputStream fos = new FileOutputStream(filename, true);
            XStream xStream = new XStream(new JettisonMappedXmlDriver());
            xStream.alias("Organization", Organization.class);
            xStream.alias("Coordinates", Coordinates.class);
            xStream.alias("Address", Address.class);
            fos.write(xStream.toXML(organization).getBytes(UTF_8));
            fos.write("\n".getBytes(UTF_8));
            fos.flush();
            fos.close();
            System.out.println("Организация с id: " + organization.getId() + " сохранена");
        } catch (IOException e) {
            System.out.println("Сериализация не выполнена:" + organization.toString());
        }
    }

    /**
     * десериализация json
     * @param filename
     * @param collection
     */
    public void deserializeOrganization(String filename, PriorityQueue<Organization> collection) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), StandardCharsets.UTF_8));
            ArrayList<String> dataFromFile = new ArrayList<>();
            String line = br.readLine();
            int counter = 0;
            while (line != null) {
                dataFromFile.add(line + "\n");
                counter += 1;
                line = br.readLine();
            }
            br.close();
            XStream xStream = new XStream(new JettisonMappedXmlDriver());
            xStream.alias("Organization", Organization.class);
            xStream.alias("Coordinates", Coordinates.class);
            xStream.alias("Address", Address.class);
            Class<?>[] classes = new Class[] { Organization.class, Coordinates.class, Address.class};
            XStream.setupDefaultSecurity(xStream);
            xStream.allowTypes(classes);
            for (int i = 0; i < counter; ++i) {
                Organization organization = (Organization)xStream.fromXML(dataFromFile.get(i));
                try {
                    if (organization.checkParameters()) {
                        collection.add(organization);
                        System.out.println("Добавлен: " + organization.toString());
                    }
                    else {
                        System.out.println("Ошибка: " + organization.toString());
                    }
                } catch (NullPointerException e){
                    System.out.println("Ошибка: " + organization.toString());}
            }
            System.out.println("Файл успешно прочитан.");
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден.");
            System.exit(1);
        } catch (IOException e) {
            System.out.println("IOException");
        }
    }
}
