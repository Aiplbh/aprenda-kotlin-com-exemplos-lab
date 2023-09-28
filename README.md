# Aprenda Kotlin Com Exemplos: Desafio de Projeto (Lab)

Desafio de Projeto criado para avaliação do conteúdo técnico explorado no repositório [aprenda-kotlin-com-exemplos](https://github.com/digitalinnovationone/aprenda-kotlin-com-exemplos). 

Nesse contexto, iremos abstrair o seguinte domínio de aplicação:

**A [DIO](https://web.dio.me) possui `Formacoes` incríveis que têm como objetivo oferecer um conjunto de `ConteudosEducacionais` voltados para uma stack tecnológica específica, preparando profissionais de TI para o mercado de trabalho. `Formacoes` possuem algumas características importantes, como `nome`, `nivel` e seus respectivos `conteudosEducacionais`. Além disso, tais experiências educacionais têm um comportamento relevante ao nosso domínio, definido pela capacidade de `matricular` um ou mais `Alunos`.**

## Introdução

Nesse desafio foi proposto implementar melhoria em um modelo de domínio da DIO referente às suas Formações. As seguintes melhorias foram implementadas  a partir das sugestões / orientações do Instrutor Venilton Falvo Jr @falvojr.

- Alterar a classe *Nível* acrescentando ou modificando os níveis existentes.
- Transformar a classe *Usuario* numa Data Class
- Alterar o atributo nome da data class *ConteudoEducacional* de var para val
- A class Nível não está sendo usada. Escolher o melhor local para implementá-la: ou classe Formação ou na classe ConteúdoEducacional

### Modelo Conceitual

Foi elaborado o seguinte Modelo Conceitual a partir da descrição do modelo de domínio proposto:

![MER](https://i.imgur.com/gGCy9lfl.png)

### Modelo Lógico

Com base no Modelo Conceitual foi elaborado o Modelo Lógico `Simplificado` abaixo:

![Diagrama de classes](https://i.imgur.com/uGQtSVal.png)

### Código final refatorado

```kotlin
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
```
***Saída do código***

```
Inscritos na formação: Front End Developer
Usuario(nome=João, codigo=1, pontuacaoDIO=8954, email=joao@example.com)
Usuario(nome=Maria, codigo=2, pontuacaoDIO=11584, email=maria@example.com)
```

O código acima foi testado alterando-se o template do [Kotlin Playgroun](https://pl.kotl.in/WcteahpyN)

### Melhoria implementadas

1. Classe Nivel (enum):
- A classe Nivel foi alterada para os níveis BASICO, INTERMEDIARIO, AVANCADO e está sendo usada na classe *ConteudoEducacional*

2. Classe Usuario:
- A Classe Usuário foi refatorada para ser uma Data Class e foram criados os seguintes atributos: nome, codigo, pontuacaoDIO e email.

3. Classe ConteudoEducacional:
- A classe ConteudoEducacional agora possui  o nome, uma duração ou carga horária e o nível do conteúdo implementado como uma instância da classe Nivel do tipo Enum.  

4. Classe Formacao:
- A classe Formacao foi otimizada para usar um Map de conteúdos educacionais em vez de uma lista, permitindo a consulta pelo nome de usuário, por exemplo.

- A lista de inscritos foi alterada para um mutableSetOf, o que garante que cada usuário seja `único` na lista de inscritos ou matriculados.

- O método matricular agora verifica se o usuário já está inscrito antes de adicioná-lo, evitando duplicatas na lista.

5. Função main:
- No cenário de teste na função main, foram criados três conteúdos educacionais e uma formação com base em um Map, associando os conteúdos a uma formação.

- Foram matriculados dois usuários fictícios na formação, garantindo que não haja duplicatas na lista de inscritos. 

- Foram impressos todos os usuários inscritos na formação `Front End Developer` criada para teste.

⚠️ Outras funcionalidades, como a capacidade de remover inscritos, calcular a duração total da formação com base nos conteúdos, getters e setters são algumas das possíveis melhorias a serem implementadas.

## Conclusão

Através desse Desafio de Projeto, foi possível aplicar diversos conteúdos aprendidos durante o Módulo de Introdução ao Kotlin do Bootcamp da TQI. O `Modelo de Domínio` proposto, embora simples, possibilitou a utilização de diversos tipos e estruturas de dados aprendidos e o uso correto dos mesmos, consolidando na prática os fundamentos da linguagem.
