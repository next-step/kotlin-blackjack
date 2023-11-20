package blackjack.business.card

class DefaultCardFactory : CardFactory {
    private val allCards: List<Card> = Card.allCards

    override fun getCards(): List<Card> = allCards
}
