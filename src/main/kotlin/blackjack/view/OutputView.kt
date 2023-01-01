package blackjack.view

import blackjack.domain.Draw
import blackjack.domain.Participant
import blackjack.domain.Person

object OutputView {

    fun printParticipantInfo(participant: Participant) {
        println()
        val participantNames = participant.getParticipantNames().joinToString(",")
        println("${participantNames}에게 ${Draw.FIRST_DRAW_COUNT}장의 카드를 나누었습니다.")
        participant.persons.forEach {
            printOwnCards(it)
            println()
        }
    }

    fun printOwnCards(person: Person) {
        print("${person.name}카드: ${person.ownCards.cards.joinToString(", ") { it.cardNumber.display + it.pattern.display }}")
    }

    fun printResult(participant: Participant) {
        println()
        println()
        participant.persons.forEach {
            printOwnCards(it)
            println(" - 결과: ${it.ownCards.sumCardNumber()}")
        }
    }
}
