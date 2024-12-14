package betting.board

import betting.Bet
import betting.BetResult
import blackjack.dealer.Dealer
import blackjack.participant.Participant
import blackjack.player.Player
import blackjack.player.Players

object BettingBoard {
    private const val DEFAULT_RATIO = 1.0
    const val BONUS_RATIO = 1.5

    private data class BetAction(
        val participant: Participant<*>,
        val amount: Double?,
        val evaluate: (Bet, Double?) -> BetResult,
    )

    fun handleBlackjack(players: Players, dealer: Dealer) {
        val blackJackPlayers =
            players.players
                .filter { it.isBlackjack() }

        if (blackJackPlayers.isEmpty()) return

        distributeBetAmountByWinLoss(
            players = blackJackPlayers,
            dealer = dealer,
            bonusRatio = BONUS_RATIO.takeIf { dealer.isNotBlackjack() } ?: DEFAULT_RATIO,
        )
    }

    private fun Participant<*>.isNotBlackjack() = !this.isBlackjack()

    fun winDealer(
        player: Player,
        dealer: Dealer,
    ) {
        val betActions =
            listOf(
                BetAction(participant = dealer, amount = dealer.winingAmount + player.betAmount) { bet, amount ->
                    BetResult.Win(bet = bet, amount = amount)
                },
                BetAction(participant = player, amount = player.bet.negative()) { bet, amount ->
                    BetResult.Lose(bet = bet, amount = amount)
                },
            )

        betActions.forEach { (participant, amount, evaluate) ->
            updateBetResult(
                participant = participant,
                amount = amount,
                evaluate = evaluate,
            )
        }
    }

    private fun updateBetResult(
        participant: Participant<*>,
        amount: Double?,
        evaluate: (Bet, Double?) -> BetResult,
    ) {
        participant.updateBetResult(evaluate(participant.bet, amount))
    }

    fun winRemainedPlayer(
        players: Players,
        dealer: Dealer,
    ) {
        val remainedPlayers =
            players.players
                .filterNot { it.isBust() }
        distributeBetAmountByWinLoss(players = remainedPlayers, dealer = dealer)
    }

    private fun distributeBetAmountByWinLoss(
        players: List<Player>,
        dealer: Dealer,
        bonusRatio: Double = DEFAULT_RATIO,
    ) {
        players.forEach {
            val betResult = BetResult.Win(bet = it.bet, amount = it.betAmount.times(bonusRatio))
            it.updateBetResult(betResult)
        }

        val sumOfPlayerBetAmount =
            players
                .sumOf { it.betAmount.times(bonusRatio) }
                .times(-1L)
        dealer.updateBetResult(BetResult.Lose(bet = dealer.bet, amount = dealer.bet.amount + sumOfPlayerBetAmount))
    }
}
