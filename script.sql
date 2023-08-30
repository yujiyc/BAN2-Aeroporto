create table modelo(
    codigo serial not null,
    capacidade int,
    peso double precision,
    primary key (codigo)
);

create table aviao(
    nroRegistro serial not null,
    nome varchar(80),
    codigo int,
    primary key (nroRegistro),
    foreign key (codigo) references modelo (codigo) match simple
    on update cascade on delete set null
);

create table teste(
    idANAC serial not null,
    nome varchar(80),
    pont_max int,
    codigo int,
    primary key (idANAC),
    foreign key (codigo) references modelo (codigo) match simple
    on update cascade on delete set null
);

create table sindicato(
    nroMembro serial not null,
    nome varchar(80),
    primary key (nroMembro)
);

create table empregado(
    matricula serial not null,
    nome varchar(80),
    telefone char(12),
    salario double precision,
    endereco varchar(100),
    nroMembro int,
    primary key (matricula),
    foreign key (nroMembro) references sindicato (nroMembro) match simple
    on update cascade on delete set null
);

create table tecnico(
    matricula int not null,
    nroMembro int,
    primary key (matricula),
    foreign key (matricula) references empregado (matricula) match simple
    on update cascade on delete set null,
    foreign key (nroMembro) references sindicato (nroMembro) match simple
    on update cascade on delete set null
);

create table controlador_aereo(
    matricula int not null,
    data_Exame date,
    nroMembro int,
    primary key (matricula),
    foreign key (matricula) references empregado (matricula) match simple
    on update cascade on delete set null,
    foreign key (nroMembro) references sindicato (nroMembro) match simple
    on update cascade on delete set null
);

create table testagem(
    id serial not null,
    nroRegistro int not null,
    matricula int not null,
    idANAC int not null,
    primary key (id),
    foreign key (nroRegistro) references aviao (nroRegistro) match simple
    on update cascade on delete set null,
    foreign key (matricula) references tecnico (matricula) match simple
    on update cascade on delete set null,
    foreign key (idANAC) references teste (idANAC) match simple
    on update cascade on delete set null
);

create table perito_em(
    id serial not null,
    matricula int not null,
    codigo int not null,
    primary key (id),
    foreign key (matricula) references tecnico (matricula) match simple
    on update cascade on delete set null,
    foreign key (codigo) references modelo (codigo) match simple
    on update cascade on delete set null
);

create table aplica(
    id serial not null,
    matricula int not null,
    idANAC int not null,
    data date,
    horas_gastas int,
    pont_obtida int,
    primary key (id),
    foreign key (matricula) references tecnico (matricula) match simple
    on update cascade on delete set null,
    foreign key (idANAC) references teste (idANAC) match simple
    on update cascade on delete set null
);