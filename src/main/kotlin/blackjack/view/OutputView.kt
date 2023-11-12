package blackjack.view

import blackjack.domain.Player
import blackjack.domain.Rank
import blackjack.domain.Symbol

object OutputView {

    private const val SEPARATOR = ", "

    fun writePlayerNames(players: List<Player>) {
        println("딜러와 ${players.joinToString(SEPARATOR) { it.name.value }}에게 2장의 나누었습니다.")
    }

    fun writePlayerCards(vararg players: Player) {
        players.forEach {
            println(
                "${it.name.value}카드: ${
                    it.cards.joinToString(SEPARATOR) { card ->
                        "${card.rank.name()}${card.symbol.name()}"
                    }
                }"
            )
        }
    }

    fun writePlayerResults(players: List<Player>) {
        players.forEach { player ->
            println(
                "${player.name.value}카드: ${
                    player.cards.joinToString(SEPARATOR) { card ->
                        "${card.rank.name()}${card.symbol.name()}"
                    }
                } - 결과: ${player.calculateScore()}"
            )
        }
    }

    private fun Rank.name(): String {
        return when (this) {
            Rank.JACK -> "J"
            Rank.QUEEN -> "Q"
            Rank.KING -> "K"
            Rank.ACE -> "A"
            else -> this.score.toString()
        }
    }

    private fun Symbol.name(): String {
        return when (this) {
            Symbol.SPADE -> "스페이드"
            Symbol.HEART -> "하트"
            Symbol.DIAMOND -> "다이아"
            Symbol.CLUB -> "클로버"
        }
    }
}
