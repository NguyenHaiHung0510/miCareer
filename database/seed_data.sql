USE miCareer_DB;

-- 1. COMPANY
INSERT INTO Company (compName, taxCode, webUrl, logoUrl, contactEmail, provId, ward, street) VALUES
('Tập đoàn VinaSol',          '0101248141', 'https://vinasol.com.vn',  '/logos/vinasol.png',  'hr@vinasol.com.vn',        'HN', 'Phường Dịch Vọng Hậu', 'Tòa nhà VinaSol, 10 Phạm Văn Bạch'),
('Tập đoàn Công nghệ MicroShop','0302553763', 'https://microshop.vn',    '/logos/microshop.png','careers@microshop.vn',     'HN', 'Phường Mai Dịch',      'Tầng 15, Keangnam Landmark 72'),
('Công ty Công nghệ Hoàng Anh', '0100109106', 'https://hoanganhtech.vn', '/logos/hoanganh.png', 'tuyendung@hoanganhtech.vn','HN', 'Phường Yên Hòa',       'Số 1 Trần Hữu Dực'),
('Công ty Cổ phần NextGen',   '0309532909', 'https://nextgen.com.vn',  '/logos/nextgen.png',  'hello@nextgen.com.vn',     'HN', 'Phường Quang Trung',   '18 Tây Sơn'),
('Công ty Fintech PayMe',     '0313525427', 'https://payme.vn',        '/logos/payme.png',    'talent@payme.vn',          'HN', 'Phường Liễu Giai',     '28 Liễu Giai');

