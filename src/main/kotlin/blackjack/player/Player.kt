package blackjack.player

import betting.BetResult
import blackjack.card.Card
import blackjack.dealer.Dealer
import blackjack.deck.Deck
import blackjack.machine.BlackJackMachine.Companion.BONUS_RATIO
import blackjack.participant.Participant

class Player(
    override val name: String,
    override val hand: Hand = Hand(cards = emptyList()),
    override val betResult: BetResult = BetResult.Default(),
) : Participant<Player> {
    override fun hitCard(card: Card): Player {
        val handWithNewCard = hand.add(card)
        val result = Player(name = name, hand = handWithNewCard, betResult = betResult)

        return when {
            result.isBust() -> result.lose()
            else -> result
        }
    }

    fun handleBlackJack(dealer: Dealer): Player =
        when {
            !this.isBlackjack() -> this
            dealer.isBlackjack() -> updateBetResult(BetResult.Win(bet = this.bet, amount = this.betAmount))
            else ->
                updateBetResult(
                    BetResult.Win(
                        bet = this.bet,
                        amount = this.betAmount.times(BONUS_RATIO),
                    ),
                )
        }

    fun win(): Player = updateBetResult(betResult = BetResult.Win(bet = this.bet, amount = this.betAmount))

    fun lose(): Player = updateBetResult(betResult = BetResult.Lose(bet = this.bet, amount = this.bet.negative()))

    fun updateBetResult(betResult: BetResult): Player = Player(name = this.name, hand = this.hand, betResult = betResult)

    fun play(
        isHitCard: Boolean,
        deck: Deck,
    ): Player =
        when {
            this.isBust() -> this
            !isHitCard -> this
            else -> this.hitCard(deck.draw())
        }

    companion object {
        fun ready(name: String): Player = Player(name = name)
    }
}
