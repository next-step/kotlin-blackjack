package blackjack.domain

import blackjack.HandsCalculator

class Hand {

    private val hands = mutableListOf<PokerCard>()
    private var ableToDraw = true

    fun hands(): List<PokerCard> {
        return hands.toList()
    }

    fun toRepresent(): String {
        return hands.joinToString(transform = PokerCard::representCard)
    }

    fun optimalValue(): Int {
        return HandsCalculator.calculateOptimalValue(hands)
    }

    fun addNewCard(pokerCard: PokerCard) {
        hands.add(pokerCard)
    }

    fun isBlackJackOrBust(): Boolean {
        return HandsCalculator.calculateOptimalValue(hands) >= BLACK_JACK_NUMBER
    }

    fun ableToDraw(): Boolean {
        return ableToDraw
    }

    fun changeAbleToDraw(ableToDraw: Boolean) {
        this.ableToDraw = ableToDraw
    }

    companion object {
        private const val BLACK_JACK_NUMBER = 21
    }
}
