package blackjack.domain.card

interface CardFactory {
    fun createCards(): List<Card>
}
