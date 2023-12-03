package blackjack.view

import blackjack.domain.Participant
import blackjack.view.ResultType.*

class ResultView {
    fun printNames(names: List<String>) {
        println(GAME_INIT.message.format(names.joinToString(", ")))
    }

    fun printCards(participants: List<Participant>) {
        participants.forEach {
            println(PARTICIPANT_CARDS.message.format(it.name, it.cards.joinToString(", ")))
        }
    }

    fun printResult(participants: List<Participant>) {
        println()
        participants.forEach {
            println(PARTICIPANT_RESULT.message.format(it.name, it.cards.joinToString(", "), it.getScore()))
        }
    }
}
