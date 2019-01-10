insert into ruangan(id,kode_ruangan) values
('1','301'),
('2','302'),
('3','303');

insert into dosen(id,kode,nama,nomor_telepon,jenis_kelamin) values
('1','D001','Profesor Ucok','01234567','PRIA'),
('2','D002','Profesor Udin','02345523','PRIA'),
('3','D003','Profesor Ucik','02345523','WANITA');

insert into mata_kuliah(id,kode,nama,waktu,dosen_id,ruangan_id)values
('1','MK01','Matematika','08:00','1','1'),
('2','MK02','TIK','09:00','2','2'),
('3','MK03','Agama','10:00','3','3');

INSERT INTO `user` (id,  username, nama,aktif,  user_type) VALUES
('admin', 'admin.kampus@yopmail.com', 'Administrator',true ,'ADMIN');

INSERT INTO user_password (user_id, password) VALUES
('admin', '$2a$08$LS3sz9Ft014MNaIGCEyt4u6VflkslOW/xosyRbinIF9.uaVLpEhB6');

insert into user_profile (id, user_id, nama_lengkap, nomor_telepon, alamat, foto) values
('p_admin','admin','Administrator','085839383','jalan sana sini',NULL);

