// Desafio de Projeto DIO (Lab) - Bootcamp TQI Backend com Kotlin
// Refatoração do template no Kotlin Playground](https://pl.kotl.in/WcteahpyN)
// Desenvolvido por:  Argemiro Leite - @Aiplbh em Setembro/2023

enum class Nivel { BASICO, INTERMEDIARIO, AVANCADO }

data class Usuario(
    val nome: String,
    val codigo: Int,
    val pontuacaoDIO: Int,
    val email: String
)

data class ConteudoEducacional(val nome: String, val duracao: Int, val nivel: Nivel)

data class Formacao(val nome: String, var conteudos: Map<String, ConteudoEducacional>) {

    val inscritos = mutableSetOf<Usuario>()
    
    fun matricular(usuario: Usuario) {
        if (!inscritos.contains(usuario)) {
            inscritos.add(usuario)
        }
    }
}

fun main() {

    // Criar alguns conteúdos educacionais
    val conteudo1 = ConteudoEducacional("Introdução à Programação", 120, Nivel.BASICO)
    val conteudo2 = ConteudoEducacional("Estruturas de Dados", 90, Nivel.INTERMEDIARIO)
    val conteudo3 = ConteudoEducacional("Algoritmos Avançados", 150, Nivel.AVANCADO)
    
    // Criar uma formação
    val formacao = Formacao("Front End Developer", mapOf(
        conteudo1.nome to conteudo1,
        conteudo2.nome to conteudo2,
        conteudo3.nome to conteudo3
    ))
    
    // Criar alguns usuários
    val usuario1 = Usuario("João", 1, 8954, "joao@example.com")
    val usuario2 = Usuario("Maria", 2, 11584, "maria@example.com")
    
    // Matricular os usuários na formação
    formacao.matricular(usuario1)
    formacao.matricular(usuario2)

    // Listar os inscritos na formação
    println("Inscritos na formação: ${formacao.nome}")
    for (usuario in formacao.inscritos) {
        println(usuario)
    }
}
