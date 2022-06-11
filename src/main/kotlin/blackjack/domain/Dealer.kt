package blackjack.domain

class Dealer(
    private val deck: Deck = ShuffledDeck(),
    hand: Hand = Hand.empty(),
    private val participant: Participant = Player(DEALER_NAME, hand)
) : Participant by participant {

    override val name: String = DEALER_NAME

    override var state = participant.state
        get() =
            if (field is Stay) field
            else participant.state
        private set

    fun draw(): Card {
        check(deck.isNotEmpty()) { "덱에 남은 카드가 없습니다" }
        return deck.draw()
    }

    override fun open(): Hand {
        return Hand(listOf(hand.first()))
    }

    override fun saidHit(): Boolean = true

    override fun receive(card: Card) {
        participant.receive(card)
        changeState()
    }

    private fun changeState() {
        val point = hand.calculate()
        if (point >= STAY_POINT && point < Point.BLACKJACK) {
            state = Stay(point)
        }
    }

    companion object {
        private const val DEALER_NAME = "딜러"
        private val STAY_POINT = Point(17)
    }
}