-- 2. USER (26 Users: 18 Candidate + 7 HR + 1 Admin Moderator)
INSERT INTO `User` (userName, pwd, fName, lName, email, phone, provId, ward, street, stat, `role`) VALUES
('nguyenvanan',    '123456', 'An',      'Nguyễn Văn',  'nguyenvanan@gmail.com',    '0901000001', 'HN', 'Phường Bách Khoa',        '1 Đại Cồ Việt',          'ACTIVE', 'CANDIDATE'),
('tranthibinh',    '123456', 'Bình',    'Trần Thị',    'tranthibinh@gmail.com',    '0901000002', 'HN', 'Phường Nhân Chính',       '15 Lê Văn Lương',        'ACTIVE', 'CANDIDATE'),
('levancuong',     '123456', 'Cường',   'Lê Văn',      'levancuong@gmail.com',     '0901000003', 'HN', 'Phường Quan Hoa',         '22 Nguyễn Khánh Toàn',   'ACTIVE', 'CANDIDATE'),
('phamthidung',    '123456', 'Dung',    'Phạm Thị',    'phamthidung@gmail.com',    '0901000004', 'HN', 'Phường Trung Hòa',        '30 Trung Kính',          'ACTIVE', 'CANDIDATE'),
('hoangvanduc',    '123456', 'Đức',     'Hoàng Văn',   'hoangvanduc@gmail.com',    '0901000005', 'HN', 'Phường Thanh Xuân Trung', '50 Nguyễn Trãi',         'ACTIVE', 'CANDIDATE'),
('vuthiphuong',    '123456', 'Phương',  'Vũ Thị',      'vuthiphuong@gmail.com',    '0901000006', 'HN', 'Phường Thịnh Quang',      '3 Tây Sơn',              'ACTIVE', 'CANDIDATE'),
('dangvangiang',   '123456', 'Giang',   'Đặng Văn',    'dangvangiang@gmail.com',   '0901000007', 'HN', 'Phường Dịch Vọng',        '100 Xuân Thủy',          'ACTIVE', 'CANDIDATE'),
('buithihuong',    '123456', 'Hương',   'Bùi Thị',     'buithihuong@gmail.com',    '0901000008', 'HN', 'Phường Thanh Xuân Bắc',   '12 Lê Trọng Tấn',        'ACTIVE', 'CANDIDATE'),
('lyvankhoi',      '123456', 'Khôi',    'Lý Văn',      'lyvankhoi@gmail.com',      '0901000009', 'HN', 'Phường Nam Đồng',         '8 Hồ Đắc Di',            'ACTIVE', 'CANDIDATE'),
('maithilinh',     '123456', 'Linh',    'Mai Thị',     'maithilinh@gmail.com',     '0901000010', 'HN', 'Phường Khương Thượng',    '45 Tôn Thất Tùng',       'ACTIVE', 'CANDIDATE'),
('truongvanminh',  '123456', 'Minh',    'Trương Văn',  'truongvanminh@gmail.com',  '0901000011', 'HN', 'Phường Láng Hạ',          '20 Láng Hạ',             'ACTIVE', 'CANDIDATE'),
('ngothingoc',     '123456', 'Ngọc',    'Ngô Thị',     'ngothingoc@gmail.com',     '0901000012', 'HN', 'Phường Trung Liệt',       '7 Thái Hà',              'ACTIVE', 'CANDIDATE'),
('dinhvanphong',   '123456', 'Phong',   'Đinh Văn',    'dinhvanphong@gmail.com',   '0901000013', 'HN', 'Phường Kim Liên',         '55 Phạm Ngọc Thạch',     'ACTIVE', 'CANDIDATE'),
('phamvanquan',    '123456', 'Quân',    'Phạm Văn',    'phamvanquan@gmail.com',    '0901000014', 'HN', 'Phường Phương Liên',      '18 Đào Duy Anh',         'ACTIVE', 'CANDIDATE'),
('hoangthiquynh',  '123456', 'Quỳnh',   'Hoàng Thị',   'hoangthiquynh@gmail.com',  '0901000015', 'HN', 'Phường Ô Chợ Dừa',        '10 Hoàng Cầu',           'ACTIVE', 'CANDIDATE'),
('lethithanh',     '123456', 'Thanh',   'Lê Thị',      'lethithanh@gmail.com',     '0901000016', 'HN', 'Phường Hà Cầu',           '5 Quang Trung',          'ACTIVE', 'CANDIDATE'),
('vuvanthang',     '123456', 'Thắng',   'Vũ Văn',      'vuvanthang@gmail.com',     '0901000017', 'HN', 'Phường Khương Mai',       '22 Vĩnh Hồ',             'ACTIVE', 'CANDIDATE'),
('dangthiuyen',    '123456', 'Uyên',    'Đặng Thị',    'dangthiuyen@gmail.com',    '0901000018', 'HN', 'Phường Ngã Tư Sở',        '33 Trường Chinh',        'ACTIVE', 'CANDIDATE');

INSERT INTO `User` (userName, pwd, fName, lName, email, phone, provId, ward, street, stat, `role`) VALUES
('mgr_vinasol',    '123456', 'Lan',     'Ngô Thị',     'lan.ngo@vinasol.com.vn',   '0902000001', 'HN', 'Phường Dịch Vọng Hậu',    'Tòa nhà VinaSol',        'ACTIVE', 'HR'),
('staff_vinasol',  '123456', 'Tú',      'Trần Anh',    'tu.tran@vinasol.com.vn',   '0902000002', 'HN', 'Phường Dịch Vọng Hậu',    'Tòa nhà VinaSol',        'ACTIVE', 'HR'),
('mgr_microshop',  '123456', 'Hùng',    'Đỗ Văn',      'hung.do@microshop.vn',     '0902000003', 'HN', 'Phường Mai Dịch',         'Keangnam Landmark 72',   'ACTIVE', 'HR'),
('mgr_hoanganh',   '123456', 'Mai',     'Bùi Thị',     'mai.bui@hoanganhtech.vn',  '0902000004', 'HN', 'Phường Yên Hòa',          'Số 1 Trần Hữu Dực',      'ACTIVE', 'HR'),
('staff_hoanganh', '123456', 'Quân',    'Lý Đình',     'quan.ly@hoanganhtech.vn',  '0902000005', 'HN', 'Phường Yên Hòa',          'Số 1 Trần Hữu Dực',      'ACTIVE', 'HR'),
('mgr_nextgen',    '123456', 'Tuấn',    'Vũ Minh',     'tuan.vu@nextgen.com.vn',   '0902000006', 'HN', 'Phường Quang Trung',      '18 Tây Sơn',             'ACTIVE', 'HR'),
('mgr_payme',      '123456', 'Hà',      'Lý Thu',      'ha.ly@payme.vn',           '0902000007', 'HN', 'Phường Liễu Giai',        '28 Liễu Giai',           'ACTIVE', 'HR'),
('admin_mod',      '123456', 'Trung',   'Trần Đức',    'mod@micareer.vn',          '0903000002', 'HN', 'Phường Thanh Xuân Trung', '18 Phạm Hùng',           'ACTIVE', 'ADMIN');

