package inspect;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.JsonSchemaGenerator;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     *
     * @param args The arguments of the program.
     */
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonSchemaGenerator schemaGen = new JsonSchemaGenerator(mapper);

        Reflections reflections = new Reflections("inspect.pojos",
                "it.arubapec.esecurity.docflysharedapi.domain.archive", new SubTypesScanner(false));
        Set<Class<?>> pojos = reflections.getSubTypesOf(Object.class);
        Map<String, String> schemaByClassNameMap = pojos.stream()
                .collect(Collectors.toMap(Class::getSimpleName, pojo -> getSchema(mapper, schemaGen, pojo)));
        schemaByClassNameMap.entrySet().forEach(schemaByClassNameEntry -> writeToFile(schemaByClassNameEntry.getKey(),
                schemaByClassNameEntry.getValue()));

    }

    private static void writeToFile(String pojoClassName, String pojoJsonSchema) {
        try {
            Path path = Paths.get("generated-schemas/" + pojoClassName + ".json");
            Files.deleteIfExists(path);
            byte[] strToBytes = pojoJsonSchema.getBytes();
            Files.write(path, strToBytes);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    private static String getSchema(ObjectMapper mapper, JsonSchemaGenerator schemaGenerator, Class clazz) {
        try {
            JsonSchema schema = schemaGenerator.generateSchema(clazz);
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(schema);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
