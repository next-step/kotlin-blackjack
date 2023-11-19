package blackjack.domain

class CardRandomShuffler : CardShuffler {
    override fun shuffle(cards: List<Card>): List<Card> {
        return cards.shuffled().toList()
    }
}
