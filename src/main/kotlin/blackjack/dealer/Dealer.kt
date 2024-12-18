package blackjack.dealer

import betting.Bet
import betting.BetResult
import blackjack.card.Card
import blackjack.deck.Deck
import blackjack.machine.BlackJackMachine.Companion.BONUS_RATIO
import blackjack.participant.Participant
import blackjack.player.Hand
import blackjack.player.Player

class Dealer(
    override val name: String = "딜러",
    override val hand: Hand = Hand(cards = emptyList()),
    override val betResult: BetResult = BetResult.Default(),
) : Participant<Dealer> {
    override fun hitCard(card: Card): Dealer = Dealer(name = name, hand = hand.add(card), betResult = betResult)

    private val notDraw: Boolean = hand.sum() > DEALER_STANDING_RULE

    fun drawIfBelowDealerStandingRule(
        deck: Deck,
        afterDraw: (Dealer) -> Unit,
    ): Dealer {
        if (notDraw) return this

        return this
            .hitCard(deck.draw())
            .also { afterDraw(it) }
    }

    fun handleBust(betAmount: Double): Dealer {
        if (isNotBust()) return this

        return this.lose(betAmount = betAmount)
    }

    private fun isNotBust(): Boolean = !this.isBust()

    fun handleBlackJack(blackJackPlayers: List<Player>): Dealer {
        val sumOfPlayersBetAmount = blackJackPlayers.sumOf { it.bet.negative() }
        return when {
            this.isBlackjack() ->
                updateBetResult(generateBetLose(bet = this.bet, amount = sumOfPlayersBetAmount))

            else ->
                updateBetResult(generateBetLose(bet = this.bet, amount = sumOfPlayersBetAmount.times(BONUS_RATIO)))
        }
    }

    private fun generateBetLose(
        bet: Bet,
        amount: Double,
    ): BetResult.Lose = BetResult.Lose(bet = bet, amount = this.winningAmount + amount)

    fun win(betAmount: Double): Dealer = updateBetResult(betResult = BetResult.Win(bet = this.bet, amount = this.winningAmount + betAmount))

    private fun lose(betAmount: Double): Dealer =
        updateBetResult(betResult = BetResult.Lose(bet = this.bet, amount = this.winningAmount - betAmount))

    private fun updateBetResult(betResult: BetResult): Dealer = Dealer(name = this.name, hand = this.hand, betResult = betResult)

    companion object {
        const val DEALER_STANDING_RULE = 16

        fun ready(initialCards: List<Card>) = Dealer(hand = Hand(cards = initialCards))
    }
}
