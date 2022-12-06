package blackjack.domain

import blackjack.domain.util.Parser

data class People(
    private val nameStr: String,
    val people: List<Person> = Parser.parse(nameStr).map { name -> Person(name) }
) : List<Person> by people {

    override fun toString(): String {
        return people.joinToString(DELIMITER) { "$it - ${it.getScore()}" }
    }

    companion object {
        private const val DELIMITER = "\n"
    }
}
