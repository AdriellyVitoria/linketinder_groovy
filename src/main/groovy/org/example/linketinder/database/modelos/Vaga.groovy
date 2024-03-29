package org.example.linketinder.database.modelos

class Vaga {
    Integer id
    String descricao
    String titulo
    String local
    String cnpj_empresa
    ArrayList<Competencia> competencias

    Vaga() {}

    Vaga(Integer id,
         String descricao,
         String titulo,
         String local
    ) {
        this.id = id
        this.descricao = descricao
        this.titulo = titulo
        this.local = local
    }
}