-- 3. CANDIDATE PROFILE
INSERT INTO Candidate (candidateId, bio, cvUrl, dob, expYears)
SELECT userId, 'Backend Developer với 3 năm kinh nghiệm phát triển ứng dụng web bằng Java/Spring Boot. Tốt nghiệp Cử nhân CNTT tại PTIT.', '/cv/nguyenvanan_cv.pdf', '2000-03-15', 3.0 FROM `User` WHERE userName = 'nguyenvanan' UNION ALL
SELECT userId, 'Frontend Developer 1 năm kinh nghiệm ReactJS, TypeScript.', '/cv/tranthibinh_cv.pdf', '2001-07-22', 1.0 FROM `User` WHERE userName = 'tranthibinh' UNION ALL
SELECT userId, 'Fullstack Developer 3 năm, thành thạo NodeJS + VueJS.', '/cv/levancuong_cv.pdf', '1999-11-05', 3.0 FROM `User` WHERE userName = 'levancuong' UNION ALL
SELECT userId, 'Data Engineer 2 năm, sử dụng Python + PostgreSQL + Docker + AWS.', '/cv/phamthidung_cv.pdf', '2000-09-18', 2.0 FROM `User` WHERE userName = 'phamthidung' UNION ALL
SELECT userId, 'Junior Developer 1 năm kinh nghiệm Java/Python.', '/cv/hoangvanduc_cv.pdf', '2002-01-10', 1.0 FROM `User` WHERE userName = 'hoangvanduc' UNION ALL
SELECT userId, 'Sinh viên năm cuối PTIT, đang tìm vị trí thực tập Java.', '/cv/vuthiphuong_cv.pdf', '2003-05-20', 0.0 FROM `User` WHERE userName = 'vuthiphuong' UNION ALL
SELECT userId, 'Frontend Developer 2 năm, thành thạo ReactJS + VueJS.', '/cv/dangvangiang_cv.pdf', '2001-02-14', 2.0 FROM `User` WHERE userName = 'dangvangiang' UNION ALL
SELECT userId, 'Sinh viên năm cuối, đam mê Python và Data Science.', '/cv/buithihuong_cv.pdf', '2003-08-30', 0.0 FROM `User` WHERE userName = 'buithihuong' UNION ALL
SELECT userId, 'Mobile Developer 1 năm kinh nghiệm Kotlin/Android.', '/cv/lyvankhoi_cv.pdf', '2002-04-12', 1.0 FROM `User` WHERE userName = 'lyvankhoi' UNION ALL
SELECT userId, 'Backend Developer 2 năm, chuyên Python + FastAPI.', '/cv/maithilinh_cv.pdf', '2001-06-25', 2.0 FROM `User` WHERE userName = 'maithilinh' UNION ALL
SELECT userId, 'Fullstack Developer 3 năm NodeJS + ReactJS.', '/cv/truongvanminh_cv.pdf', '1999-09-08', 3.0 FROM `User` WHERE userName = 'truongvanminh' UNION ALL
SELECT userId, 'Junior Fullstack 1 năm kinh nghiệm NodeJS.', '/cv/ngothingoc_cv.pdf', '2002-11-15', 1.0 FROM `User` WHERE userName = 'ngothingoc' UNION ALL
SELECT userId, 'Senior Fullstack 4 năm NodeJS + VueJS.', '/cv/dinhvanphong_cv.pdf', '1998-03-22', 4.0 FROM `User` WHERE userName = 'dinhvanphong' UNION ALL
SELECT userId, 'Sinh viên năm cuối, biết JavaScript cơ bản.', '/cv/phamvanquan_cv.pdf', '2003-07-01', 0.0 FROM `User` WHERE userName = 'phamvanquan' UNION ALL
SELECT userId, 'Data Engineer 3 năm Python + AWS.', '/cv/hoangthiquynh_cv.pdf', '1999-12-10', 3.0 FROM `User` WHERE userName = 'hoangthiquynh' UNION ALL
SELECT userId, 'Junior Data Analyst 1 năm, sử dụng Python + MySQL.', '/cv/lethithanh_cv.pdf', '2002-02-28', 1.0 FROM `User` WHERE userName = 'lethithanh' UNION ALL
SELECT userId, 'Sinh viên năm cuối, mới học Python.', '/cv/vuvanthang_cv.pdf', '2003-10-05', 0.0 FROM `User` WHERE userName = 'vuvanthang' UNION ALL
SELECT userId, 'Backend Developer 2 năm Python + FastAPI + MongoDB + Redis.', '/cv/dangthiuyen_cv.pdf', '2001-04-18', 2.0 FROM `User` WHERE userName = 'dangthiuyen';

