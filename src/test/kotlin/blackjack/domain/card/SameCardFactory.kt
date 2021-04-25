package blackjack.domain.card

class SameCardFactory(private val cardType: CardType) : CardFactory {
    override fun createCards(): List<Card> {
        return (1..52).map { Card(CardShape.CLOVER, cardType) }
    }
}
