package blackjack.player

import betting.BetResult
import blackjack.card.Card
import blackjack.dealer.Dealer
import blackjack.machine.BlackJackMachine.Companion.BONUS_RATIO
import blackjack.participant.Participant

class Player(
    override val name: String,
    override val hand: Hand = Hand(cards = emptyList()),
    override val betResult: BetResult = BetResult.Default(),
) : Participant<Player> {
    override fun hitCard(card: Card): Player = Player(name = name, hand = hand.add(card), betResult = betResult)

    override fun isWin(opponent: Participant<*>): Boolean? =
        (opponent as? Dealer)?.let { dealer ->
            when {
                dealer.isBust() -> true
                isBust() -> false
                else -> hand.sum() > opponent.hand.sum()
            }
        }

    override fun equals(other: Any?): Boolean =
        when {
            this === other -> true
            other !is Player -> false
            else -> name == other.name
        }

    override fun hashCode(): Int = name.hashCode()

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

    fun updateBetResult(betResult: BetResult): Player =
        Player(name = this.name, hand = this.hand, betResult = betResult)

    companion object {
        fun ready(name: String): Player = Player(name = name)
    }
}