-- 4. HR ACCOUNT
INSERT INTO HR (hrId, emailSign, posId, compId)
-- 1. VinaSol (Có 1 Quản lý, 1 Nhân viên)
SELECT u.userId, 'Ngô Thị Lan - HR Manager | VinaSol Group', p.posId, c.compId FROM `User` u, HRPosition p, Company c WHERE u.userName = 'mgr_vinasol' AND p.posName = 'HR_MANAGER' AND c.compName = 'Tập đoàn VinaSol' UNION ALL
SELECT u.userId, 'Trần Anh Tú - Talent Acquisition | VinaSol Group', p.posId, c.compId FROM `User` u, HRPosition p, Company c WHERE u.userName = 'staff_vinasol' AND p.posName = 'HR_STAFF' AND c.compName = 'Tập đoàn VinaSol' UNION ALL

-- 2. MicroShop (1 Quản lý)
SELECT u.userId, 'Đỗ Văn Hùng - HR Manager | MicroShop', p.posId, c.compId FROM `User` u, HRPosition p, Company c WHERE u.userName = 'mgr_microshop' AND p.posName = 'HR_MANAGER' AND c.compName = 'Tập đoàn Công nghệ MicroShop' UNION ALL

-- 3. Hoàng Anh (Có 1 Quản lý, 1 Nhân viên)
SELECT u.userId, 'Bùi Thị Mai - HR Manager | Hoang Anh Tech', p.posId, c.compId FROM `User` u, HRPosition p, Company c WHERE u.userName = 'mgr_hoanganh' AND p.posName = 'HR_MANAGER' AND c.compName = 'Công ty Công nghệ Hoàng Anh' UNION ALL
SELECT u.userId, 'Lý Đình Quân - Recruiter | Hoang Anh Tech', p.posId, c.compId FROM `User` u, HRPosition p, Company c WHERE u.userName = 'staff_hoanganh' AND p.posName = 'HR_STAFF' AND c.compName = 'Công ty Công nghệ Hoàng Anh' UNION ALL

-- 4. NextGen (1 Quản lý)
SELECT u.userId, 'Vũ Minh Tuấn - HR Director | NextGen JSC', p.posId, c.compId FROM `User` u, HRPosition p, Company c WHERE u.userName = 'mgr_nextgen' AND p.posName = 'HR_MANAGER' AND c.compName = 'Công ty Cổ phần NextGen' UNION ALL

