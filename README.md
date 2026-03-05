# Hướng dẫn mới về việc áp dụng quy trình bảo mật tự động local
    - Ae lưu ý ngay khi clone dự án về chạy tool: setup-env.bat hoặc setup-env-wlove.bat
    - Đây là tool cài môi trường cho 3 tool:
        - GitLeaks: Rà quét lỗ hổng tự động
        - PMD:      Rà quét lỗi lập trình dựa trên file .java
        - SpotBugs + Plugin FindSecBugs: Rà quét lỗ hổng bảo mật trên file bytecode

    - Bộ tool này là pha 1 trong quy trình phát triển phần mềm an toàn bảo mật
        tích hợp công cụ SCA/DAST/SAST do Hưng và các ae nhóm btl môn ATBM HTTT phát triển


# Hướng dẫn làm việc với git repo này cho ae
# Repo này có 2 nhánh chính
  - main: nhánh cao nhất, chứa code sản phẩm hoàn thiện theo từng bước
  - develop: nhánh phát triển, chứa code mới nhất trong giai đoạn phát triển của ae

# Nguyên tắc là ae không bao giờ push trực tiếp lên develop và main
# Phải thực hiện theo các bước bên dưới để tạo nhánh mới và xong thì tạo pull request vào develop, Khoa và Hưng sẽ (tạm) đảm nhận review và duyệt

 + Trước khi ae code, hãy đảm bảo lấy code mới nhất từ develop
     - git clone https://github.com/NguyenHaiHung0510/miCareer.git đối với lần đầu
     - git checkout develop: chuyển sang nhánh develop ở local ( dùng "git checkout -b develop origin/develop" nếu không có )
     - git pull origin develop: kéo code mới nhất về

  + Tiếp theo là tạo nhánh mới local, ae sẽ code mới trong nhánh này
      - git checkout -b testFlow ( ae thay testFlow bằng tên branch của ae, tốt nhất nên cho có ý nghĩa ngắn gọn như newCodeModel_ten_ae, newCodeDA_tem_ae hoặc đơn giản là dev_<tên của ae> là đc )

  + Khi xong việc, ae có thể sử dụng
      - git status: check trạng thái các thay đổi
      - git add: add từng file hoặc add tất = "git add ."
      - git commit -m "viết hàm abc, thêm tính năng xyz"
      - git push origin testFlow ( nhớ thay tên testFlow bằng tên branch ae đặt )
   

