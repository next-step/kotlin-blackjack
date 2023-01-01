package domain.player

import domain.algorithm.DefaultScoreOptimizationAlgorithm
import domain.card.CardDeck
import domain.card.Cards
import domain.factory.DefaultCardsFactory

class Dealer(
    val deck: CardDeck = CardDeck(cardsFactory = DefaultCardsFactory)
) : Playable {

    override val hands: Cards = Cards(scoreOptimizationAlgorithm = DefaultScoreOptimizationAlgorithm)

    init {
        deck.shuffle()
    }

    fun giveCard(playable: Playable) {
        val card = deck.pop()
        playable.receiveCard(card = card)
    }

    override fun isAvailableReceive(): Boolean = this.hands.isAvailableReceiveNumber(AVAILABLE_TARGET_NUMBER)

    fun isWin(playable: Playable): Boolean {
        if (this.handsCardScore() > WIN_SCORE) {
            return true
        }

        if (WIN_SCORE < playable.handsCardScore()) {
            return false
        }

        return playable.handsCardScore() > this.handsCardScore()
    }

    private companion object {
        const val AVAILABLE_TARGET_NUMBER = 17
        const val WIN_SCORE = 21
    }
}
