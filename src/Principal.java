import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Principal {

	public static void main(String[] args) throws SQLException, ParseException {

		// Inserir os funcionários
//		Funcionario f1 = new Funcionario();
//		Funcionario f2 = new Funcionario();
//		Funcionario f3 = new Funcionario();
//		Funcionario f4 = new Funcionario();
//		Funcionario f5 = new Funcionario();
//		Funcionario f6 = new Funcionario();
//		Funcionario f7 = new Funcionario();
//		Funcionario f8 = new Funcionario();
//		Funcionario f9 = new Funcionario();
//		Funcionario f10 = new Funcionario();
		
		//Setar valores		
//		f1.setNome("Maria");
//		f1.setDataNascimento(LocalDate.of(2000, 10, 18));
//		f1.setSalario(BigDecimal.valueOf(2009.44));
//		f1.setFuncao("Operador");
//		
//		f2.setNome("João");
//		f2.setDataNascimento(LocalDate.of(1990, 05, 12));
//		f2.setSalario(BigDecimal.valueOf(2284.38));
//		f2.setFuncao("Operador");
//		
//		f3.setNome("Caio");
//		f3.setDataNascimento(LocalDate.of(1961, 05, 02));
//		f3.setSalario(BigDecimal.valueOf(9836.14));
//		f3.setFuncao("Coordenador");
//		
//		f4.setNome("Miguel");
//		f4.setDataNascimento(LocalDate.of(1988, 10, 14));
//		f4.setSalario(BigDecimal.valueOf(19119.88));
//		f4.setFuncao("Diretor");
//		
//		f5.setNome("Alice");
//		f5.setDataNascimento(LocalDate.of(1995, 01, 05));
//		f5.setSalario(BigDecimal.valueOf(2234.68));
//		f5.setFuncao("Recepcionista");
//		
//		f6.setNome("Heitor");
//		f6.setDataNascimento(LocalDate.of(1999, 11, 19));
//		f6.setSalario(BigDecimal.valueOf(1582.72));
//		f6.setFuncao("Operador");
//		
//		f7.setNome("Arthur");
//		f7.setDataNascimento(LocalDate.of(1993, 03, 31));
//		f7.setSalario(BigDecimal.valueOf(4071.84));
//		f7.setFuncao("Contador");
//		
//		f8.setNome("Laura");
//		f8.setDataNascimento(LocalDate.of(1994, 07, 8));
//		f8.setSalario(BigDecimal.valueOf(3017.45));
//		f8.setFuncao("Gerente");
//		
//		f9.setNome("Heloísa");
//		f9.setDataNascimento(LocalDate.of(2003, 05, 24));
//		f9.setSalario(BigDecimal.valueOf(1606.85));
//		f9.setFuncao("Eletricista");
//		
//		f10.setNome("Helena");
//		f10.setDataNascimento(LocalDate.of(1996, 9, 02));
//		f10.setSalario(BigDecimal.valueOf(2799.93));
//		f10.setFuncao("Gerente");

		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection connection = connectionFactory.recuperarConexao();

		// 3.1 – Inserir todos os funcionários, na mesma ordem e informações da tabela acima.
//		PreparedStatement stm = connection.prepareStatement("INSERT INTO funcionarios (nome, data_nascimento, salario, funcao) VALUES (?, ?, ?, ?)");
//		stm.setString(1, f10.getNome());
//		stm.setObject(2, f10.getDataNascimento());
//		stm.setBigDecimal(3, f10.getSalario());
//		stm.setString(4, f10.getFuncao());
//		stm.execute();
//		Integer linhasModificadas = stm.getUpdateCount();
//		System.out.println("Quantidade de linhas que foram modificadas: " + linhasModificadas);

		// 3.2 – Remover o funcionário “João” da lista.
//		PreparedStatement stm = connection.prepareStatement("DELETE FROM funcionarios WHERE nome =  ?");
//		stm.setString(1, f2.getNome());
//		stm.execute();
//		Integer linhasModificadas = stm.getUpdateCount();
//		System.out.println("Quantidade de linhas que foram modificadas: " + linhasModificadas);

		// 3.3 – Imprimir todos os funcionários com todas suas informações, sendo que:
		// • informação de data deve ser exibido no formato dd/mm/aaaa;
		// • informação de valor numérico deve ser exibida no formatado com separador de
		// milhar como ponto e decimal como vírgula.
//		PreparedStatement stm = connection.prepareStatement("SELECT * FROM funcionarios;");
//		stm.execute();
//		ResultSet rst = stm.getResultSet();
//		while(rst.next()) {
//			String nome = rst.getString(1);
//			Date dataNacimento = rst.getDate(2);
//			BigDecimal salario = rst.getBigDecimal(3);
//			String funcao = rst.getString(4);
//			
//			SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
//			String data = formatador.format(dataNacimento);
//			
//			System.out.println("Nome: " + nome);
//			System.out.println("Data de nascimento: " + data);
//			System.out.println("Salário: R$" + salario);
//			System.out.println("Cargo: " + funcao);
//		}
//		rst.close();
//		stm.close();
//		connection.close();

		// 3.4 – Os funcionários receberam 10% de aumento de salário, atualizar a lista de funcionários com novo valor.
		PreparedStatement stm = connection.prepareStatement("SELECT * FROM funcionarios;");
		PreparedStatement stm2 = connection.prepareStatement("UPDATE funcionarios SET salario = ? WHERE nome = ?;");
		stm.execute();
		ResultSet rst = stm.getResultSet();
		while(rst.next()) {
			String nome = rst.getString(1);
			Double salario = rst.getDouble(3);
			
			salario += salario * 0.10;
			BigDecimal novoSalario = new BigDecimal(salario);
			novoSalario = novoSalario.setScale(2, RoundingMode.HALF_EVEN);
			
			stm2.setBigDecimal(1, novoSalario);
			stm2.setString(2, nome);
			stm2.execute();
			System.out.println("Salário do funcionário " + nome + " foi atualizado para R$" + novoSalario);
			
		}
		rst.close();
		stm.close();
		stm2.close();
		connection.close();

		// 3.5 – Agrupar os funcionários por função em um MAP, sendo a chave a “função” e o valor a “lista de funcionários”.
//		   Map<String, String> funcionarios = new HashMap<String, String>();
//		   PreparedStatement stm = connection.prepareStatement("SELECT funcao, nome FROM funcionarios;");
//			stm.execute();
//			ResultSet rst = stm.getResultSet();
//			while(rst.next()) {
//				String funcao = rst.getString(1);
//				String nome = rst.getString(2);
//				funcionarios.put(funcao, nome);
//			}
//			System.out.println(funcionarios);
//			rst.close();
//			stm.close();
		
		// 3.6 – Imprimir os funcionários, agrupados por função. - não consegui
		
		// 3.8 – Imprimir os funcionários que fazem aniversário no mês 10 e 12.
//		PreparedStatement stm = connection.prepareStatement(
//				"SELECT nome, MONTH(data_nascimento) FROM funcionarios WHERE MONTH(data_nascimento) = 10 OR MONTH(data_nascimento) = 12;");
//		stm.execute();
//		ResultSet rst = stm.getResultSet();
//		while(rst.next()){
//			String nome = rst.getString(1);
//			System.out.println(nome);
//		}
//		rst.close();
//		stm.close();

		// 3.9 – Imprimir o funcionário com a maior idade, exibir os atributos: nome e
		// idade.
//		PreparedStatement stm = connection
//				.prepareStatement("SELECT nome, YEAR(data_nascimento) FROM funcionarios ORDER BY YEAR(data_nascimento) ASC;");
//		stm.execute();
//		ResultSet rst = stm.getResultSet();
//		while(rst.next()) {
//		String nome = rst.getString(1);
//		int ano = rst.getInt(2);
//		int idade = 2022 - ano;
//		
//		System.out.println("Funcionário " + nome + " com " + idade + " anos");
//		}
//		rst.close();
//		stm.close();
		
		//3.10 – Imprimir a lista de funcionários por ordem alfabética
//		PreparedStatement stm = connection
//				.prepareStatement("SELECT nome FROM funcionarios ORDER BY nome;");
//		stm.execute();
//		ResultSet rst = stm.getResultSet();
//		while(rst.next()) {
//		String nome = rst.getString(1);
//	
//		System.out.println(nome);
//		}
//		rst.close();
//		stm.close();
		
		//3.11 – Imprimir o total dos salários dos funcionários
//		PreparedStatement stm = connection.prepareStatement("SELECT SUM(salario) FROM funcionarios;");
//		stm.execute();
//		ResultSet rst = stm.getResultSet();
//		while(rst.next()) {
//		double somaSalarios = rst.getDouble(1);
//		BigDecimal somaSalariosFinal = new BigDecimal(somaSalarios);
//		somaSalariosFinal = somaSalariosFinal.setScale(2, RoundingMode.HALF_EVEN);
//		
//		System.out.println("R$"+somaSalariosFinal);
//		}
//		rst.close();
//		stm.close();
		
		//3.12 – Imprimir quantos salários mínimos ganha cada funcionário, considerando que o salário mínimo é R$1212.00.
//		PreparedStatement stm = connection.prepareStatement("SELECT nome, salario FROM funcionarios;");
//		stm.execute();
//		ResultSet rst = stm.getResultSet();
//		while(rst.next()) {
//			String nome = rst.getString(1);
//			double salario = rst.getDouble(2);
//			double salariosMinimos = salario / 1212;
//			int salariosMinimosInteiros = (int)salariosMinimos;
//			System.out.println("O funcionário " + nome + " ganha " + salariosMinimosInteiros + " salário(s) mínimo(s)");
//		}
//		rst.close();
//		stm.close();
	}

}
