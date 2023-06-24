package blackjack.view.output

import blackjack.domain.player.Player

class PlayerOutputView(player: Player) {
    init {
        with(player) {
            val message = "${name.value}카드: "
            val cardsStr =
                cards.getValue().map { card -> card.denom.symbol + card.type.korName }.joinToString(SEPARATOR)
            println(message + cardsStr)
        }
    }

    companion object {
        const val SEPARATOR = ", "
    }
}
