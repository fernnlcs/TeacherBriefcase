package src.model.BO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import src.model.VO.DisciplinaVO;
import src.model.VO.ProfessorVO;
import src.model.VO.ProvaVO;
import src.model.VO.QuestaoVO;

public class DisciplinaBO implements DisciplinaInterBO {
    public void cadastrar(DisciplinaVO disciplina) {
        // cadastra uma nova disciplina

        // analisa
        // TODO DAO
    }

    public List<DisciplinaVO> listar() {
        // lista todas as disciplinas

        List<DisciplinaVO> lista = new ArrayList<DisciplinaVO>();
        // TODO DAO
        // ajusta
        return lista;
    }

    public DisciplinaVO buscar(DisciplinaVO disciplina) {
        // busca uma disciplina

        DisciplinaVO resultado = new DisciplinaVO();
        // TODO DAO
        // ajusta
        return resultado;
    }

    @Override
    public List<DisciplinaVO> buscar(ProfessorVO professor) {
        // TODO Auto-generated method stub
        return null;
    }

    public void atualizar(DisciplinaVO disciplina) {
        // edita os dados de uma disciplina

        // analisa
        // TODO DAO
        // ajusta
    }

    public void excluir(DisciplinaVO disciplina) {
        // exclui uma disciplina

        // analisa
        // TODO DAO
    }

    public void adicionar(DisciplinaVO disciplina, String assunto) {

        List<String> lista = disciplina.getAssuntos();

        // Se este assunto não estiver na lista desta disciplina, ele será adicionado
        if (!lista.contains(assunto)) {
            lista.add(assunto);
            disciplina.setAssuntos(lista);

            // TODO DAO
        }
    }

    public void remover(DisciplinaVO disciplina, String assunto) {

        List<String> lista = disciplina.getAssuntos();

        // Se este assunto estiver na lista desta disciplina, ele será removido
        if (lista.remove(assunto)) {
            disciplina.setAssuntos(lista);

            // Atualizar as questões (remover este assunto da lista de cada uma)
            List<QuestaoVO> questoes = disciplina.getQuestoes();

            Iterator<QuestaoVO> questoesIt = questoes.iterator();
            while (questoesIt.hasNext()) {
                QuestaoBO questaoBO = new QuestaoBO();
                questaoBO.remover(questoesIt.next(), assunto);
            }

            // TODO DAO
        }
    }

    public void adicionar(DisciplinaVO disciplina, ProfessorVO professor) {

        List<ProfessorVO> lista = disciplina.getProfessores();

        // Se este professor não estiver na lista desta disciplina, será adicionado
        if (!lista.contains(professor)) {
            lista.add(professor);
            disciplina.setProfessores(lista);

            // atualiza o professor
            ProfessorBO professorBO = new ProfessorBO();
            professorBO.adicionar(professor, disciplina);

            // TODO DAO
        }
    }

    public void remover(DisciplinaVO disciplina, ProfessorVO professor) {

        List<ProfessorVO> lista = disciplina.getProfessores();

        // Se este professor estiver na lista desta disciplina, será removido
        if (lista.remove(professor)) {
            disciplina.setProfessores(lista);

            // atualiza o professor
            ProfessorBO professorBO = new ProfessorBO();
            professorBO.remover(professor, disciplina);

            // TODO DAO
        }
    }

    public void adicionar(DisciplinaVO disciplina, QuestaoVO questao) {

        List<QuestaoVO> lista = disciplina.getQuestoes();

        // Se esta questão não estiver na lista desta disciplina, será adicionada
        if (!lista.contains(questao)) {
            lista.add(questao);
            disciplina.setQuestoes(lista);

            // Atualiza a questão
            questao.setDisciplina(disciplina);
        }

    }

    public void remover(DisciplinaVO disciplina, QuestaoVO questao) {

        List<QuestaoVO> lista = disciplina.getQuestoes();

        // Se esta questão estiver na lista desta disciplina, será removida
        if (lista.remove(questao)) {
            disciplina.setQuestoes(lista);

            // Atualiza a questão
            questao.setDisciplina(null);

            // TODO DAO
        }
    }

    public void adicionar(DisciplinaVO disciplina, ProvaVO prova) {

        List<ProvaVO> lista = disciplina.getProvas();

        // Se esta prova não estiver na lista desta disciplina, será adicionada
        if (!lista.contains(prova)) {
            lista.add(prova);
            disciplina.setProvas(lista);

            // Atualizar a prova
            prova.setDisciplina(disciplina);

            // TODO DAO
        }
    }

    public void remover(DisciplinaVO disciplina, ProvaVO prova) {

        List<ProvaVO> lista = disciplina.getProvas();

        // Se esta prova estiver na lista desta disciplina, será removida
        if (lista.remove(prova)) {
            disciplina.setProvas(lista);

            // Atualizar a prova
            prova.setDisciplina(null);

            // TODO DAO
        }
    }
}
