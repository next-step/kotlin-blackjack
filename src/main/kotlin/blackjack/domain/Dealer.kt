package blackjack.domain

class Dealer(
    private val deck: Deck = ShuffledDeck(),
    private val participant: Participant = Player(DEALER_NAME)
) : Participant by participant {

    fun draw(): Card {
        check(deck.isNotEmpty()) { "덱에 남은 카드가 없습니다" }
        return deck.draw()
    }

    companion object {
        private const val DEALER_NAME = "딜러"
    }
}
