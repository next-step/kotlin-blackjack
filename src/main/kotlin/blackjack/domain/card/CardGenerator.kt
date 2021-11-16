package blackjack.domain.card

interface CardGenerator {
    fun generateCards(): List<Card>
}
