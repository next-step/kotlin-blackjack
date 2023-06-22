package blackjack.view.output

import blackjack.domain.Player

class PlayerResultOutputView(player: Player) {
    init {
        with(player) {
            val message = "${name.value}카드: "
            val cardsStr = cards.value.map { card -> card.denom.symbol + card.type.korName }.joinToString(SEPARATOR)
            println(message + cardsStr + " - 결과: ${player.score}")
        }
    }

    companion object {
        const val SEPARATOR = ", "
    }
}
