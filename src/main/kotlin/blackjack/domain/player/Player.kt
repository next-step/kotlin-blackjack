package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.card.Score
import blackjack.domain.game.*

private const val PLAYER_FIRST_OPEN_COUNT = 2

class Player(
    name: PlayerName,
    private val bet: Money,
    hand: Hand = PlayerHand(),
    override val afterHitCallBack: AfterHitWhileCallback? = null,
) : Gamer(name, hand) {

    override fun wantHit(answerProvider: AnswerProvider): Boolean {
        return answerProvider.getAnswer(this).hit
    }

    override fun firstOpenCardsCount() = PLAYER_FIRST_OPEN_COUNT

    fun calculateProfitBy(dealer: Dealer): Profit {
        return result.getProfit(bet, dealer)
    }
}

class PlayerHand : PlayingHand() {

    override val cards: Cards = Cards.createEmpty()

    override fun hitByGamer(card: Card): Hand {
        if (cards.score == Score.BLACK_JACK_SCORE) {
            return Stay(cards)
        }
        return this
    }
}
