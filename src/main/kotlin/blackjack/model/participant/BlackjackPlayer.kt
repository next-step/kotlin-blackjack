package blackjack.model.participant

import blackjack.model.BettingMoneyProvider
import blackjack.model.BlackjackPlayerConsumer
import blackjack.model.CardDeck
import blackjack.model.Money
import blackjack.model.MoreWantedCardPredicate
import blackjack.model.PlayerName

class BlackjackPlayer(
    deck: CardDeck,
    bettingMoneyProvider: BettingMoneyProvider,
    val name: PlayerName,
    private val blackjackPlayerConsumer: BlackjackPlayerConsumer,
    private val moreWantedCardPredicate: MoreWantedCardPredicate,
) : BlackjackParticipant(deck) {

    private val bettingMoney: Money = bettingMoneyProvider.bet(name)

    override fun draw() {
        var isReceivedMoreCard = false
        while (handDeck.isLessScoreThanLimit && moreWantedCardPredicate.isWantedMorePredicate(name)) {
            add(cardDeck.draw())
            blackjackPlayerConsumer.consumeParticipant(this)
            isReceivedMoreCard = true
        }
        if (!isReceivedMoreCard) {
            blackjackPlayerConsumer.consumeParticipant(this)
        }
    }

    fun revenue(dealer: BlackjackDealer): Money {
        if (isSameLimitScore) {
            return revenueBlackjack(dealer)
        }
        if (isWinFrom(dealer)) {
            return bettingMoney
        }
        return bettingMoney.negative
    }

    private fun revenueBlackjack(dealer: BlackjackDealer): Money {
        return if (dealer.isSameLimitScore) {
            Money.ZERO
        } else if (handDeck.equalsCountOf(BONUS_REVENUE_CARD_COUNT)) {
            bettingMoney.oneAndHalfTimes
        } else {
            bettingMoney
        }
    }

    private fun isWinFrom(dealer: BlackjackDealer): Boolean {
        if (dealer.isScoreOverThanLimitScore) {
            return true
        }
        if (isScoreOverThanLimitScore) {
            return false
        }
        return isWinByScoreGap(dealer)
    }

    companion object {
        private const val BONUS_REVENUE_CARD_COUNT = 2
    }
}
