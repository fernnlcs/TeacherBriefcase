package src.model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import src.model.VO.QuestaoVO;

public class QuestaoDAO extends BaseDAO<QuestaoVO> implements QuestaoInterDAO {

    @Override
    public void cadastrar(QuestaoVO vo) {
        String sql = "insert into questao (enunciado, dificuldade, data_criacao) values (?, ?, ?)";
        PreparedStatement statement;

        try {
            statement = getConnection().prepareStatement(sql);
            statement.setString(1, vo.getEnunciado());
            statement.setInt(2, vo.getDificuldade());
            statement.setTimestamp(3, new Timestamp(vo.getDataCriacao().getTimeInMillis()));

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0)
                throw new SQLException("Não foi possível realizar este cadastro");

            ResultSet generatedKeys = statement.getGeneratedKeys();

            if (generatedKeys.next())
                vo.setId(generatedKeys.getLong("id"));

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public ResultSet listar() {
        String sql = "select * from questao";
        Statement statement;
        ResultSet result = null;

        try {
            statement = getConnection().createStatement();
            result = statement.executeQuery(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public ResultSet buscar(QuestaoVO vo) {
        String sql = "select * from questao where id = ?";
        PreparedStatement statement;
        ResultSet result = null;

        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, vo.getId());
            result = statement.executeQuery();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public ResultSet buscarPorDificuldade(QuestaoVO vo) {
        String sql = "select * from questao where dificuldade = ?";
        PreparedStatement statement;
        ResultSet result = null;

        try {
            statement = getConnection().prepareStatement(sql);
            statement.setInt(1, vo.getDificuldade());
            result = statement.executeQuery();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public void editar(QuestaoVO vo) {
        String sql = "update questao set enunciado = ?, dificuldade = ?, data_criacao = ? where id = ?";
        PreparedStatement statement;

        try {
            statement = getConnection().prepareStatement(sql);
            statement.setString(1, vo.getEnunciado());
            statement.setInt(2, vo.getDificuldade());
            statement.setTimestamp(3, new Timestamp(vo.getDataCriacao().getTimeInMillis()));
            statement.setLong(4, vo.getId());

            if (statement.executeUpdate() == 0)
                throw new SQLException("Não foi possível realizar esta atualização.");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    public void excluir(QuestaoVO vo) {
        String sql = "delete from questao where id = ?";
        PreparedStatement statement;

        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, vo.getId());

            if (statement.executeUpdate() == 0)
                throw new SQLException("Não foi possível realizar esta exclusão.");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    
}
