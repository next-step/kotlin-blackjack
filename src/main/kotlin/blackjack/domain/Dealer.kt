package blackjack.domain

import blackjack.domain.Game.Companion.DEFAULT_CARD_COUNT
import blackjack.domain.Game.Companion.MAXIMUM_SCORE_FOR_DEALER_DRAWING

data class Dealer(val deck: Deck = Deck()) : Player() {
    override val cards: Cards = Cards(setOf())
    var result = listOf(0, 0)
        private set

    fun pickCard(): Card? = deck.provideCard(deck.shuffled())

    fun setUpWithCards(players: Players): Cards {
        repeat(DEFAULT_CARD_COUNT) {
            draw(pickCard())

            (0 until players.size()).forEach { nth ->
                players.findPlayer(nth).draw(pickCard())
            }
        }
        return cards
    }

    fun hasScoreBelow17(score: Int): Boolean = score < MAXIMUM_SCORE_FOR_DEALER_DRAWING

    fun faceUpCard(): Card = cards.firstCard()

    fun checkWin(players: Players): List<Int> {
        val loseCount = players.compareWith(dealer = this)
        val winCount = players.size() - loseCount
        result = listOf(winCount, loseCount)
        return result.toList()
    }

    override fun toString(): String = DEALER_NAME

    companion object {
        private const val DEALER_NAME = "딜러"
    }
}
