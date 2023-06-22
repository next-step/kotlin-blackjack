package blackjack.model

data class BlackjackDealer(
    private var deck: CardDeck,
    private val cardSelector: (CardDeck) -> TrumpCard,
) {
    fun drawCardAndRemoved(): TrumpCard {
        check(deck.isNotEmpty()) { "deck is empty. blackjack dealer can't draw card. dealer($this)" }
        val card = cardSelector(deck)
        deck -= card
        return card
    }
}
