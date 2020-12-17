package src.model.BO;

import java.util.List;

import src.exception.AuthenticationException;
import src.exception.OperationException;
import src.model.VO.DisciplinaVO;
import src.model.VO.ProfessorVO;
import src.model.VO.QuestaoVO;

public interface DisciplinaInterBO extends BaseInterBO<DisciplinaVO> {

    public List<DisciplinaVO> buscar(ProfessorVO professor) throws AuthenticationException, OperationException;    

    public DisciplinaVO buscarPorCodigo(DisciplinaVO disciplina);

    public List<DisciplinaVO> buscarPorNome(DisciplinaVO disciplina);

    public void adicionar(DisciplinaVO disciplina, String assunto) throws OperationException;

    public void remover(DisciplinaVO disciplina, String assunto) throws OperationException;

    public void adicionar(DisciplinaVO disciplina, QuestaoVO questao) throws OperationException;

}
