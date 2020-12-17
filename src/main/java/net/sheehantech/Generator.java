package net.sheehantech;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import flexjson.JSONDeserializer;

import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class Generator {

    public static void main(String[] args) throws IOException {
        JSONDeserializer deserializer = new JSONDeserializer();
        Object contents = deserializer.deserialize(Files.newBufferedReader(Paths.get("bible-contents.json")));
        System.out.println(contents);
        System.out.println(contents.getClass());
        Map<String,Object> contentsMap = (Map<String, Object>) contents;
        String key = (String)contentsMap.keySet().toArray()[0];
        System.out.println(contentsMap.get(key).getClass());
        List<Object> books = (List<Object>) contentsMap.get(key);
        TypeSpec.Builder bibleBuilder = TypeSpec.enumBuilder("Book")
                .addModifiers(Modifier.PUBLIC)
                .addField(String.class, "title", Modifier.PRIVATE)
                .addField(Integer.class, "numChapters", Modifier.PRIVATE)
                .addMethod(MethodSpec.constructorBuilder()
                        .addModifiers(Modifier.PRIVATE)
                        .addParameter(String.class, "title")
                        .addParameter(Integer.class, "numChapters")
                        .addStatement("this.$N = $N", "title", "title")
                        .addStatement("this.$N = $N", "numChapters", "numChapters")
                        .build());
        for (Object book : books) {
            Map<String,Object> bookMap = (Map<String, Object>) book;
            System.out.println(bookMap);
            String title = (String)bookMap.get("passage");
            List chapters = (List<Object>)bookMap.get("chapters");
            bibleBuilder.addEnumConstant(
                    title.replace("1", "ONE")
                            .replace("2", "TWO")
                            .replace("3", "THREE")
                            .replaceAll(" ", "")
                            .toUpperCase(),
                    TypeSpec.anonymousClassBuilder("$S, $L", title, chapters.size()).build());
//                    , TypeSpec.anonymousClassBuilder("$S", "fist")
//                            .addMethod(MethodSpec.methodBuilder("toString")
//                                    .addAnnotation(Override.class)
//                                    .addModifiers(Modifier.PUBLIC)
//                                    .addStatement("return $S", "avalanche!")
//                                    .returns(String.class)
//                                    .build())
//                            .build())
//                    .addEnumConstant("SCISSORS", TypeSpec.anonymousClassBuilder("$S", "peace")
//                            .build())
//                    .addEnumConstant("PAPER", TypeSpec.anonymousClassBuilder("$S", "flat")
//                            .build())
//                    .addField(String.class, "handsign", Modifier.PRIVATE, Modifier.FINAL)
//                    .addMethod(MethodSpec.constructorBuilder()
//                            .addParameter(String.class, "handsign")
//                            .addStatement("this.$N = $N", "handsign", "handsign")
//                            .build())
//                    .build();

        }
        TypeSpec bible = bibleBuilder.build();
        JavaFile javaFile = JavaFile.builder("bible", bible)
                .build();

        javaFile.writeTo(Path.of("target"));

    }
}
