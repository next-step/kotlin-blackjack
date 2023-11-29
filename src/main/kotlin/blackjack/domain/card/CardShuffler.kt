package blackjack.domain.card

interface CardShuffler {
    fun shuffle(cards: List<Card>): List<Card>
}
