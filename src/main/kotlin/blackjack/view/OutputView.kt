package blackjack.view

import blackjack.People
import blackjack.Person

object OutputView {
    fun printInitialState(people: People) {
        println("${people.joinToString(", ") { it.name }}에게 ${people.size}장을 나누어 주었습니다.")
        people.people.map { println(it) }
    }

    fun printCardState(person: Person) {
        println(person)
    }

    fun printResult(people: People) {
        println(people)
    }
}
