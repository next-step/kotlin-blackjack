package blackjack.model.participant

import blackjack.model.CardDeck
import blackjack.model.HandDeck
import blackjack.model.TrumpCard

sealed class BlackjackParticipant(val cardDeck: CardDeck) {
    val handDeck: HandDeck = HandDeck()

    val deckScore: Int
        get() = handDeck.score

    val deckCount: Int
        get() = handDeck.count

    val isSameLimitScore: Boolean
        get() = handDeck.isLessScoreThanLimit

    val isScoreOverThanLimitScore: Boolean
        get() = handDeck.isScoreOverThanLimitScore

    init {
        repeat(INITIAL_DEALING_COUNT) {
            add(cardDeck.draw())
        }
    }

    fun isWinByScoreGap(participant: BlackjackParticipant): Boolean {
        return handDeck.gapScoreWithLimitScore < participant.handDeck.gapScoreWithLimitScore
    }

    fun add(card: TrumpCard) {
        handDeck.add(card)
    }

    abstract fun draw()

    companion object {
        const val INITIAL_DEALING_COUNT: Int = 2
    }
}
