package eon.p2p.base.util;

import jdk.internal.util.xml.impl.Input;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.*;
import java.util.UUID;

/**
 * 文件工具类
 */
@Component
public class FileUtil {

    private static ServletContext ctx;

    @Autowired
    public void setCtx(ServletContext ctx) {
        this.ctx = ctx;
    }

    public static String getPath(String basePath, MultipartFile file) {
        String path = UUID.randomUUID().toString() + file.getOriginalFilename();
        path = ctx.getRealPath(basePath + path);
        return path;
    }

    public static String upload(MultipartFile file) {
        File path = new File(getPath("/images/realAuth/", file));
        try (InputStream in = file.getInputStream(); OutputStream out = new FileOutputStream(path)) {
            StreamUtils.copy(in, out);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return path.toString();
    }
}
