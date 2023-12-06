package blackjack.domain.batting

import blackjack.domain.BlackJackJudge
import blackjack.domain.Dealer
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerName
import blackjack.domain.player.Players
import blackjack.domain.result.distribution.DealEndResult
import blackjack.domain.result.game.Profit
import blackjack.domain.result.game.VictoryStatus
import java.math.BigDecimal

data class BetBoard(
    private val playerBets: MutableMap<PlayerName, PlayerBet>
) {
    fun playerBet(name: PlayerName): PlayerBet =
        findPlayerBet(name)

    fun playerProfit(name: PlayerName): Profit =
        findFinishedPlayerBet(name).profit

    fun dealerProfit(): Profit =
        playerBets.map {
            val bet = it.value as? PlayerBet.Finished
                ?: throw IllegalStateException("모든 플레이어가 베팅을 끝내지 않았습니다")
            bet.profit
        }.reduce(Profit::plus).negative

    fun closeBetting(dealEndResult: DealEndResult) {
        dealEndResult.players.value.forEach { player ->
            closeBet(player, dealEndResult.dealer)
        }
    }

    private fun closeBet(player: Player, dealer: Dealer) {
        val betToFinish = findPlacedPlayerBet(player.name)
        val payoutAmount = player.payout(dealer, betToFinish.betAmount)
        val finishedBet = PlayerBet.Finished.of(betToFinish, payoutAmount)
        playerBets[player.name] = finishedBet
    }

    private fun findPlacedPlayerBet(name: PlayerName): PlayerBet.Placed =
        findPlayerBet(name) as? PlayerBet.Placed ?: throw IllegalStateException("해당 플레이어의 베팅이 이미 종료되었습니다")

    private fun findFinishedPlayerBet(name: PlayerName): PlayerBet.Finished =
        findPlayerBet(name) as? PlayerBet.Finished ?: throw IllegalStateException("해당 플레이어의 베팅이 종료 되지 않았습니다")

    private fun findPlayerBet(name: PlayerName): PlayerBet =
        playerBets[name] ?: throw IllegalArgumentException("베팅에 참여한 플레이어가 아닙니다")

    private fun Player.payout(dealer: Dealer, betAmount: Amount): Amount =
        when (BlackJackJudge.judgeVictory(this, dealer)) {
            VictoryStatus.WIN -> calculateWinAmount(this, betAmount)
            VictoryStatus.LOSS -> Amount.ZERO
            VictoryStatus.PUSH -> betAmount
        }

    private fun calculateWinAmount(player: Player, betAmount: Amount): Amount =
        if (BlackJackJudge.isBlackJack(player)) betAmount * BLACK_JACK_MULTIPLIER + betAmount
        else betAmount * 2

    companion object {
        private val BLACK_JACK_MULTIPLIER = BigDecimal(1.5)
        fun of(players: Players, betAmount: (player: Player) -> Amount): BetBoard =
            BetBoard(players.value.associate { it.name to it.placeBet(betAmount) }.toMutableMap())

        private fun Player.placeBet(betAmount: (player: Player) -> Amount): PlayerBet =
            PlayerBet.Placed(this.name, betAmount(this))
    }
}
