select * from conta c 

select * from movimentacao m2  

select sum(valor) from movimentacao m 

select avg(valor) from movimentacao m 

insert into Movimentacao (data, valor, conta_id, descricao, tipoMovimentacao) values ('2017-01-12 18:01:07', 80.0, 2, 'Restaurante', 1);
insert into Movimentacao (data, valor, conta_id, descricao, tipoMovimentacao) values ('2017-01-12 19:31:12', 100.0, 2, 'Cinema', 1);
insert into Movimentacao (data, valor, conta_id, descricao, tipoMovimentacao) values ('2017-01-13 10:01:54', 40.0, 2, 'Café da manhã', 1);
insert into Movimentacao (data, valor, conta_id, descricao, tipoMovimentacao) values ('2017-01-14 15:20:13', 20.0, 2, 'Lanche', 1);