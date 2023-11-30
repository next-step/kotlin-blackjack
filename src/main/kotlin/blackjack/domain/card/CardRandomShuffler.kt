package blackjack.domain.card

class CardRandomShuffler : CardShuffler {
    override fun shuffle(cards: List<Card>): List<Card> {
        return cards.shuffled().toList()
    }
}