-- 5. PayMe (1 Quản lý)
SELECT u.userId, 'Lý Thu Hà - Head of HR | PayMe Fintech', p.posId, c.compId FROM `User` u, HRPosition p, Company c WHERE u.userName = 'mgr_payme' AND p.posName = 'HR_MANAGER' AND c.compName = 'Công ty Fintech PayMe';

-- Gán quyền cho Admin
INSERT INTO Admin (adminId, roleId)
SELECT userId, (SELECT roleId FROM AdminRole WHERE roleName = 'NORMAL_ADMIN') FROM `User` WHERE userName = 'admin_mod';

-- 5. JOB POSTINGS
SET @cat_backend = (SELECT catId FROM JobCategory WHERE catName = 'Backend');
SET @cat_frontend = (SELECT catId FROM JobCategory WHERE catName = 'Frontend');
SET @cat_fullstack = (SELECT catId FROM JobCategory WHERE catName = 'Fullstack');
SET @cat_ai = (SELECT catId FROM JobCategory WHERE catName = 'AI/ML');
SET @cat_devops = (SELECT catId FROM JobCategory WHERE catName = 'DevOps');
SET @cat_mobile = (SELECT catId FROM JobCategory WHERE catName = 'Mobile');

SET @lvl_junior = (SELECT levelId FROM JobLevel WHERE levelName = 'Junior');
SET @lvl_middle = (SELECT levelId FROM JobLevel WHERE levelName = 'Middle');

-- VinaSol Jobs (Đăng bởi Staff và Manager)
SET @hr_vinasol_mgr = (SELECT userId FROM `User` WHERE userName = 'mgr_vinasol');
SET @hr_vinasol_staff = (SELECT userId FROM `User` WHERE userName = 'staff_vinasol');
SET @comp_vinasol = (SELECT compId FROM Company WHERE compName = 'Tập đoàn VinaSol');

-- Junior Backend
INSERT INTO JobPosting (title, `desc`, minSalary, maxSalary, workLoc, workMode, expAt, compId, hrId, catId, levelId, stat)
VALUES ('Backend Developer (Java/Spring Boot)', 'Phát triển và bảo trì các RESTful API cho hệ thống Core sử dụng Java 21 và Spring Boot 3. Tham gia thiết kế Database MySQL và tối ưu hóa truy vấn. Yêu cầu 1+ năm kinh nghiệm thực tế, nắm vững OOP, SOLID và Git flow.', 18000000, 25000000, 'Cầu Giấy, Hà Nội', 'ONSITE', '2026-06-30 23:59:59', @comp_vinasol, @hr_vinasol_staff, @cat_backend, @lvl_junior, 'PUBLISHED');

-- Middle Frontend
INSERT INTO JobPosting (title, `desc`, minSalary, maxSalary, workLoc, workMode, expAt, compId, hrId, catId, levelId, stat)
VALUES ('Frontend Developer (ReactJS)', 'Xây dựng giao diện web SPA với ReactJS, Redux Toolkit và Tailwind CSS. Phối hợp cùng UI/UX Designer để đảm bảo pixel-perfect. Yêu cầu 2+ năm kinh nghiệm, hiểu biết về SSR/Next.js và tối ưu hóa Web Core Vitals là một lợi thế.', 25000000, 35000000, 'Cầu Giấy, Hà Nội', 'HYBRID', '2026-07-15 23:59:59', @comp_vinasol, @hr_vinasol_mgr, @cat_frontend, @lvl_middle, 'PUBLISHED');

-- MicroShop Jobs 
SET @hr_microshop = (SELECT userId FROM `User` WHERE userName = 'mgr_microshop');
SET @comp_microshop = (SELECT compId FROM Company WHERE compName = 'Tập đoàn Công nghệ MicroShop');

