import com.google.googlejavaformat.java.Formatter;
import com.google.googlejavaformat.java.FormatterException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.*;

public class Reflector {

    public static void printStructure(Class<?> someClass) {
        String fileName = someClass.getSimpleName();
        File f = new File(fileName + ".java");
        try (FileWriter outputFile = new FileWriter(f)) {
            StringBuilder ans = new StringBuilder();
            writeClass(ans, someClass);
            String st = new Formatter().formatSource(ans.toString());
            outputFile.write(st);
        } catch (IOException | FormatterException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void writeModifier(StringBuilder ans, Class<?> someClass) throws IOException {
        int mod = someClass.getModifiers();
        ans.append(Modifier.toString(mod)).append(' ');
        if (someClass.isAnonymousClass()) {
            ans.append("abstract ");
        }
        if (someClass.isInterface()) {
            ans.append("interface ");
        } else
            ans.append("class ");
        ans.append(someClass.getTypeName());
        TypeVariable<? extends Class<?>>[] types = someClass.getTypeParameters();
        if (types.length != 0) {
            ans.append('<');
            for (int i = 0; i < types.length - 1; i++) {
                ans.append(types[i].getName()).append(" extends ");
                ans.append(types[i].getBounds()[0].getTypeName());
                ans.append(',');
            }
            ans.append(types[types.length - 1].getName()).append(" extends ");
            ans.append(types[types.length - 1].getBounds()[0].getTypeName());
            ans.append('>');
        }
        Type t = someClass.getGenericSuperclass();
        if (t != null) {
            ans.append(" extends ");
            ans.append(t.getTypeName());
        }
        Type[] interfaces = someClass.getGenericInterfaces();
        if (interfaces.length != 0) {
            ans.append(" implements ");
            for (int i = 0; i < interfaces.length - 1; i++) {
                ans.append(interfaces[i].getTypeName()).append(", ");
            }
            ans.append(interfaces[interfaces.length - 1].getTypeName());
        }
        ans.append(" {\n");
    }

    private static void writeField(StringBuilder ans, Field field) throws IOException {
        int mod = field.getModifiers();
        ans.append(Modifier.toString(mod)).append(' ');
        ans.append(field.getGenericType().getTypeName()).append(' ');
        ans.append(field.getName());
        Class<?> fType = field.getType();
        if (fType.isPrimitive()) {
            if (fType == boolean.class)
                ans.append(" = false");
            else if (fType != void.class)
                ans.append(" = 0");
        } else
            ans.append(" = null");
        ans.append(";\n");
    }

    private static void writeFields(StringBuilder ans, Class<?> someClass) throws IOException {
        Field[] fields = someClass.getDeclaredFields();
        for (Field f : fields)
            writeField(ans, f);
    }

    private static void writeArgs(StringBuilder ans, Type[] types) throws IOException {
        for (int i = 0; i < types.length; i++) {
            ans.append(types[i].getTypeName()).append(' ');
            ans.append("arg").append(i);
            if (i != types.length - 1)
                ans.append(" ,");
        }
    }

    private static void writeConstructor(StringBuilder ans, Constructor constr) throws IOException {
        int mod = constr.getModifiers();
        ans.append(Modifier.toString(mod)).append(' ');
        ans.append(constr.getName()).append('(');
        writeArgs(ans, constr.getGenericParameterTypes());
        ans.append(") {}\n");
    }

    private static void writeConstructors(StringBuilder ans, Class<?> someClass) throws IOException {
        Constructor<?>[] constrs = someClass.getDeclaredConstructors();
        for (Constructor<?> c : constrs)
            writeConstructor(ans, c);
    }

    private static void writeMethod(StringBuilder ans, Method method) throws IOException {
        int mod = method.getModifiers();
        ans.append(Modifier.toString(mod)).append(' ');
        Type[] types = method.getGenericParameterTypes();
        if (types.length > 0) {
            ans.append('<');
            for (int i = 0; i < types.length - 1; i++)
                ans.append(types[i].getTypeName()).append(',');
            ans.append(types[types.length - 1].getTypeName()).append('>');
        }
        ans.append(method.getGenericReturnType().getTypeName()).append(' ');
        ans.append(method.getName()).append('(');
        writeArgs(ans, method.getGenericParameterTypes());
        ans.append(") {\n");
        ans.append("return ");
        Class<?> retType = method.getReturnType();
        if (retType.isPrimitive()) {
            if (retType == boolean.class)
                ans.append("false");
            else if (retType != void.class)
                ans.append("0");
        } else
            ans.append("null");
        ans.append(";\n");
        ans.append("}\n");
    }

    private static void writeMethods(StringBuilder ans, Class<?> someClass) throws IOException {
        Method[] methods = someClass.getDeclaredMethods();
        for (Method m : methods)
            writeMethod(ans, m);
    }

    private static void writeClass(StringBuilder ans, Class<?> someClass) throws IOException {
        writeModifier(ans, someClass);
        writeConstructors(ans, someClass);
        writeFields(ans, someClass);
        writeMethods(ans, someClass);
        Class<?>[] classes = someClass.getDeclaredClasses();
        for (Class<?> clazz : classes)
            writeClass(ans, clazz);
        ans.append("\n}\n");

    }
}
