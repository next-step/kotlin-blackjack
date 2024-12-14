package betting.board

import betting.BetResult
import blackjack.dealer.Dealer
import blackjack.participant.Participant
import blackjack.player.Player

class BettingBoard(
    val participantBets: MutableMap<Participant<*>, BetResult> = mutableMapOf(),
) {
    private fun getDealer(): Participant<*> =
        participantBets
            .filter { it.key is Dealer }
            .keys
            .first()

    private fun getPlayers(): List<Pair<Participant<*>, BetResult>> =
        participantBets
            .filter { it.key !is Dealer }
            .toList()

    fun setup(
        playerBets: Map<Participant<*>, BetResult>,
        dealer: Dealer,
    ) {
        playerBets.forEach { (player, betResult) ->
            participantBets[player] = betResult
        }
        participantBets[dealer] = BetResult.Default()
        handleBlackjack()
    }

    private fun handleBlackjack() {
        val blackJackPlayers = getPlayers()
            .filter { (participant, _) -> participant is Player && participant.isBlackjack() }

        if (blackJackPlayers.isEmpty()) return

        distributeBetAmountByWinLoss(
            players = blackJackPlayers,
            bonusRatio = BONUS_RATIO.takeIf { getDealer().isNotBlackjack() } ?: DEFAULT_RATIO,
        )
    }

    private fun Participant<*>.isNotBlackjack() = !this.isBlackjack()

    fun bust(player: Player) {
        val playerBet = participantBets[player] ?: return
        val dealerBet = participantBets[getDealer()] ?: return

        participantBets[getDealer()] = BetResult.Win(bet = dealerBet.bet, amount = dealerBet.winning.amount + playerBet.bet.amount)
        participantBets[player] = BetResult.Lose(bet = playerBet.bet, amount = playerBet.bet.negative())
    }

    fun winAllPlayer() {
        distributeBetAmountByWinLoss(players = getPlayers())
    }

    private fun distributeBetAmountByWinLoss(
        players: List<Pair<Participant<*>, BetResult>>,
        bonusRatio: Double = DEFAULT_RATIO,
    ) {
        players.forEach { (player, betResult) ->
            participantBets[player] = BetResult.Win(bet = betResult.bet, amount = betResult.bet.amount.times(bonusRatio))
        }

        val dealerBet = participantBets[getDealer()] ?: return
        val sumOfPlayerBetAmount =
            players
                .sumOf { (_, betResult) -> betResult.bet.amount.times(bonusRatio) }
                .times(-1L)
        participantBets[getDealer()] = BetResult.Lose(bet = dealerBet.bet, amount = dealerBet.bet.amount + sumOfPlayerBetAmount)
    }

    companion object {
        private const val DEFAULT_RATIO = 1.0
        const val BONUS_RATIO = 1.5
    }
}
