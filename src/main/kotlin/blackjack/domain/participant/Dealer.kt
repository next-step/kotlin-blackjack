package blackjack.domain.participant

import blackjack.domain.card.Card
import blackjack.domain.card.Hand
import blackjack.domain.card.Point

class Dealer(
    private val deck: Deck = ShuffledDeck(),
    hand: Hand = Hand.empty(),
    state: State = Hittable,
    private val participant: Participant = Player(DEALER_NAME, hand, state)
) : Participant by participant {

    override var state: State = participant.state
        private set

    fun draw(): Card {
        check(!deck.isEmpty()) { "덱에 남은 카드가 없습니다" }
        return deck.draw()
    }

    override fun isPlayable(askHit: (String) -> Boolean): Boolean {
        return state is Hittable
    }

    override fun open(): Hand {
        return Hand(listOf(hand.first()))
    }

    override fun receive(card: Card) {
        participant.receive(card)
        changeState()
    }

    private fun changeState() {
        val point = hand.calculate()
        state = participant.state
        if (point >= STAY_POINT && point < Point.BLACKJACK) {
            state = Stay(point)
        }
    }

    companion object {
        private const val DEALER_NAME = "딜러"
        private val STAY_POINT = Point(17)
    }
}
