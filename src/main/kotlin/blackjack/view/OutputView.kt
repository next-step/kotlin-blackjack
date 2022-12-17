package blackjack.view

import blackjack.domain.Person

object OutputView {
    private const val DELIMITER = "\n"

    fun printInitialState(people: List<Person>) {
        println("${people.joinToString(", ") { it.name }}에게 ${people.size}장을 나누어 주었습니다.")
        people.map { println(printPerson(it)) }
    }

    fun printCardState(person: Person) {
        println(printPerson(person))
    }

    fun printResult(people: List<Person>) {
        println(people.joinToString(DELIMITER) { "${printPerson(it)} - ${it.getScore()}" })
    }

    private fun printPerson(person: Person): String {
        return "${person.name}카드: ${person.cards}"
    }
}
