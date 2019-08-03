package com.leyou.upload.service.impl;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.leyou.upload.service.UploadService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service("upload")
public class UploadServiceImpl implements UploadService {

    @Autowired
    private FastFileStorageClient storageClient;

    private static final List<String> CONTENT_TYPES = Arrays.asList("image/jpeg", "image/gif");

    public String upload(MultipartFile file) {

        String originalFilename = file.getOriginalFilename();
        // 校验文件的类型
        String contentType = file.getContentType();
        if (!CONTENT_TYPES.contains(contentType)){
            // 文件类型不合法，直接返回null
            log.info("文件类型不合法：{}", originalFilename);
            return null;
        }

        try {
            // 校验文件的内容
            BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
            if (bufferedImage == null){
                log.info("文件内容不合法：{}", originalFilename);
                return null;
            }

            // 保存到服务器
            // file.transferTo(new File("C:\\leyou\\images\\" + originalFilename));
            String ext = StringUtils.substringAfterLast(originalFilename, ".");
            StorePath storePath = this.storageClient.uploadFile(file.getInputStream(), file.getSize(), ext, null);

            // 生成url地址，返回
            return "http://image.leyou.com/" + storePath.getFullPath();
        } catch (IOException e) {
            log.info("服务器内部错误：{}", originalFilename);
            e.printStackTrace();
        }
        return null;
    }

    /*private static final List<String> CONTENT_TYPES = Arrays.asList("image/jpeg","image/gif");

    @Override
    public String upload(MultipartFile file) {

        String originalFilename = file.getOriginalFilename();
        String contentType = file.getContentType();
        // 校验文件的类型
        if (!CONTENT_TYPES.contains(contentType)){
            // 文件类型不合法，直接返回null
            log.error("文件类型不合法：{}"+originalFilename);
            return null;
        }

        try {
            // 校验文件的内容
            BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
            if (bufferedImage == null){
                log.error("文件内容不合法：{}"+originalFilename);
                return null;
            }

            // 保存到服务器
            file.transferTo(new File("D:\\IDEA\\images\\"+originalFilename));
            // 生成url地址，返回
            return "http://image.leyou.com/"+originalFilename;
        } catch (IOException e) {
            log.error("服务器内部错误：{}",e);
        }
        return null;
    }*/
}
