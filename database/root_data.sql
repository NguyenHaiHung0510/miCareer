USE miCareer_DB;

INSERT INTO Region (regId, regName) VALUES
('NORTH','Miền Bắc'),
('CENTRAL','Miền Trung'),
('SOUTH','Miền Nam');

INSERT INTO Province (provId, provName, regId) VALUES
('HN','Thành phố Hà Nội','NORTH'),
('HU','Thành phố Huế','CENTRAL'),
('LC','Tỉnh Lai Châu','NORTH'),
('DB','Tỉnh Điện Biên','NORTH'),
('SL','Tỉnh Sơn La','NORTH'),
('LS','Tỉnh Lạng Sơn','NORTH'),
('QN','Tỉnh Quảng Ninh','NORTH'),
('TH','Tỉnh Thanh Hóa','CENTRAL'),
('NA','Tỉnh Nghệ An','CENTRAL'),
('HT','Tỉnh Hà Tĩnh','CENTRAL'),
('CB','Tỉnh Cao Bằng','NORTH'),
('TQ','Tỉnh Tuyên Quang','NORTH'),
('LCA','Tỉnh Lào Cai','NORTH'),
('TN','Tỉnh Thái Nguyên','NORTH'),
('PT','Tỉnh Phú Thọ','NORTH'),
('BN','Tỉnh Bắc Ninh','NORTH'),
('HY','Tỉnh Hưng Yên','NORTH'),
('HP','Thành phố Hải Phòng','NORTH'),
('NB','Tỉnh Ninh Bình','CENTRAL'),
('QT','Tỉnh Quảng Trị','CENTRAL'),
('DN','Thành phố Đà Nẵng','CENTRAL'),
('QNG','Tỉnh Quảng Ngãi','CENTRAL'),
('GL','Tỉnh Gia Lai','CENTRAL'),
('KH','Tỉnh Khánh Hòa','CENTRAL'),
('LD','Tỉnh Lâm Đồng','CENTRAL'),
('DL','Tỉnh Đắk Lắk','CENTRAL'),
('HCM','TP. Hồ Chí Minh','SOUTH'),
('DNI','Tỉnh Đồng Nai','SOUTH'),
('TNIN','Tỉnh Tây Ninh','SOUTH'),
('CT','Thành phố Cần Thơ','SOUTH'),
('VL','Tỉnh Vĩnh Long','SOUTH'),
('DT','Tỉnh Đồng Tháp','SOUTH'),
('CM','Tỉnh Cà Mau','SOUTH'),
('AG','Tỉnh An Giang','SOUTH');

INSERT INTO JobCategory (catName, `desc`) VALUES
('Backend', 'Backend development'),
('Frontend', 'Frontend development'),
('DevOps', 'DevOps & Infrastructure'),
('Data', 'Data Engineering & Analysis'),
('Fullstack', 'Fullstack development'),
('Mobile', 'Mobile App development (iOS/Android)'),
('AI/ML', 'Artificial Intelligence & Machine Learning'),
('Tester', 'Quality Assurance & Software Testing'),
('Game', 'Game development (Unity/C++/C#)'),
('Embedded', 'Embedded Systems & IoT'),
('Security', 'Cyber Security & Information Assurance'),
('UI/UX Design', 'User Interface & User Experience Design'),
('Project Management', 'PM/PO/Business Analyst'),
('Cloud Computing', 'Cloud Architect & Solutions'),
('Blockchain', 'Web3 & Blockchain development'),
('System Admin', 'System Administration & Network'),
('Sales/Marketing', 'IT Sales & Digital Marketing');

INSERT INTO JobLevel (levelName, `desc`) VALUES
('Intern', 'Thực tập sinh'),
('Fresher', 'Sinh viên mới tốt nghiệp/Dưới 1 năm kinh nghiệm'),
('Junior', 'Nhân viên có từ 1-2 năm kinh nghiệm'),
('Middle', 'Nhân viên có từ 2-4 năm kinh nghiệm'),
('Senior', 'Chuyên viên có trên 5 năm kinh nghiệm'),
('Lead', 'Trưởng nhóm kỹ thuật (Tech Lead/Team Lead)'),
('Manager', 'Quản lý bộ phận/Dự án (Project Manager/Department Manager)'),
('Director', 'Giám đốc bộ phận/C-level (CTO, CIO)');

INSERT INTO HRPosition (posName, `desc`) VALUES
('HR_MANAGER','HR Quản lý'),
('HR_STAFF','HR Nhân viên');

