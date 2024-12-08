package blackjack.dealer

import blackjack.card.Card
import blackjack.participant.Participant
import blackjack.player.Hand
import blackjack.player.Player
import blackjack.view.ResultView

class Dealer(
    override val name: String = "딜러",
    override val hand: Hand = Hand(cards = emptyList()),
) : Participant<Dealer> {
    override fun hitCard(card: Card): Dealer = Dealer(name = name, hand = hand.add(card))

    fun isDraw(): Boolean = hand.sum() <= DEALER_STANDING_RULE

    fun drawIfAllowed(draw: () -> Card): Dealer =
        if (isDraw()) {
            this
                .hitCard(draw())
                .also { ResultView.printDealerDrawCard() }
        } else {
            this
        }

    fun isWin(
        player: Player,
    ): Boolean =
        when {
            isBust() -> false
            player.isBust() -> true
            else -> hand.sum() > player.hand.sum()
        }

    companion object {
        const val DEALER_STANDING_RULE = 16

        fun ready(initialCards: List<Card>) = Dealer(hand = Hand(cards = initialCards))
    }
}