-- Middle Fullstack
INSERT INTO JobPosting (title, `desc`, minSalary, maxSalary, workLoc, workMode, expAt, compId, hrId, catId, levelId, stat)
VALUES ('Fullstack Developer (NodeJS/Vue)', 'Phát triển tính năng end-to-end với Node.js (Express) và Vue.js 3. Làm việc với kiến trúc Microservices, sử dụng Docker, Redis và RabbitMQ. Yêu cầu 3+ năm kinh nghiệm, tư duy System Design tốt và khả năng giải quyết bài toán high-traffic.', 30000000, 45000000, 'Nam Từ Liêm, Hà Nội', 'ONSITE', '2026-07-31 23:59:59', @comp_microshop, @hr_microshop, @cat_fullstack, @lvl_middle, 'PUBLISHED');

-- Middle AI/NLP
INSERT INTO JobPosting (title, `desc`, minSalary, maxSalary, workLoc, workMode, expAt, compId, hrId, catId, levelId, stat)
VALUES ('AI Engineer (Python/NLP)', 'Nghiên cứu và triển khai các mô hình NLP (Transformers, LLMs) cho hệ thống chatbot nội bộ. Fine-tune mô hình trên tập dữ liệu tiếng Việt. Yêu cầu kinh nghiệm sâu với PyTorch/TensorFlow và khả năng đóng gói model thành API bằng FastAPI.', 35000000, 55000000, 'Nam Từ Liêm, Hà Nội', 'HYBRID', '2026-08-15 23:59:59', @comp_microshop, @hr_microshop, @cat_ai, @lvl_middle, 'PUBLISHED');

-- Hoàng Anh Jobs
SET @hr_hoanganh_staff = (SELECT userId FROM `User` WHERE userName = 'staff_hoanganh');
SET @comp_hoanganh = (SELECT compId FROM Company WHERE compName = 'Công ty Công nghệ Hoàng Anh');

-- Middle DevOps
INSERT INTO JobPosting (title, `desc`, minSalary, maxSalary, workLoc, workMode, expAt, compId, hrId, catId, levelId, stat)
VALUES ('DevOps Engineer', 'Thiết kế và vận hành hệ thống CI/CD pipeline sử dụng GitLab CI. Quản trị hạ tầng Cloud (AWS/GCP) bằng Terraform (IaC). Giám sát hệ thống với Prometheus & Grafana. Yêu cầu kinh nghiệm deploy và vận hành cluster Kubernetes (K8s) trên môi trường Production.', 35000000, 50000000, 'Cầu Giấy, Hà Nội', 'HYBRID', '2026-08-31 23:59:59', @comp_hoanganh, @hr_hoanganh_staff, @cat_devops, @lvl_middle, 'PUBLISHED');

-- Junior Mobile
INSERT INTO JobPosting (title, `desc`, minSalary, maxSalary, workLoc, workMode, expAt, compId, hrId, catId, levelId, stat)
VALUES ('Mobile Developer (Kotlin)', 'Phát triển ứng dụng Android native sử dụng Kotlin và kiến trúc MVVM. Tích hợp RESTful API và các SDK bên thứ ba. Tham gia review code và tối ưu hóa hiệu năng ứng dụng. Yêu cầu 1+ năm kinh nghiệm, nắm vững Android Lifecycle và Jetpack Components.', 18000000, 25000000, 'Cầu Giấy, Hà Nội', 'ONSITE', '2026-07-31 23:59:59', @comp_hoanganh, @hr_hoanganh_staff, @cat_mobile, @lvl_junior, 'PUBLISHED');
-- NextGen & PayMe Jobs
SET @hr_nextgen = (SELECT userId FROM `User` WHERE userName = 'mgr_nextgen');
SET @comp_nextgen = (SELECT compId FROM Company WHERE compName = 'Công ty Cổ phần NextGen');
SET @hr_payme = (SELECT userId FROM `User` WHERE userName = 'mgr_payme');
SET @comp_payme = (SELECT compId FROM Company WHERE compName = 'Công ty Fintech PayMe');