INSERT INTO Skill (skillName, `desc`) VALUES
('Java', 'Ngôn ngữ lập trình hướng đối tượng'),
('Python', 'Ngôn ngữ lập trình đa năng, phổ biến trong AI/ML'),
('JavaScript', 'Ngôn ngữ lập trình phía client và server'),
('TypeScript', 'Superset của JavaScript có kiểu dữ liệu tĩnh'),
('C++', 'Ngôn ngữ lập trình hiệu năng cao'),
('C#', 'Ngôn ngữ lập trình của hệ sinh thái .NET'),
('PHP', 'Ngôn ngữ lập trình web phía server'),
('Swift', 'Ngôn ngữ lập trình cho hệ sinh thái Apple'),
('Kotlin', 'Ngôn ngữ lập trình Android hiện đại'),
('ReactJS', 'Thư viện JavaScript xây dựng giao diện'),
('VueJS', 'Framework JavaScript nhẹ cho frontend'),
('Angular', 'Framework frontend của Google'),
('HTML/CSS', 'Ngôn ngữ đánh dấu và tạo kiểu web'),
('Spring Boot', 'Framework Java phát triển ứng dụng web'),
('FastAPI', 'Framework Python xây dựng API hiệu năng cao'),
('NodeJS', 'Runtime JavaScript phía server'),
('Django', 'Framework Python full-stack'),
('MySQL', 'Hệ quản trị cơ sở dữ liệu quan hệ'),
('PostgreSQL', 'Hệ quản trị CSDL quan hệ mã nguồn mở'),
('MongoDB', 'Cơ sở dữ liệu NoSQL hướng tài liệu'),
('Redis', 'Hệ thống lưu trữ dữ liệu in-memory'),
('Docker', 'Nền tảng container hóa ứng dụng'),
('Git', 'Hệ thống quản lý phiên bản mã nguồn'),
('Linux', 'Hệ điều hành mã nguồn mở'),
('AWS', 'Dịch vụ điện toán đám mây Amazon'),
('TOEIC', 'Chứng chỉ tiếng Anh giao tiếp quốc tế'),
('IELTS', 'Chứng chỉ tiếng Anh học thuật quốc tế'),
('Làm việc nhóm', 'Kỹ năng phối hợp và cộng tác'),
('Giao tiếp', 'Kỹ năng truyền đạt và lắng nghe'),
('Quản lý thời gian', 'Kỹ năng sắp xếp và ưu tiên công việc'),
('ExpressJS', 'Framework web tối giản cho Node.js'),
('NestJS', 'Framework Node.js xây dựng ứng dụng server-side có cấu trúc'),
('Nginx', 'Web server và reverse proxy hiệu năng cao'),
('PM2', 'Process manager cho ứng dụng Node.js'),
('RESTful API', 'Kiến trúc thiết kế API theo nguyên tắc REST'),
('GraphQL', 'Ngôn ngữ truy vấn API do Facebook phát triển'),
('WebSocket', 'Giao thức truyền dữ liệu hai chiều thời gian thực'),
('Flutter', 'Framework phát triển ứng dụng đa nền tảng của Google'),
('Svelte', 'Framework frontend biên dịch tại thời điểm build'),
('Tailwind CSS', 'Utility-first CSS framework'),
('Redux', 'Thư viện quản lý state cho ứng dụng JavaScript'),
('Webpack', 'Module bundler cho ứng dụng JavaScript'),
('Vite', 'Build tool frontend thế hệ mới, tốc độ cao'),
('Machine Learning', 'Học máy - xây dựng mô hình từ dữ liệu'),
('Deep Learning', 'Học sâu - mạng nơ-ron nhiều tầng'),
('NLP', 'Xử lý ngôn ngữ tự nhiên'),
('TensorFlow', 'Framework mã nguồn mở cho Machine Learning của Google'),
('PyTorch', 'Framework Deep Learning của Meta'),
('SQL', 'Ngôn ngữ truy vấn cơ sở dữ liệu quan hệ'),
('Azure ML', 'Dịch vụ Machine Learning trên nền tảng đám mây Microsoft'),
('GitHub', 'Nền tảng lưu trữ và quản lý mã nguồn dựa trên Git');

INSERT INTO AdminRole (roleName, `desc`) VALUES
('SUPER_ADMIN','Quản trị viên cấp cao, toàn quyền hệ thống'),
('NORMAL_ADMIN','Quản trị viên thường, kiểm duyệt nội dung');

INSERT INTO Permission (permCode, `desc`) VALUES
('USER_CREATE', 'Tạo người dùng mới'),
('USER_UPDATE', 'Cập nhật thông tin người dùng'),
('USER_DELETE', 'Xóa người dùng'),
('USER_BAN', 'Khóa/Chặn người dùng'),
('USER_MANAGE', 'Quản lý người dùng chung'),
('COMPANY_CREATE', 'Thêm mới công ty'),
('COMPANY_VERIFY', 'Xác thực thông tin công ty'),
('JOB_CREATE', 'Đăng tin tuyển dụng'),
('JOB_UPDATE', 'Cập nhật tin tuyển dụng'),
('JOB_DELETE', 'Xóa tin tuyển dụng'),
('JOB_APPROVE', 'Phê duyệt tin tuyển dụng'),
('SKILL_MANAGE', 'Quản lý danh mục kỹ năng'),
('EMAIL_TEMPLATE_MANAGE', 'Quản lý mẫu email'),
('VIEW_REPORT', 'Xem báo cáo thống kê');

INSERT INTO RolePermission (roleId, permId)
SELECT r.roleId, p.permId 
FROM AdminRole r 
CROSS JOIN Permission p 
WHERE r.roleName = 'SUPER_ADMIN';

