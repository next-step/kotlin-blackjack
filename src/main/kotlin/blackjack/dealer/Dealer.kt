package blackjack.dealer

import betting.BetResult
import blackjack.card.Card
import blackjack.participant.Participant
import blackjack.player.Hand
import blackjack.player.Player
import blackjack.view.ResultView

class Dealer(
    override val name: String = "딜러",
    override val hand: Hand = Hand(cards = emptyList()),
    override var betResult: BetResult = BetResult.Default(),
) : Participant<Dealer> {
    override fun hitCard(card: Card): Dealer = Dealer(name = name, hand = hand.add(card), betResult = betResult)

    override fun isWin(opponent: Participant<*>): Boolean? =
        (opponent as? Player)?.let {
            when {
                isBust() -> false
                opponent.isBust() -> true
                else -> hand.sum() > opponent.hand.sum()
            }
        }

    fun shouldDraw(): Boolean = hand.sum() <= DEALER_STANDING_RULE

    fun drawIfBelowDealerStandingRule(draw: () -> Card): Dealer =
        if (shouldDraw()) {
            this
                .hitCard(draw())
                .also { ResultView.printDealerDrawCard() }
        } else {
            this
        }

    companion object {
        const val DEALER_STANDING_RULE = 16

        fun ready(initialCards: List<Card>) = Dealer(hand = Hand(cards = initialCards))
    }
}
