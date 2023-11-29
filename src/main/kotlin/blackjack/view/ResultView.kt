package blackjack.view

import blackjack.Participant
import blackjack.view.ResultType.*

class ResultView {
    fun printNames(names: List<String>) {
        println(GAME_INIT.message.format(names.joinToString(", ")))
    }

    fun printCards(participants: List<Participant>) {
        participants.forEach {
            println(CARDS.message.format(it.name, it.cards.joinToString(", ")))
        }
    }

    fun printResult(participants: List<Participant>) {
        println()
        participants.forEach {
            println(RESULT.message.format(it.name, it.cards.joinToString(", "), it.score))
        }
    }
}
