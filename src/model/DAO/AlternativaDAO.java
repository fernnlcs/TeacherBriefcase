package src.model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import src.model.VO.AlternativaVO;

public class AlternativaDAO extends BaseDAO implements AlternativaInterDAO {

    public final String tabela = "alternativa";

    @Override
    public void cadastrar(AlternativaVO vo) {
        String sql = "insert into " + tabela + " (questao, texto, verdadeira) values (?, ?, ?)";
        PreparedStatement statement;

        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, vo.getQuestao().getId());
            statement.setString(2, vo.getTexto());
            statement.setBoolean(3, vo.isVerdadeira());

            if (statement.executeUpdate() == 0)
                throw new SQLException("Não foi possível realizar este cadastro.");

            ResultSet generatedKeys = statement.getGeneratedKeys();

            if (generatedKeys.next())
                vo.setId(generatedKeys.getLong("id"));

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public ResultSet listar() {
        String sql = "select * from " + tabela;
        Statement statement;
        ResultSet result = null;

        try {
            statement = getConnection().createStatement();
            result = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public ResultSet buscar(AlternativaVO vo) {
        String sql = "select * from " + tabela + " where id = ?";
        PreparedStatement statement;
        ResultSet result = null;

        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, vo.getId());
            result = statement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		};

        return result;
    }

    @Override
    public ResultSet buscarPorQuestao(AlternativaVO vo) {
        String sql = "select * from " + tabela + " where questao = ?";
        PreparedStatement statement;
        ResultSet result = null;

        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, vo.getQuestao().getId());
            result = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public void atualizar(AlternativaVO vo) {
        String sql = "update " + tabela + " set texto = ?, verdadeira = ? where id = ?";
        PreparedStatement statement;

        try {
            statement = getConnection().prepareStatement(sql);
            statement.setString(1, vo.getTexto());
            statement.setBoolean(2, vo.isVerdadeira());
            statement.setLong(3, vo.getId());

            if (statement.executeUpdate() == 0)
                throw new SQLException("Não foi possível realizar a atualização.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void excluir(AlternativaVO vo) {
        String sql = "delete from " + tabela + " where id = ?";
        PreparedStatement statement;

        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, vo.getId());

            if (statement.executeUpdate() == 0)
                throw new SQLException("Não foi possível realizar a exclusão.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