INSERT INTO `User` (userName, pwd, fName, lName, email, phone, stat, `role`, provId)
VALUES ('admin', '1', 'System', 'Admin', 'admin@micareer.vn', '0987654321', 'ACTIVE', 'ADMIN', 'HN');

-- Map User này vào bảng Admin với role SUPER_ADMIN
INSERT INTO Admin (adminId, roleId)
SELECT u.userId, r.roleId 
FROM `User` u 
JOIN AdminRole r ON r.roleName = 'SUPER_ADMIN' 
WHERE u.userName = 'admin';

INSERT INTO EmailType (typeName, `desc`) VALUES
('INTERVIEW_INVITE', 'Thư mời phỏng vấn'),
('OFFER_LETTER', 'Thư đề nghị công việc'),
('REJECTION', 'Thư từ chối ứng viên'),
('APPLICATION_RECEIVED', 'Xác nhận đã nhận đơn ứng tuyển'),
('WELCOME', 'Chào mừng ứng viên đăng ký tài khoản'),
('STATUS_UPDATE', 'Thông báo cập nhật trạng thái đơn ứng tuyển');

INSERT INTO EmailTemplate (typeId, subj, body, `desc`) VALUES
(
  (SELECT typeId FROM EmailType WHERE typeName = 'INTERVIEW_INVITE'),
  'Thư mời phỏng vấn - {{companyName}} - Vị trí {{jobTitle}}',
  'Kính gửi {{candidateName}},\n\nSau khi xem xét kỹ lưỡng hồ sơ của bạn, chúng tôi xin mời bạn tham gia phỏng vấn cho vị trí {{jobTitle}} tại {{companyName}}.\n\nThời gian: {{interviewTime}}\nHình thức: {{interviewMode}}\nĐịa điểm: {{interviewLocation}}\nĐể buổi phỏng vấn thuận lợi vui lòng chuẩn bị kỹ và có mặt đúng giờ.\n\nTrân trọng,\n{{hrName}}',
  'Mẫu mời phỏng vấn mặc định'
),
(
  (SELECT typeId FROM EmailType WHERE typeName = 'OFFER_LETTER'),
  'Thư đề nghị công việc - {{companyName}}',
  'Kính gửi {{candidateName}},\n\nChúc mừng! Chúng tôi vui mừng thông báo bạn đã được chọn cho vị trí {{jobTitle}} tại {{companyName}}.\n\nMức lương: {{salary}}\nNgày bắt đầu dự kiến: {{startDate}}\n\nVui lòng phản hồi trong vòng 7 ngày.\n\nTrân trọng,\n{{hrName}}',
  'Mẫu offer mặc định'
),
(
  (SELECT typeId FROM EmailType WHERE typeName = 'REJECTION'),
  'Thông báo kết quả ứng tuyển - {{companyName}}',
  'Kính gửi {{candidateName}},\n\nCảm ơn bạn đã quan tâm đến vị trí {{jobTitle}} tại {{companyName}}.\n\nSau khi xem xét kỹ lưỡng, chúng tôi rất tiếc phải thông báo rằng chúng tôi đã chọn ứng viên khác phù hợp hơn.\n\nChúng tôi mong được hợp tác cùng bạn trong những cơ hội tiếp theo.\nChúc bạn thành công.\n\nTrân trọng,\n{{hrName}}',
  'Mẫu từ chối mặc định'
),
(
  (SELECT typeId FROM EmailType WHERE typeName = 'APPLICATION_RECEIVED'),
  'Xác nhận đơn ứng tuyển - {{companyName}}',
  'Kính gửi {{candidateName}},\n\nChúng tôi đã nhận được đơn ứng tuyển của bạn cho vị trí {{jobTitle}}.\nĐơn của bạn đang trong quá trình xem xét. Chúng tôi sẽ thông báo kết quả trong thời gian sớm nhất.\n\nTrân trọng,\nĐội ngũ tuyển dụng {{companyName}}',
  'Mẫu xác nhận nhận đơn'
),
(
  (SELECT typeId FROM EmailType WHERE typeName = 'WELCOME'),
  'Chào mừng bạn đến với miCareer!',
  'Kính gửi {{candidateName}},\n\nChào mừng bạn đã đăng ký tài khoản tại miCareer!\n\nHãy hoàn thiện hồ sơ và upload CV để bắt đầu tìm kiếm cơ hội việc làm phù hợp nhé.\n\nChúc bạn Mã đáo thành công!\nĐội ngũ miCareer',
  'Mẫu chào mừng đăng ký'
),
(
  (SELECT typeId FROM EmailType WHERE typeName = 'STATUS_UPDATE'),
  'Cập nhật trạng thái đơn ứng tuyển - {{companyName}}',
  'Kính gửi {{candidateName}},\n\nĐơn ứng tuyển của bạn cho vị trí {{jobTitle}} tại {{companyName}} đã được cập nhật. Vui lòng kiểm tra trên website.\n\nTrạng thái mới: {{newStatus}}\n\nTrân trọng,\n{{hrName}}',
  'Mẫu thông báo đổi trạng thái'
);
