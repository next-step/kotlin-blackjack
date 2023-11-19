package blackjack.domain

interface CardShuffler {
    fun shuffle(cards: List<Card>): List<Card>
}
