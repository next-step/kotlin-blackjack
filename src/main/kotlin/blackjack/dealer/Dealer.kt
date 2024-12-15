package blackjack.dealer

import betting.Bet
import betting.BetResult
import blackjack.card.Card
import blackjack.machine.BlackJackMachine.Companion.BONUS_RATIO
import blackjack.participant.Participant
import blackjack.player.Hand
import blackjack.player.Player
import blackjack.view.ResultView

class Dealer(
    override val name: String = "딜러",
    override val hand: Hand = Hand(cards = emptyList()),
    override val betResult: BetResult = BetResult.Default(),
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
    ): BetResult.Lose = BetResult.Lose(bet = bet, amount = this.winingAmount + amount)

    fun win(player: Player): Dealer =
        updateBetResult(betResult = BetResult.Win(bet = this.bet, amount = this.winingAmount + player.betAmount))

    fun lose(players: List<Player>): Dealer {
        val sumOfPlayersBetAmount = players.sumOf { it.betAmount}
        return updateBetResult(betResult = BetResult.Lose(bet = this.bet, amount = this.winingAmount - sumOfPlayersBetAmount))
    }

    private fun updateBetResult(betResult: BetResult): Dealer =
        Dealer(name = this.name, hand = this.hand, betResult = betResult)

    companion object {
        const val DEALER_STANDING_RULE = 16

        fun ready(initialCards: List<Card>) = Dealer(hand = Hand(cards = initialCards))
    }
}
