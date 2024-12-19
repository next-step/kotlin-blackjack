package blackjack.participant

import blackjack.card.Card
import blackjack.dealer.Dealer
import blackjack.player.Hand
import blackjack.player.Player

object ParticipantFixture {
    fun Participant<*>.hitCards(cards: List<Card>): Participant<*> =
        when (this) {
            is Player ->
                Player(
                    name = this.name,
                    hand = Hand(cards = this.hand.cards.plus(cards)),
                    betResult = this.betResult,
                )

            is Dealer ->
                Dealer(
                    name = this.name,
                    hand = Hand(cards = this.hand.cards.plus(cards)),
                    betResult = this.betResult,
                )

            else -> throw IllegalArgumentException("Unsupported participant type")
        }
}
