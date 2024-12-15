package blackjack.dealer

import betting.Bet
import betting.BetResult
import betting.TurnResult
import blackjack.card.Card
import blackjack.machine.BlackJackMachine.Companion.BONUS_RATIO
import blackjack.participant.Participant
import blackjack.player.Hand
import blackjack.player.Player
import blackjack.player.Players

class Dealer(
    override val name: String = "딜러",
    override val hand: Hand = Hand(cards = emptyList()),
    override val betResult: BetResult = BetResult.Default(),
) : Participant<Dealer> {
    override fun hitCard(card: Card): Dealer = Dealer(name = name, hand = hand.add(card), betResult = betResult)

    fun shouldDraw(): Boolean = hand.sum() <= DEALER_STANDING_RULE

    fun drawIfBelowDealerStandingRule(
        players: Players,
        draw: () -> Card,
        afterDraw: (Dealer) -> Unit,
    ): TurnResult =
        when {
            shouldDraw() -> {
                this
                    .hitCard(draw())
                    .also { afterDraw(it) }
                    .handleBust(players = players)
            }

            else -> TurnResult.status(players = players, dealer = this)
        }

    private fun handleBust(players: Players): TurnResult =
        TurnResult.status(
            players = players.applyWinToWinners(),
            dealer = this.lose(players = players.getRemainedPlayers()),
        )

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

    fun win(player: Player): Dealer =
        updateBetResult(betResult = BetResult.Win(bet = this.bet, amount = this.winningAmount + player.betAmount))

    private fun lose(players: Players): Dealer {
        val sumOfPlayersBetAmount = players.sum()
        return updateBetResult(betResult = BetResult.Lose(bet = this.bet, amount = this.winningAmount - sumOfPlayersBetAmount))
    }

    private fun updateBetResult(betResult: BetResult): Dealer =
        Dealer(name = this.name, hand = this.hand, betResult = betResult)

    companion object {
        const val DEALER_STANDING_RULE = 16

        fun ready(initialCards: List<Card>) = Dealer(hand = Hand(cards = initialCards))
    }
}
