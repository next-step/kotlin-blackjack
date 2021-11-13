package blackject.view

import blackject.model.Person

class OutputView {
    fun printGivenCard(persons: List<Person>, initCardNumber: Int) {
        val names = persons.joinToString { it.name }
        println("${names}에게 ${initCardNumber}장의 나누었습니다.")
    }
}
