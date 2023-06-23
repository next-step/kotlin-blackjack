package blackjack.view.output

import blackjack.domain.player.Player

class PlayerResultOutputView(player: Player) {
    init {
        with(player) {
            val message = "${name.value}카드: "
            val cardsStr = cards.value.map { card -> card.denom.symbol + card.type.korName }.joinToString(SEPARATOR)
            println(message + cardsStr + " - 결과: ${player.cards.getOptimizedScore()}")
        }
    }

    companion object {
        const val SEPARATOR = ", "
    }
}
