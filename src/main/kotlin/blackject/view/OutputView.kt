package blackject.view

import blackject.model.Person
import blackject.model.card.CardNumber

object OutputView {
    fun printGivenCard(persons: List<Person>, initCardNumber: Int) {
        val names = persons.joinToString { it.name }
        println("${names}에게 ${initCardNumber}장의 나누었습니다.")
    }

    fun printCardListOfPerson(person: Person) {
        println("${person.name}카드: ${person.cards.cardList.joinToString { it.cardName }}")
    }

    fun printAddedDealerCard(maxInt: Int) {
        println("딜러는 ${maxInt}이하라 한장의 카드를 더 받았습니다.\n")
    }

    fun gameResult(person: Person, maxNumber: Int, exceptCard: CardNumber) {
        println(
            "${person.name}카드: ${person.cards.cardList.joinToString { it.cardName }} - 결과: ${
            person.cards.getResultNumber(maxNumber, exceptCard = exceptCard)}"
        )
    }
}
