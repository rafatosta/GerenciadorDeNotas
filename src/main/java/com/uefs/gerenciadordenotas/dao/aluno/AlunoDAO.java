package com.uefs.gerenciadordenotas.dao.aluno;


import com.uefs.gerenciadordenotas.dao.CRUD;
import com.uefs.gerenciadordenotas.model.Aluno;

import java.util.List;

public interface AlunoDAO extends CRUD<Aluno> {
    public List<Aluno> findByName(String name);

}
