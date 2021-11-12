package blackJack.domain

class Player(val name: String, val cards: Cards) {
    fun receiveCard(card: Card): Cards {
        cards.checkOverlap(card)
        return cards + card
    }
}
