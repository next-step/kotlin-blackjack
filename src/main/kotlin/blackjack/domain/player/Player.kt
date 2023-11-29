package blackjack.domain.player

import blackjack.domain.card.Card

class Player(
    val name: PlayerName,
    val cards: PlayerCards = PlayerCards()
) {
    fun handCard(card: Card) {
        cards.add(card)
    }
}
