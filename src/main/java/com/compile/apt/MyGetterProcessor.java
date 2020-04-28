package com.compile.apt;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.Writer;
import java.util.Set;

/**
 * 指定要处理的版本 java8 要处理的注解 com.compile.apt.GeneratePrint
 * @author zhanglifeng
 * @since 2020-04-28 11:02
 */
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes("com.compile.apt.GeneratePrint")
public class MyGetterProcessor extends AbstractProcessor {

    /**
     * 1、先用javac 编译GeneratePrint.java 和MyGetterProcessor.java
     * 2、到src/main/java 目录下执行：
     * javac -processor com.compile.apt.MyGetterProcessor com/compile/apt/TestAnn.java
     * 原因：指定了package，会报错：
     * java.lang.NoClassDefFoundError: MyGetterProcessor (wrong name: com/compile/apt/MyGetterProcessor)
     */
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        StringBuilder builder = new StringBuilder()
                .append("package com.compile.apt;\n\n")
                .append("public class GeneratedClass {\n\n") // open class
                .append("\tpublic String getMessage() {\n") // open method
                .append("\t\treturn \"");
        // for each javax.lang.model.element.Element annotated with the CustomAnnotation
        for (Element element : roundEnv.getElementsAnnotatedWith(GeneratePrint.class)) {
            String objectType = element.getSimpleName().toString();
            // this is appending to the return statement
            builder.append(objectType).append(" says hello!\\n");
        }
        builder.append("\";\n") // end return
                .append("\t}\n") // close method
                .append("}\n"); // close class
        try { // write the file
            JavaFileObject source = processingEnv.getFiler().createSourceFile("com.compile.apt.GeneratedClass");
            Writer writer = source.openWriter();
            writer.write(builder.toString());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            // Note: calling e.printStackTrace() will print IO errors
            // that occur from the file already existing after its first run, this is normal
        }
        return true;
    }
}
