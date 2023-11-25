package blackjack.dealer

import action.BlackJackAction
import blackjack.BlackjackParticipant
import blackjack.card.Card
import blackjack.deck.Deck
import blackjack.hand.Hand
import blackjack.hand.StandardHand

data class Dealer(
    val hand: Hand = StandardHand(),
    val dealerStrategy: DealerStrategy = DefaultDealerStrategy()
) : BlackjackParticipant {

    val cards: List<Card> = hand.cards()

    override fun receiveCard(card: Card): Dealer = Dealer(hand.addCard(card))

    override fun calculateBestValue(): Int = hand.calculateBestValue()

    fun decideAction(deck: Deck): BlackJackAction {
        return dealerStrategy.decideAction(hand, deck)
    }
}