INSERT INTO JobPosting (title, `desc`, minSalary, maxSalary, workLoc, workMode, expAt, compId, hrId, catId, levelId, stat)
VALUES ('Backend Developer (Python/FastAPI)', 'Xây dựng API backend với FastAPI...', 18000000, 32000000, 'Đống Đa, Hà Nội', 'HYBRID', '2026-08-15 23:59:59', @comp_nextgen, @hr_nextgen, @cat_backend, @lvl_junior, 'PUBLISHED');

INSERT INTO JobPosting (title, `desc`, minSalary, maxSalary, workLoc, workMode, expAt, compId, hrId, catId, levelId, stat)
VALUES ('Frontend Developer (VueJS)', 'Giao diện ứng dụng fintech VueJS...', 16000000, 28000000, 'Ba Đình, Hà Nội', 'ONSITE', '2026-08-31 23:59:59', @comp_payme, @hr_payme, @cat_frontend, @lvl_junior, 'PUBLISHED');

-- 6. MAPPING SKILLS
INSERT INTO JobRequirement (jobPostId, skillId)
SELECT jp.jobPostId, s.skillId FROM JobPosting jp CROSS JOIN Skill s
WHERE jp.title = 'Backend Developer (Java/Spring Boot)' AND s.skillName IN ('Java','Spring Boot','PostgreSQL','Git','Docker');

INSERT INTO JobRequirement (jobPostId, skillId)
SELECT jp.jobPostId, s.skillId FROM JobPosting jp CROSS JOIN Skill s
WHERE jp.title = 'Frontend Developer (ReactJS)' AND s.skillName IN ('ReactJS','TypeScript','HTML/CSS','Git','Tailwind CSS','Redux');

INSERT INTO JobRequirement (jobPostId, skillId)
SELECT jp.jobPostId, s.skillId FROM JobPosting jp CROSS JOIN Skill s
WHERE jp.title = 'AI Engineer (Python/NLP)' AND s.skillName IN ('Python','NLP','TensorFlow','PyTorch','PostgreSQL','FastAPI','Docker','IELTS');

INSERT INTO CandidateSkill (candidateId, skillId)
SELECT u.userId, s.skillId FROM `User` u CROSS JOIN Skill s
WHERE u.userName = 'nguyenvanan' AND s.skillName IN ('Java','Spring Boot','PostgreSQL','Docker','Git','Làm việc nhóm','ReactJS','Python','Machine Learning');

INSERT INTO CandidateSkill (candidateId, skillId)
SELECT u.userId, s.skillId FROM `User` u CROSS JOIN Skill s
WHERE u.userName = 'phamthidung' AND s.skillName IN ('Python','PostgreSQL','Docker','AWS','Linux');

-- 7. JOB APPLICATIONS
INSERT INTO JobApplication (candidateId, jobPostId, appliedAt, stat, cvSnapUrl, coverLetter)
SELECT u.userId, jp.jobPostId, '2026-04-01 08:00:00', 'ACCEPTED', '/snapshots/an_backend_vinasol.pdf', 'Tôi là Nguyễn Văn An, Backend 3 năm kinh nghiệm...'
FROM `User` u CROSS JOIN JobPosting jp WHERE u.userName='nguyenvanan' AND jp.title='Backend Developer (Java/Spring Boot)';

INSERT INTO JobApplication (candidateId, jobPostId, appliedAt, stat, cvSnapUrl, coverLetter)
SELECT u.userId, jp.jobPostId, '2026-04-08 08:00:00', 'ACCEPTED', '/snapshots/dung_ai_microshop.pdf', 'Data Engineer 2 năm, thành thạo Python...'
FROM `User` u CROSS JOIN JobPosting jp WHERE u.userName='phamthidung' AND jp.title='AI Engineer (Python/NLP)';

