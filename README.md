# Sistema de Gestão de Barbearia

Aplicação de terminal em Java para registrar cortes de cabelo e barba, listar serviços e consultar o faturamento acumulado durante a execução.

## Requisitos

- JDK 21
- Maven

## Testes e validações

```sh
mvn test
mvn verify
```

`mvn verify` também verifica a formatação Java com Eclipse JDT. Para formatar os fontes:

```sh
mvn spotless:apply
```

## Executar

```sh
mvn package
java -cp target/classes barbearia.BarbeariaSystem
```

## Estrutura

```text
src/
	main/java/barbearia/
		BarbeariaSystem.java       # Interface de terminal
		GerenciadorBarbearia.java  # Registro e faturamento
		Servico.java               # Contrato e validações compartilhadas
		CorteCabelo.java           # Serviço de corte de cabelo
		CorteBarba.java            # Serviço de corte de barba
	test/java/barbearia/
		GerenciadorBarbeariaTest.java
		ServicoTest.java
```

## Decisões técnicas

- A interface de terminal é separada das regras de domínio; os registros ficam em memória e são descartados ao encerrar o programa.
- `Servico` concentra validações compartilhadas; valores monetários precisam ser finitos e não negativos.
- Os preços são calculados com `double` para manter o escopo simples do exercício. Para uso financeiro real, deve-se migrar para `BigDecimal` e definir regras de arredondamento.
- JUnit Jupiter cobre os cálculos, rejeição de entradas inválidas e acesso somente para leitura à lista de serviços.
