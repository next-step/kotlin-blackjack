package blackject.view

import blackject.model.Person

object OutputView {
    fun printGivenCard(persons: List<Person>, initCardNumber: Int) {
        val names = persons.joinToString { it.name }
        println("${names}에게 ${initCardNumber}장의 나누었습니다.")
    }

    fun printCardListOfPerson(person: Person) {
        println("${person.name}카드: ${person.cardList.joinToString { it.cardName }}")
    }

    fun gameResult(person: Person, maxNumber: Int) {
        println("${person.name}카드: ${person.cardList.joinToString { it.cardName }} - 결과: ${person.getResultNumber(maxNumber)}")
    }
}
