package blackjack.domain.player

import blackjack.domain.card.Deck

class Participants(
    val players: List<PlayerImpl>,
    val dealer: Dealer
) {
    val allPlayers: List<Player>
        get() = listOf(dealer) + players

    fun drawAll(deck: Deck) {
        dealer.drawCard(deck)
        players.forEach { it.drawCard(deck) }
    }
}
