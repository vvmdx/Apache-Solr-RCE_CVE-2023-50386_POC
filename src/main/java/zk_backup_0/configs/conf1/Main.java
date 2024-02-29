/*
使用Java Security Manager + JDK 17的环境，令其通过URLClassLoader加载，还原Solr环境（方便windows下idea测试）
*/
package zk_backup_0.configs.conf1;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.ProtectionDomain;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {

       URL[] urls = new URL[]{new URL("file:D:\\xxx\\target\\classes\\zk_backup_0\\configs\\conf1\\ExpTest.class")};
       URLClassLoader classLoader = new URLClassLoader(urls);
       Class<?> loadedClass = classLoader.loadClass("zk_backup_0.configs.conf1.ExpTest");
       Object object = loadedClass.newInstance();
       System.out.println("123");

    }
}