-- 8. STATUS HISTORY, INTERVIEW & OFFER
SET @app_an_backend = (SELECT ja.jobAppId FROM JobApplication ja JOIN `User` u ON ja.candidateId=u.userId JOIN JobPosting jp ON ja.jobPostId=jp.jobPostId WHERE u.userName='nguyenvanan' AND jp.title='Backend Developer (Java/Spring Boot)');
SET @app_dung_ai = (SELECT ja.jobAppId FROM JobApplication ja JOIN `User` u ON ja.candidateId=u.userId JOIN JobPosting jp ON ja.jobPostId=jp.jobPostId WHERE u.userName='phamthidung' AND jp.title='AI Engineer (Python/NLP)');

-- Status History (HR Manager xử lý)
INSERT INTO AppStatusHistory (jobAppId, hrId, oldStat, newStat, changeAt) VALUES 
(@app_an_backend, @hr_vinasol_mgr, 'PENDING', 'REVIEWING', '2026-04-03 09:00:00'),
(@app_an_backend, @hr_vinasol_mgr, 'REVIEWING', 'INTERVIEWING', '2026-04-05 10:00:00'),
(@app_an_backend, @hr_vinasol_mgr, 'INTERVIEWING', 'ACCEPTED', '2026-04-20 14:00:00');

-- Interviews
INSERT INTO Interview (jobAppId, title, startAt, endAt, stat, mode, linkMeet, loc) VALUES 
(@app_an_backend, 'Phỏng vấn Vòng 1', '2026-04-07 09:00:00', '2026-04-07 10:00:00', 'COMPLETED', 'ONLINE', 'https://meet.google.com/vinasol-j1', NULL),
(@app_an_backend, 'Phỏng vấn Vòng 2', '2026-04-14 09:00:00', '2026-04-14 10:30:00', 'COMPLETED', 'OFFLINE', NULL, 'Tòa nhà VinaSol, Tầng 8');

SET @iv_an_v1 = (SELECT intervId FROM Interview WHERE jobAppId=@app_an_backend AND title='Phỏng vấn Vòng 1');
INSERT INTO InterviewFeedback (intervId, hrId, score, cmt, subAt) VALUES 
(@iv_an_v1, @hr_vinasol_mgr, 9.00, 'Java senior xuất sắc. Kiến thức Spring Boot sâu.', '2026-04-07 10:30:00');

-- Offers
INSERT INTO Offer (jobAppId, hrId, salary, `desc`, stat, ver) VALUES 
(@app_an_backend, @hr_vinasol_mgr, 22000000, 'Thử việc 2 tháng. Lương thử việc 85%.', 'ACCEPTED', 1);

INSERT INTO Offer (jobAppId, hrId, salary, `desc`, stat, ver) VALUES 
(@app_dung_ai, @hr_microshop, 25000000, 'Thử việc 2 tháng. Lương 14 tháng.', 'REJECTED', 1),
(@app_dung_ai, @hr_microshop, 28000000, 'Update sau đàm phán. Có project bonus.', 'ACCEPTED', 2);

-- 9. EMAIL LOGS
SET @tmpl_invite = (SELECT tmplId FROM EmailTemplate et JOIN EmailType ty ON et.typeId=ty.typeId WHERE ty.typeName='INTERVIEW_INVITE');
SET @tmpl_offer = (SELECT tmplId FROM EmailTemplate et JOIN EmailType ty ON et.typeId=ty.typeId WHERE ty.typeName='OFFER_LETTER');

INSERT INTO EmailLog (tmplId, jobAppId, userId, content, sentAt, rcvEmail) VALUES 
(@tmpl_invite, @app_an_backend, @hr_vinasol_mgr, 'Mời phỏng vấn vòng 1 Backend Java. Link meet đính kèm.', '2026-04-05 15:00:00', 'nguyenvanan@gmail.com'),
(@tmpl_offer, @app_an_backend, @hr_vinasol_mgr, 'Gửi Offer letter lương 22M VNĐ.', '2026-04-16 10:00:00', 'nguyenvanan@gmail.com');