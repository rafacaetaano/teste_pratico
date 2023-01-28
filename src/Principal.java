import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class Principal {

	public static void main(String[] args) throws SQLException, ParseException {
		
		//Inserir os funcionários
		
		Funcionario f1 = new Funcionario();
		Funcionario f2 = new Funcionario();
		Funcionario f3 = new Funcionario();
		Funcionario f4 = new Funcionario();
		Funcionario f5 = new Funcionario();
		Funcionario f6 = new Funcionario();
		Funcionario f7 = new Funcionario();
		Funcionario f8 = new Funcionario();
		Funcionario f9 = new Funcionario();
		Funcionario f10 = new Funcionario();
		
		//Setar valores
		f1.setNome("Maria");
		f1.setDataNascimento(LocalDate.of(2000, 10, 18));
		f1.setSalario(BigDecimal.valueOf(2009.44));
		f1.setFuncao("Operador");
		
		f2.setNome("João");
		f2.setDataNascimento(LocalDate.of(1990, 05, 12));
		f2.setSalario(BigDecimal.valueOf(2284.38));
		f2.setFuncao("Operador");
		
		f3.setNome("Caio");
		f3.setDataNascimento(LocalDate.of(1961, 05, 02));
		f3.setSalario(BigDecimal.valueOf(9836.14));
		f3.setFuncao("Coordenador");
		
		f4.setNome("Miguel");
		f4.setDataNascimento(LocalDate.of(1988, 10, 14));
		f4.setSalario(BigDecimal.valueOf(19119.88));
		f4.setFuncao("Diretor");
		
		f5.setNome("Alice");
		f5.setDataNascimento(LocalDate.of(1995, 01, 05));
		f5.setSalario(BigDecimal.valueOf(2234.68));
		f5.setFuncao("Recepcionista");
		
		f6.setNome("Heitor");
		f6.setDataNascimento(LocalDate.of(1999, 11, 19));
		f6.setSalario(BigDecimal.valueOf(1582.72));
		f6.setFuncao("Operador");
		
		f7.setNome("Arthur");
		f7.setDataNascimento(LocalDate.of(1993, 03, 31));
		f7.setSalario(BigDecimal.valueOf(4071.84));
		f7.setFuncao("Contador");
		
		f8.setNome("Laura");
		f8.setDataNascimento(LocalDate.of(1994, 07, 8));
		f8.setSalario(BigDecimal.valueOf(3017.45));
		f8.setFuncao("Gerente");
		
		f9.setNome("Heloísa");
		f9.setDataNascimento(LocalDate.of(2003, 05, 24));
		f9.setSalario(BigDecimal.valueOf(1606.85));
		f9.setFuncao("Eletricista");
		
		f10.setNome("Helena");
		f10.setDataNascimento(LocalDate.of(1996, 9, 02));
		f10.setSalario(BigDecimal.valueOf(2799.93));
		f10.setFuncao("Gerente");
		
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection connection = connectionFactory.recuperarConexao();
		
		//3.1 – Inserir todos os funcionários, na mesma ordem e informações da tabela acima.
//		PreparedStatement stm = connection.prepareStatement("INSERT INTO funcionarios (nome, data_nascimento, salario, funcao) VALUES (?, ?, ?, ?)");
//		stm.setString(1, f10.getNome());
//		stm.setObject(2, f10.getDataNascimento());
//		stm.setBigDecimal(3, f10.getSalario());
//		stm.setString(4, f10.getFuncao());
//		stm.execute();
//		Integer linhasModificadas = stm.getUpdateCount();
//		System.out.println("Quantidade de linhas que foram modificadas: " + linhasModificadas);
		
		//3.2 – Remover o funcionário “João” da lista.
//		PreparedStatement stm = connection.prepareStatement("DELETE FROM funcionarios WHERE nome =  ?");
//		stm.setString(1, f2.getNome());
//		stm.execute();
//		Integer linhasModificadas = stm.getUpdateCount();
//		System.out.println("Quantidade de linhas que foram modificadas: " + linhasModificadas);
		
		//3.3 – Imprimir todos os funcionários com todas suas informações, sendo que:
		//• informação de data deve ser exibido no formato dd/mm/aaaa;
		//• informação de valor numérico deve ser exibida no formatado com separador de milhar como ponto e decimal como vírgula.
//		PreparedStatement stm = connection.prepareStatement("SELECT * FROM funcionarios;");
//		stm.execute();
//		ResultSet rst = stm.getResultSet();
//		while(rst.next()) {
//			String nome = rst.getString(1);
//			String dataNacimento = rst.getString(2);
//			BigDecimal salario = rst.getBigDecimal(3);
//			String funcao = rst.getString(4);
//			
//			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
//			Date data = (Date) formatter.parse(dataNacimento);
//			
//			System.out.println(nome);
//			System.out.println(data);
//			System.out.println(salario);
//			System.out.println(funcao);
//		}
//		rst.close();
//		stm.close();
//		connection.close();
		
		//PreparedStatement stm2 = connection.prepareStatement("UPDATE funcionarios SET salario = ? WHERE nome = ?");
		PreparedStatement stm = connection.prepareStatement("SELECT * FROM funcionarios;");
		stm.execute();
		ResultSet rst = stm.getResultSet();
		while(rst.next()) {
			String nome = rst.getString(1);
			Double salario = rst.getDouble(3);
			
			salario += salario * 0.10;
			//BigDecimal novaSalario = new BigDecimal(salario);
			
			System.out.println(nome);
			System.out.println(salario);
			System.out.printfln( "%.2f", salario);
		}
		rst.close();
		stm.close();
		connection.close();
		
		
		
//		stm.close();
//		connection.close();
	}

}
