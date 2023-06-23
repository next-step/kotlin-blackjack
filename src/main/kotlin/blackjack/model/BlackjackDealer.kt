package blackjack.model

data class BlackjackDealer(
    private var deck: CardDeck,
    private val cardSelector: (Collection<TrumpCard>) -> TrumpCard,
) {
    fun drawCard(): TrumpCard {
        val card = deck.card(cardSelector)
        deck -= card
        return card
    }
}
