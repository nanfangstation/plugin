package com.lydia.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;

/**
 * @author Lydia
 * @since 2020-03-01 下午3:12
 **/
public class PluginFileUtil {

    private PluginFileUtil() {
    }

    public static String getMd5ByFile(File file) throws FileNotFoundException {
        String value = null;
        FileInputStream in = new FileInputStream(file);
        try {
            MappedByteBuffer byteBuffer = in.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(byteBuffer);
            BigInteger bi = new BigInteger(1, md5.digest());
            value = bi.toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return value;
    }

    public static void cleanEmptyFile(Path path) {
        if (path == null) {
            return;
        }
        if (!Files.exists(path)) {
            return;
        }
        try {
            Files.list(path)
                    .forEach(subPath -> {
                        File file = subPath.toFile();
                        if (!file.isFile()) {
                            return;
                        }
                        long length = file.length();
                        if (length == 0) {
                            try {
                                Files.deleteIfExists(subPath);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 如果文件不存在, 则会创建
     *
     * @param path 插件路径
     * @return 插件路径
     * @throws IOException 没有发现文件异常
     */
    public static Path createExistFile(Path path) throws IOException {
        Path parent = path.getParent();
        if (!Files.exists(parent)) {
            Files.createDirectories(parent);
        }
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
        return path;
    }
}
