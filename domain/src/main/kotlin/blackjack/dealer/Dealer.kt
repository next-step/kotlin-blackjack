package blackjack.dealer

import action.BlackJackAction
import blackjack.BlackjackParticipant
import blackjack.card.Card
import blackjack.deck.Deck
import blackjack.hand.Hand
import blackjack.hand.StandardHand

class Dealer(
    override val hand: Hand = StandardHand(),
    private val dealerStrategy: DealerStrategy = DefaultDealerStrategy()
) : BlackjackParticipant {

    val cards: List<Card>
        get() = hand.cards.toList()

    override fun receiveCard(card: Card): Dealer = Dealer(hand.addCard(card))

    override fun calculateBestValue(): Int = hand.calculateBestValue()

    fun decideAction(deck: Deck): BlackJackAction {
        return dealerStrategy.decideAction(hand, deck)
    }
}
