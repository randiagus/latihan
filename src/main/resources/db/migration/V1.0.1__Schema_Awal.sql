CREATE TABLE dosen 
  ( 
     id            VARCHAR(36) NOT NULL, 
     jenis_kelamin VARCHAR(255), 
     kode          VARCHAR(255), 
     nama          VARCHAR(255), 
     nomor_telepon VARCHAR(255), 
     PRIMARY KEY (id) 
  ) 
engine=myisam; 

CREATE TABLE mahasiswa 
  ( 
     id            VARCHAR(36) NOT NULL, 
     jenis_kelamin INTEGER, 
     nama          VARCHAR(255), 
     nim           VARCHAR(255), 
     tanggal_lahir DATE, 
     PRIMARY KEY (id) 
  ) 
engine=myisam; 

CREATE TABLE mata_kuliah_mahasiswa 
  ( 
     id_mahasiswa   VARCHAR(36) NOT NULL, 
     id_mata_kuliah VARCHAR(36) NOT NULL, 
     PRIMARY KEY (id_mahasiswa, id_mata_kuliah) 
  ) 
engine=myisam; 

CREATE TABLE mata_kuliah 
  ( 
     id         VARCHAR(36) NOT NULL, 
     kode       VARCHAR(255), 
     nama       VARCHAR(255), 
     waktu      VARCHAR(36),
     dosen_id   VARCHAR(36), 
     ruangan_id VARCHAR(36), 
     PRIMARY KEY (id) 
  ) 
engine=myisam; 

CREATE TABLE ruangan 
  ( 
     id           VARCHAR(36) NOT NULL, 
     kode_ruangan VARCHAR(255), 
     PRIMARY KEY (id) 
  ) 
engine=myisam; 

CREATE TABLE user 
  ( 
     id               VARCHAR(36) NOT NULL, 
     nama             VARCHAR(255) NOT NULL, 
     user_type        VARCHAR(255),
     username         VARCHAR(255) NOT NULL,
     aktif         bit NOT NULL,
     PRIMARY KEY (id)
  ) 
engine=myisam; 

CREATE TABLE user_password 
  ( 
     password VARCHAR(255) NOT NULL,
     user_id  VARCHAR(36) NOT NULL, 
     PRIMARY KEY (user_id)
  ) 
engine=myisam;

create table user_profile (
  id varchar(36) not null,
  alamat varchar(255),
  foto varchar(255),
  nama_lengkap varchar(255),
  nomor_telepon varchar(255),
  user_id varchar(36) not null,
  primary key (id)
  ) engine=MyISAM;

alter table user_profile add constraint FK6kwj5lk78pnhwor4pgosvb51r foreign key (user_id) references user (id);


ALTER TABLE dosen 
  ADD CONSTRAINT uk_p5vyj40fb0vry7awpymkhl9bj UNIQUE (kode);

ALTER TABLE mahasiswa 
  ADD CONSTRAINT uk_kvm6yjgxjs9vo3qhqsjog1a1p UNIQUE (nim);

ALTER TABLE mata_kuliah
  ADD CONSTRAINT uk_cpael77ii7swdam4pfgo850il UNIQUE (kode);

ALTER TABLE ruangan
  ADD CONSTRAINT uk_my80hvbe5nusbmbi1x52b98ui UNIQUE (kode_ruangan);

alter table user
  add constraint UK_sb8bbouer5wak8vyiiy4pf2bx unique (username);

ALTER TABLE mata_kuliah_mahasiswa
  ADD CONSTRAINT fkcvauxqudadqk23omt1uf84cfm FOREIGN KEY (id_mata_kuliah)
  REFERENCES mata_kuliah (id);

ALTER TABLE mata_kuliah_mahasiswa
  ADD CONSTRAINT fkrca460g1gdf0t0ehe38knnhi3 FOREIGN KEY (id_mahasiswa)
  REFERENCES mahasiswa (id);

ALTER TABLE mata_kuliah
  ADD CONSTRAINT fka56tfl77fs5iasubrog242u4c FOREIGN KEY (dosen_id) REFERENCES
  dosen (id);

ALTER TABLE mata_kuliah
  ADD CONSTRAINT fkp835uooirrkt2kb9por65vx3t FOREIGN KEY (ruangan_id) REFERENCES
  ruangan (id);

ALTER TABLE user_password
  ADD CONSTRAINT fkrf8m4kwo8g1vnn10tnbev9df0 FOREIGN KEY (user_id) REFERENCES
  user (id)

