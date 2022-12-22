package blackjack.domain

class Participant(val persons: List<Person>) {

    constructor(expression: String): this(expression.split(",").map { Person(it) })

    fun getParticipantNames() = persons.map { it.name }
}
