package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Card.AceCard
import blackjack.domain.game.TakeMorePlayerStrategy

open class Player(
    private val _name: String,
    val takeMorePlayerStrategy: TakeMorePlayerStrategy,
    val receivedCards: MutableSet<Card> = mutableSetOf()
) {

    var isWinner: Boolean = false

    val score: Int
        get() = calculateScore()

    val name: String
        get() = _name

    fun calculateScore(): Int {
        var score = receivedCards.sumOf { it.number }

        if (score > BLACKJACK_SCORE) {
            val aceCount = receivedCards
                .count { it is AceCard }

            score = score - (ACE_NUMBER_TO_ELEVEN * aceCount) + (ACE_NUMBER_TO_ONE * aceCount)
        }

        return score
    }

    fun canMoreGame(): Boolean {
        return calculateScore() != BLACKJACK_SCORE && calculateScore() < BLACKJACK_SCORE
    }

    fun addCard(card: Card) {
        receivedCards.add(card)
    }

    fun wantToTake(): Boolean {
        return takeMorePlayerStrategy.wantToTake(this)
    }

    companion object {
        private const val BLACKJACK_SCORE = 21
        private const val ACE_NUMBER_TO_ONE = 1
        private const val ACE_NUMBER_TO_ELEVEN = 11
    }
}
