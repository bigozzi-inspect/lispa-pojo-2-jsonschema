package inspect;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.JsonSchemaGenerator;

import org.apache.commons.io.FileUtils;
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
        FileUtils.cleanDirectory(new File("generated-schemas"));

        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(ZonedDateTime.class, new ZonedDateTimeSerializer());

        ObjectMapper mapper = new ObjectMapper();
        // mapper.setDefaultPropertyInclusion(Include.NON_NULL);
        // mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        // mapper.setLocale(Locale.ITALIAN);
        mapper.registerModule(javaTimeModule);

        JsonSchemaGenerator schemaGen = new JsonSchemaGenerator(mapper);

        Reflections reflections = new Reflections(/* "inspect.entity", */ "it.arubapec.esecurity.docflysharedapi.domain",
                "it.arubapec.esecurity.docflydomain.dto.generic", "it.arubapec.esecurity.docflydomain.dto.archive",
                new SubTypesScanner(false));
        Set<Class<?>> pojos = reflections.getSubTypesOf(Object.class);
        Map<String, String> schemaByClassNameMap = pojos.stream()
                .collect(Collectors.toMap(pojo -> simpleNameOrName(pojo, pojos), pojo -> getSchema(mapper, schemaGen, pojo)));
        schemaByClassNameMap.entrySet().forEach(schemaByClassNameEntry -> writeToFile(schemaByClassNameEntry.getKey(),
                schemaByClassNameEntry.getValue()));

        Reflections enumReflections = new Reflections("it.arubapec.esecurity.docflyenum", new SubTypesScanner(false));
        Set<Class<?>> enumPojos = enumReflections.getSubTypesOf(Object.class);
        Map<String, String> enumSchemaByClassNameMap = enumPojos.stream()
                .collect(Collectors.toMap(Class::getName, pojo -> getSchema(mapper, schemaGen, pojo)));
        enumSchemaByClassNameMap.entrySet().forEach(schemaByClassNameEntry -> writeToFile(schemaByClassNameEntry.getKey(),
                schemaByClassNameEntry.getValue()));

    }

    private static String simpleNameOrName(Class<?> clazz,  Set<Class<?>> pojos) {
        return pojos.stream().anyMatch(pojo ->
            !pojo.equals(clazz) && pojo.getSimpleName().equals(clazz.getSimpleName())
        ) ? clazz.getName() : clazz.getSimpleName();
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
