package blackjack.common

import blackjack.domain.player.Player

class PlayerSummary(player: Player, excludeHiddenCard: Boolean = false) {
    val playerName: String = player.name

    val playerCards: List<String> = player.cards.getNames()
        .let {
            if (excludeHiddenCard && player.isFirstCardHidden)
                it.drop(1)
            else
                it
        }

    val playerCardsTotal: Int = player.cards.total.value
}
