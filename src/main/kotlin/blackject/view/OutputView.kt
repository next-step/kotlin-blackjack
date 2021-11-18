package blackject.view

import blackject.model.Person
import blackject.model.card.CardNumber

object OutputView {
    fun printGivenCard(persons: List<Person>, initCardNumber: Int) {
        val names = persons.joinToString { it.name }
        println("${names}에게 ${initCardNumber}장의 나누었습니다.")
        println()
    }

    fun printCardListOfPerson(person: Person) {
        println("${person.name}카드: ${person.cards.cardList.joinToString { it.cardName }}")
        println()
    }

    fun gameResult(person: Person, maxNumber: Int) {
        println(
            "${person.name}카드: ${person.cards.cardList.joinToString { it.cardName }} - 결과: ${
            person.cards.getResultNumber(maxNumber, exceptCard = CardNumber.ACE)}"
        )
    }
}
