package com.uefs.gerenciadordenotas.dao;

import com.uefs.gerenciadordenotas.dao.aluno.AlunoDAO;
import com.uefs.gerenciadordenotas.dao.aluno.AlunoListImpl;

public class DAO {

    private static AlunoDAO alunoDAO;

    public static AlunoDAO getAluno() {
        if (alunoDAO == null) {
            alunoDAO = new AlunoListImpl();
        }
        return alunoDAO;
    }

}
