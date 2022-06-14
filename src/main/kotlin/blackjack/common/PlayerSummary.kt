package blackjack.common

import blackjack.domain.player.Player

class PlayerSummary(player: Player) {
    val playerName: String = player.name

    val playerCards: List<String> = player.cards.list.map { CardName.of(it) }

    val playerCardsTotal: Int = player.cards.total.value
}
