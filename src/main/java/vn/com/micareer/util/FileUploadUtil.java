/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.com.micareer.util;

import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

public class FileUploadUtil {

    private static final String UPLOAD_DIR = "uploads";

    // Upload file và trả về đường dẫn tương đối
    public static String saveFile(Part filePart, String uploadRootPath) throws IOException {

        if (filePart == null || filePart.getSize() == 0) {
            return null;
        }

        // Tạo thư mục uploads nếu chưa tồn tại
        File uploadDir = new File(uploadRootPath + File.separator + UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // Lấy tên file gốc
        String originalFileName = Path.of(filePart.getSubmittedFileName())
                .getFileName().toString();

        // Tạo tên file unique (tránh trùng)
        String fileExtension = "";
        int dotIndex = originalFileName.lastIndexOf(".");
        if (dotIndex > 0) {
            fileExtension = originalFileName.substring(dotIndex);
        }

        String newFileName = UUID.randomUUID() + fileExtension;

        // Đường dẫn lưu file
        Path filePath = Path.of(uploadDir.getAbsolutePath(), newFileName);

        // Lưu file
        Files.copy(filePart.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        // Trả về đường dẫn để lưu DB
        return UPLOAD_DIR + "/" + newFileName;
    }

    // Kiểm tra loại file hợp lệ (ví dụ chỉ cho phép PDF/DOCX)
    public static boolean isValidCvFile(Part filePart) {
        if (filePart == null) return false;

        String contentType = filePart.getContentType();
        return contentType.equals("application/pdf")
                || contentType.equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document")
                || contentType.equals("application/msword");
    }
}