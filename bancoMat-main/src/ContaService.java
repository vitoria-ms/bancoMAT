import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ContaService{
    // este método insere uma conta no banco de dados
  public static void inserir(Conta conta) throws SQLException {
    // estabelece uma conexão com o banco de dados
    
    try (Connection conn = App.getConexao();){
      String sql = "INSERT INTO conta (numero, cliente, saldo) VALUES (?,?,?)"; //consulta SQL a ser executada no banco de dado.

      PreparedStatement ps = conn.prepareStatement(sql); // Cria um objeto Statement parametrizado e passa a string sql a ser executada
      ps.setString(1, conta.getNumero()); // carrega o primeiro parametro
      ps.setString(2, conta.getCliente().getNome());  // carrega o segundo parametro
      ps.setDouble(3, conta.getSaldo());  // carrega o terceiro parametro
      int res = ps.executeUpdate();  //  executa a consulta SQL e retorna um valor inteiro
      if (res == 1) {
        System.out.println("Conta inserida com sucesso.");
      }
    } catch (SQLException ex) {
      System.out.println("Não conseguiu adicionar uma conta no BD.");
    }
  }
  
  // este método altera uma conta no banco de dados
  public static void atualizar(Conta conta, String numero) throws SQLException {
    // estabelece uma conexão com o banco de dados
    
    try (Connection conn = App.getConexao();){
      //consulta SQL a ser executada no banco de dados
      String sql = "UPDATE conta SET numero = ?, cliente = ?, saldo = ? WHERE numero = ?";

      // Cria um objeto Statement parametrizado e passa a string sql a ser executada
      PreparedStatement ps = conn.prepareStatement(sql);
      ps.setString(1, conta.getNumero());  // carrega o primeiro parametro
      ps.setString(2, conta.getCliente().getNome());  // carrega o segundo parametro
      ps.setDouble(3, conta.getSaldo());  // carrega o terceiro parametro
      ps.setString(4, numero);  // carrega o quarto parametro
      int res = ps.executeUpdate();  //  executa a consulta SQL e retorna um valor inteiro

      if (res == 1) {
        System.out.println("Conta atualizada com sucesso.");
      }
      System.out.println("Atualizou o cliente da conta.");
    } catch (SQLException ex) {
      System.out.println("Não conseguiu atualizar uma conta no BD.");
    } 
  }

  // este método exclui uma conta no banco de dados
  public static void deletar(Conta conta) throws SQLException {
    // estabelece uma conexão com o banco de dados
    
    try (Connection conn = App.getConexao();){
      String atualizar = "DELETE FROM conta WHERE numero = ?"; //consulta SQL a ser executada no banco de dados

      // Cria um objeto Statement parametrizado e passa a string sql a ser executada
      PreparedStatement ps = conn.prepareStatement(atualizar);
      ps.setString(1, conta.getNumero());  // carrega o primeiro parametro
      int res = ps.executeUpdate(); //  executa a consulta SQL e retorna um valor inteiro

      if (res == 1) {
        System.out.println("Conta excluída com sucesso.");
      }

    } catch (SQLException ex) {
      System.out.println("Não conseguiu excluir uma conta no BD.");
    } 
  }
}