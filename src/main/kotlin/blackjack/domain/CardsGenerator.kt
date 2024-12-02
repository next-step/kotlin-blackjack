package blackjack.domain

interface CardsGenerator {
    fun generate(): List<Card>
}
