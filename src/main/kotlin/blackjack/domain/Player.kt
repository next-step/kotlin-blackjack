package blackjack.domain

class Player(
    val name: PlayerName,
    val cards: PlayerCards = PlayerCards()
) {
    fun handCard(card: Card) {
        cards.add(card)
    }
}
