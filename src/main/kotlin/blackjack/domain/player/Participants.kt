package blackjack.domain.player

import blackjack.domain.card.Deck

class Participants(
    val players: List<GamePlayer>,
    val dealer: Dealer
) {
    val allGameMembers: List<GameMember>
        get() = listOf(dealer) + players

    fun drawAll(deck: Deck) {
        dealer.drawCard(deck)
        players.forEach { it.drawCard(deck) }
    }

    fun finishGame() {
        players.forEach { player ->
            dealer.compareScore(player)
        }
    }
}
