package blackjack.domain.card

class CardShuffleMachine : ShuffleMachine {
    override fun shuffle(cards: List<Card>): List<Card> {
        return cards.shuffled().toList()
    }
}
