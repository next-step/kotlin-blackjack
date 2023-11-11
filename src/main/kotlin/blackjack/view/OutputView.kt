package blackjack.view

import blackjack.domain.Player
import blackjack.domain.Symbol

object OutputView {

    fun writePlayerNames(players: List<Player>) {
        println("딜러와 ${players.joinToString(", ") { it.name.value }}에게 2장의 나누었습니다.")
    }

    fun writePlayerCards(vararg players: Player) {
        players.forEach {
            println(
                "${it.name.value}카드: ${
                    it.cards.joinToString(", ") { card ->
                        "${card.rank.score}${getCardSymbolName(card.symbol)}"
                    }
                }"
            )
        }
    }

    private fun getCardSymbolName(symbol: Symbol): String {
        return when (symbol) {
            Symbol.SPADE -> "스페이드"
            Symbol.HEART -> "하트"
            Symbol.DIAMOND -> "다이아"
            Symbol.CLUB -> "클로버"
        }
    }
}
