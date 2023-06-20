package br.fzlbpms.Test.model.Tarefa

import java.util.Date

class Tarefa {
    class Tarefa(val nome: String, val detalhes: String?, val createDate: Date, val pzoFinal: Date){

        var status = 0.0
            get() {
                return field
            }
            set(value) {
                field = value
            }
    }

}
