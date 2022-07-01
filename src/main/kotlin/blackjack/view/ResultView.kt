package blackjack.view

import blackjack.BlackjackBettingDto
import blackjack.domain.BlackjackBetting

class ResultView(private val players: List<BlackjackBettingDto>) {

    fun result() {
        players.forEach {
            println("%s카드: %s - 결과: %s".format(it.name, it.cards.joinToString(SEPARATOR_CUSTOM) { card -> card.toString() }, it.score))
        }

        println("최종 수익")
        players.forEach {
            println("%s : %s".format(it.name, it.money))
        }
    }

    companion object {
        private const val SEPARATOR_CUSTOM = ", "
    }
}
