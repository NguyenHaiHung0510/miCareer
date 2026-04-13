package vn.com.micareer.util;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

public class UploadCVUtil {

    private static final long MAX_SIZE = 2 * 1024 * 1024; // 2MB
    private static Cloudinary cloudinary;

    // 🔥 init Cloudinary (static)
    static {
        try {
            Properties props = new Properties();

            InputStream input = UploadCVUtil.class
                    .getClassLoader()
                    .getResourceAsStream("cloudinary.properties");

            if (input == null) {
                throw new RuntimeException("Không tìm thấy cloudinary.properties");
            }

            props.load(input);

            cloudinary = new Cloudinary(ObjectUtils.asMap(
                    "cloud_name", props.getProperty("cloud_name"),
                    "api_key", props.getProperty("api_key"),
                    "api_secret", props.getProperty("api_secret")
            ));

        } catch (Exception e) {
            throw new RuntimeException("Init Cloudinary failed", e);
        }
    }

    // 🎯 DTO kết quả (gọn gàng hơn Map)
    public static class UploadResult {
        private String url;
        private String publicId;
        private long uploadTime;

        public UploadResult(String url, String publicId, long uploadTime) {
            this.url = url;
            this.publicId = publicId;
            this.uploadTime = uploadTime;
        }

        public String getUrl() {
            return url;
        }

        public String getPublicId() {
            return publicId;
        }

        public long getUploadTime() {
            return uploadTime;
        }
    }

    // 🔥 MAIN METHOD
    public static UploadResult uploadCV(Part filePart, String userId) throws Exception {

        // ❌ check null
        if (filePart == null || filePart.getSize() == 0) {
            throw new Exception("Vui lòng chọn file");
        }

        // ❌ check size
        if (filePart.getSize() > MAX_SIZE) {
            throw new Exception("File quá lớn (tối đa 2MB)");
        }

        // ❌ check type
        if (!"application/pdf".equals(filePart.getContentType())) {
            throw new Exception("Chỉ chấp nhận file PDF");
        }

        // 🔥 tạo file temp
        File tempFile = File.createTempFile("upload_", ".pdf");
        filePart.write(tempFile.getAbsolutePath());

        long start = System.currentTimeMillis();

        // 🔥 upload
        Map uploadResult = cloudinary.uploader().upload(tempFile, ObjectUtils.asMap(
                "folder", "cv",
                "resource_type", "raw", // ✅ đúng cho PDF
                "public_id", userId + "_" + UUID.randomUUID()
        ));

        long end = System.currentTimeMillis();

        // ❌ xoá file temp
        tempFile.delete();

        String url = (String) uploadResult.get("secure_url");
        String publicId = (String) uploadResult.get("public_id");

        return new UploadResult(url, publicId, (end - start));
    }

    // 🗑️ delete CV
    public static boolean deleteCV(String publicId) throws Exception {
        if (publicId == null || publicId.isEmpty()) {
            return false;
        }

        Map result = cloudinary.uploader().destroy(publicId, ObjectUtils.asMap(
                "resource_type", "raw"
        ));

        String status = (String) result.get("result");
        System.out.println(status);
        return "ok".equals(status);
    }
}