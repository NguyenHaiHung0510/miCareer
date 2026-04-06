USE miCareer_DB;

-- 							--------REGION---------
INSERT INTO Region (regId, regName) VALUES
('NORTH','Miền Bắc'),
('CENTRAL','Miền Trung'),
('SOUTH','Miền Nam');

-- 							-------PROVINCE----------
INSERT INTO Province (provId, provName, regId) VALUES
-- 11 Đơn vị giữ nguyên không sáp nhập
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

-- 23 Đơn vị hình thành sau sáp nhập
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

-- 						----------ADMIN ROLE-----------
INSERT INTO AdminRole (roleName, `desc`) VALUES
('SUPER_ADMIN','Full quyền'),
('NORMAL_ADMIN','Admin thường');

-- 							-------	PERMISSION------
INSERT INTO Permission (permCode, `desc`) VALUES
-- Nhóm Quản lý Người dùng
('USER_CREATE', 'Tạo người dùng mới'),
('USER_UPDATE', 'Cập nhật thông tin người dùng'),
('USER_DELETE', 'Xóa người dùng'),
('USER_BAN', 'Khóa/Chặn người dùng'),
('USER_MANAGE', 'Quản lý người dùng chung'),

-- Nhóm Quản lý Công ty
('COMPANY_CREATE', 'Thêm mới công ty'),
('COMPANY_VERIFY', 'Xác thực thông tin công ty'),

-- Nhóm Quản lý Tin tuyển dụng (Job)
('JOB_CREATE', 'Đăng tin tuyển dụng'),
('JOB_UPDATE', 'Cập nhật tin tuyển dụng'),
('JOB_DELETE', 'Xóa tin tuyển dụng'),
('JOB_APPROVE', 'Phê duyệt tin tuyển dụng'),

-- Nhóm Quản lý Hệ thống & Email
('SKILL_MANAGE', 'Quản lý danh mục kỹ năng'),
('EMAIL_TEMPLATE_MANAGE', 'Quản lý mẫu email'),
('EMAIL_LOG_VIEW', 'Xem lịch sử gửi email'),
('EMAIL_SEND', 'Gửi email hệ thống'),

-- Nhóm Báo cáo
('VIEW_REPORT', 'Xem báo cáo thống kê');

-- 						--------------HR POSITION--------
INSERT INTO HRPosition (posName, `desc`) VALUES
('HR_MANAGER','Quản lý HR'),
('HR_STAFF','Nhân viên HR');

-- 							----------JOB CATEGORY-------------
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

-- 							-----------JOB LEVEL-------
INSERT INTO JobLevel (levelName, `desc`) VALUES
('Intern', 'Thực tập sinh'),
('Fresher', 'Sinh viên mới tốt nghiệp/Dưới 1 năm kinh nghiệm'),
('Junior', 'Nhân viên có từ 1-2 năm kinh nghiệm'),
('Middle', 'Nhân viên có từ 2-4 năm kinh nghiệm'),
('Senior', 'Chuyên viên có trên 5 năm kinh nghiệm'),
('Lead', 'Trưởng nhóm kỹ thuật (Tech Lead/Team Lead)'),
('Manager', 'Quản lý bộ phận/Dự án (Project Manager/Department Manager)'),
('Director', 'Giám đốc bộ phận/C-level (CTO, CIO)');

-- 							--------SKILL-----------
INSERT INTO Skill (skillName, `desc`) VALUES
('Java', ''),
('Spring Boot', ''),
('C#', ''),
('.NET', ''),
('Python', ''),
('Django', ''),
('Flask', ''),
('JavaScript', ''),
('TypeScript', ''),
('ReactJS', ''),
('VueJS', ''),
('Angular', ''),
('NodeJS', ''),
('ExpressJS', ''),
('MySQL', ''),
('PostgreSQL', ''),
('MongoDB', ''),
('Redis', ''),
('Docker', ''),
('Kubernetes', ''),
('AWS', ''),
('Linux', ''),
('Git', '');

-- 						------------EMAIL TYPE-----------
INSERT INTO EmailType (typeName, `desc`) VALUES
('APP_CONFIRM',''),
('INTERVIEW_INVITE',''),
('OFFER','');
