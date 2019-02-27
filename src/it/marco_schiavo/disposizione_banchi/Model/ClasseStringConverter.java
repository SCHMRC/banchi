package it.marco_schiavo.disposizione_banchi.Model;

import javafx.util.StringConverter;

public class ClasseStringConverter extends StringConverter<Classe> {

	@Override
	public String toString(Classe object) {
		return object.getNome();
	}

	@Override
	public Classe fromString(String string) {
		return null;
	}

}
