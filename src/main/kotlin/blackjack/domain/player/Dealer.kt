package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.card.Score
import blackjack.domain.game.Hand
import blackjack.domain.game.PlayingHand
import blackjack.domain.game.Stay

const val DEALER_HIT_MAX = 16
private const val DEALER_FIRST_OPEN_COUNT = 1
private val DEALER_HIT_MAX_SCORE = Score(DEALER_HIT_MAX)
private val DEALER_NAME = PlayerName("딜러")

class Dealer(
    hand: Hand = DealerHand(),
    override val afterHitCallBack: AfterHitWhileCallback? = null,
) : Gamer(DEALER_NAME, hand) {

    override fun wantHit(answerProvider: AnswerProvider) = true

    override fun firstOpenCardsCount() = DEALER_FIRST_OPEN_COUNT

    fun isBlackJack() = result.isBlackJack()

    fun isBust() = result.isBust()
}

class DealerHand : PlayingHand() {

    override val cards: Cards = Cards.createEmpty()

    override fun hitByGamer(card: Card): Hand {
        if (cards.score > DEALER_HIT_MAX_SCORE) {
            return Stay(cards)
        }
        return this
    }
}
