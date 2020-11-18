package model.BO;

import model.VO.AlternativaVO;
import model.VO.QuestaoComAlternativasVO;

public class QuestaoComAlternativasBO extends QuestaoBO {

    public void adicionar(QuestaoComAlternativasVO questao, AlternativaVO alternativa) {
        // adiciona uma alternativa à questão

        AlternativaVO[] lista = questao.getAlternativas();

        // verifica se já existe uma alternativa com o mesmo texto na questão
        for (int i = 0; i < lista.length; i++)
            if (lista[i].getTexto().equals(alternativa.getTexto()))
                return;
        // O método não continua, pois já existe uma alternativa de mesmo texto

        // incrementa a lista
        lista[lista.length] = alternativa;

        // atualiza a questão
        questao.setAlternativas(lista);

        // atualiza a alternativa
        alternativa.setQuestao(questao);

        // analisa
        // DAO
        // ajusta
    }

    public void remover(QuestaoComAlternativasVO questao, AlternativaVO alternativa) {
        // remove a alternativa da questão

        AlternativaVO[] lista = questao.getAlternativas();

        // verifica se realmente existe uma alternativa com o mesmo texto na questão
        for (int i = 0; i < lista.length; i++)
            if (lista[i].getTexto().equals(alternativa.getTexto())) {
                // atualiza a questão
                    // CÓDIGO QUE REMOVE A ALTERNATIVA DO ARRAY

                // atualiza a alternativa (exclui, pois ela depende do vínculo com a questão)
                AlternativaBO alternativaBO = new AlternativaBO();
                alternativaBO.excluir(alternativa);

                return;
            }
            
        // analisa
        // DAO
        // ajusta
    }
}
