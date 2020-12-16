package src.model.BO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import src.exception.AuthenticationException;
import src.exception.OperationException;
import src.model.DAO.ProfessorDAO;
import src.model.VO.DisciplinaVO;
import src.model.VO.ProfessorVO;

public class ProfessorBO implements ProfessorInterBO {

    ProfessorDAO professorDAO = new ProfessorDAO();

    @Override
    public void cadastrar(ProfessorVO professor) {
        // Cadastra o professor
        professorDAO.cadastrar(professor);
    }

    @Override
    public List<ProfessorVO> listar() throws AuthenticationException, OperationException {

        List<ProfessorVO> lista = new ArrayList<ProfessorVO>();

        ResultSet consulta = professorDAO.listar();

        try {
            if (consulta != null)
                while (consulta.next()) {
                    ProfessorVO atual = new ProfessorVO();
                    atual.setId(consulta.getLong("id"));
                    atual = buscar(atual);
                    lista.add(atual);
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public ProfessorVO buscar(ProfessorVO professor) throws AuthenticationException, OperationException {

        ResultSet consulta = professorDAO.buscar(professor);

        try {
            if (consulta != null && consulta.next()) {
                professor.setNome(consulta.getString("nome"));
                professor.setEmail(consulta.getString("email"));
                professor.setSenha(consulta.getString("senha"));
                Calendar criacao = Calendar.getInstance();
                criacao.setTime(consulta.getDate("data_criacao"));
                professor.setDataCriacao(criacao);

                DisciplinaBO disciplinaBO = new DisciplinaBO();
                professor.setDisciplinas(disciplinaBO.buscar(professor));
            } else {
                throw new SQLException("A busca não retornou nenhum resultado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return professor;
    }

    @Override
    public List<ProfessorVO> buscar(DisciplinaVO disciplina) throws AuthenticationException, OperationException {
        List<ProfessorVO> lista = new ArrayList<ProfessorVO>();

        ResultSet busca = professorDAO.buscar(disciplina);

        try {
            if (busca != null)
                while (busca.next()) {
                    ProfessorVO atual = new ProfessorVO();
                    atual.setId(busca.getLong("id"));
                    atual = buscar(atual);
                    lista.add(atual);
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public ProfessorVO buscarPorEmail(ProfessorVO professor) throws AuthenticationException {

        ResultSet busca = professorDAO.buscarPorEmail(professor);

        try {
            if (busca != null && busca.next()) {
                professor.setNome(busca.getString("nome"));
                professor.setId(busca.getLong("id"));
                professor.setSenha(busca.getString("senha"));
                Calendar criacao = Calendar.getInstance();
                criacao.setTime(busca.getDate("data_criacao"));
                professor.setDataCriacao(criacao);
            } else {
                throw new SQLException("A busca não retornou nenhum resultado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return professor;
    }

    @Override
    public void atualizar(ProfessorVO professor) {
        professorDAO.atualizar(professor);
    }

    @Override
    public void excluir(ProfessorVO professor) {
        professorDAO.excluir(professor);
    }

    @Override
    public ProfessorVO autenticar(ProfessorVO professor) throws AuthenticationException {
        // Verifica se o e-mail e a senha do Professor correspondem ao BD

        ProfessorVO busca = new ProfessorVO(professor.getEmail());
        busca = buscarPorEmail(busca);

        if (busca.getSenha().equals(professor.getSenha()))
            return busca;
        else
            throw new AuthenticationException();
    }

    @Override
    public void adicionar(ProfessorVO professor, DisciplinaVO disciplina) {
        List<DisciplinaVO> lista = professor.getDisciplinas();

        // Se esta disciplina não estiver na lista deste professor, será adicionada
        if (!lista.contains(disciplina)) {
            lista.add(disciplina);
            professor.setDisciplinas(lista);

            // atualiza a disciplina
            DisciplinaBO disciplinaBO = new DisciplinaBO();
            disciplinaBO.adicionar(disciplina, professor);

            // Se a relação ainda NÃO estiver adicionada no BD (pode ocorrer de estar)
            if (!disciplinaBO.buscar(professor).contains(disciplina))
                professorDAO.adicionar(professor, disciplina);
        }
    }

    @Override
    public void remover(ProfessorVO professor, DisciplinaVO disciplina) {
        List<DisciplinaVO> lista = professor.getDisciplinas();

        // Se esta disciplina estiver na lista deste professor, será removida
        if (lista.remove(disciplina)) {
            professor.setDisciplinas(lista);

            // atualiza a disciplina
            DisciplinaBO disciplinaBO = new DisciplinaBO();
            disciplinaBO.remover(disciplina, professor);

            // Se a disciplina ainda REALMENTE estiver adicionada no BD (pode ocorrer de não
            // estar)
            if (disciplinaBO.buscar(professor).contains(disciplina))
                professorDAO.remover(professor, disciplina);
        }
    }
}
