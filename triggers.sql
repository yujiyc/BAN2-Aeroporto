-- Função/Gatilho que impede a deleção de um aviao que possui uma media de nota nos testes maior que 4.

create or replace function impedeDelecaoMedia() returns trigger as
$$
declare media double precision default 0;
begin
	select avg(pont_obtida) from aviao a join testagem t using (nroregistro) 
	join tec_aplica_teste ap using (idanac)
	where nroregistro = old.nroregistro into media;
	
	if media < 4 then
		return old;
	end if;
	
	raise exception 'Não é possível excluir um avião com média de testes maior que 4!';
end;
$$
language plpgsql;

create trigger impedeDelecaoMedia before delete on aviao for each row
execute procedure impedeDelecaoMedia();

-- Função/Gatilho que Limita a quantidade de pericidade em modelos do tecnico

create or replace function impedePeritoModelo() returns trigger as
$$
declare qtd_modelos int default 0;
begin
	select count(m.codigo) from tecnico t join tec_peritoem_modelo tpm using (matricula) 
	join modelo m using (codigo) where matricula = new.matricula into qtd_modelos;
	
	if qtd_modelos < 3 then
		return new;
	end if;
	
	raise exception 'Um técnico não pode ser perito em mais do que 3 modelos!';
end;
$$
language plpgsql;

create trigger impedePeritoModelo before insert or update on tec_peritoem_modelo for each row
execute procedure impedePeritoModelo();

-- Função/Gatilho que impede a inserção de testes para um tecnico que já atingiu o limite de horas_gastas em um ems.

create or replace function impedeHorasExtras() returns trigger as
$$
declare mes char(2) default '';
		ano char(4) default '';
		horas_gastas int default 0;
begin
	select date_part('month',current_date) :: char(2) into mes;
	select date_part('year',current_date) :: char(4) into ano;
	
	select sum(horas_gastas) from tec_aplica_teste where (matricula = new.matricula) 
	and (mes = date_part('month',new.data)) and (ano = date_part('year',new.data))
	into horas_gastas;
												 
	if horas_gastas > 120 then
		raise exception 'O técnico chegou ao limite de 120 horas trabalhadas em um mes';
	end if;
	
	return new;
end;
$$
language plpgsql;

create trigger impedeHorasExtras before insert on tec_aplica_teste for each row
execute procedure impedeHorasExtras();

-- Impede a inserção de um modelo que não possua um peso minimo e uma capacidade minima.

create or replace function impedeInsercaoModelo() returns trigger as
$$ 
begin
	if new.capacidade < 200 or new.peso < 300000 then
		raise exception 'Não é possivel inserir um modelo que possua peso menor que 300 toneladas e capacidade menor que 200 passageiros!';
	end if;
	
	return new;
end;
$$
language plpgsql;

create trigger impedeInsercaoModelo before insert on modelo for each row
execute procedure impedeInsercaoModelo();