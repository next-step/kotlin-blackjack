package blackjack

import blackjack.domain.player.Player

class PlayerSummary(player: Player) {
    val playerName: String = player.name

    val playerCards: List<String> = player.cards.getNames()

    val playerCardsTotal: Int = player.cards.total.value
}
