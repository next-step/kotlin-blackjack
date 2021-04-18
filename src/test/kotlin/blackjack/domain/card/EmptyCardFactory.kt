package blackjack.domain.card

class EmptyCardFactory : CardFactory {
    override fun createCards(): List<Card> {
        return emptyList()
    }
}
