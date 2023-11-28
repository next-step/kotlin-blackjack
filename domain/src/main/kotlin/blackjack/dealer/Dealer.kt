package blackjack.dealer

import action.BlackJackAction
import blackjack.BlackjackParticipant
import blackjack.card.Card
import blackjack.deck.Deck
import blackjack.hand.Hand
import blackjack.hand.StandardHand

data class Dealer(
    val dealerStrategy: DealerStrategy = DefaultDealerStrategy(),
    private val hand: Hand = StandardHand(),
) : BlackjackParticipant {

    val cards: List<Card> get() = hand.cards()

    override fun receiveCard(card: Card): Dealer = copy(hand = hand.addCard(card))

    override fun receiveCard(cards: List<Card>): Dealer = copy(hand = hand.addCard(cards))

    override fun calculateBestValue(): Int = hand.calculateBestValue()

    fun decideAction(deck: Deck): BlackJackAction {
        return dealerStrategy.decideAction(hand, deck)
    }
}
