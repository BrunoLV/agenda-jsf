package br.com.valhala.agenda.web.converter;

import javax.faces.convert.EnumConverter;
import javax.faces.convert.FacesConverter;

import br.com.valhala.agenda.modelo.enums.EnumTipoTelefone;

@FacesConverter(value = "enumTipoTelefoneConverter")
public class EnumTipoTelefoneConverter extends EnumConverter {

    public EnumTipoTelefoneConverter() {
        super(EnumTipoTelefone.class);
    }

}
