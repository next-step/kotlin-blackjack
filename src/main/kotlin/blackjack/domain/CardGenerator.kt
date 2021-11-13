package blackjack.domain

interface CardGenerator {
    fun generateCards(): List<Card>
}
