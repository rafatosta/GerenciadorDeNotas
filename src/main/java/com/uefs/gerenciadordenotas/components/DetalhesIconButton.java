package com.uefs.gerenciadordenotas.components;


import com.uefs.gerenciadordenotas.controller.MyController;
import com.uefs.gerenciadordenotas.model.Aluno;

public class DetalhesIconButton extends CustomIconButton {

	private MyController controller;

	public DetalhesIconButton(MyController controller) {
		super("Localizar.png");
		this.controller = controller;
	}

	@Override
	public void actionRun(Object item) {
		Aluno a = (Aluno) item;
		this.controller.consultar(a);
	}

}
