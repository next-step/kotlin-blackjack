package blackjack.domain

import blackjack.domain.GameResult.Companion.resultOfPlayer
import blackjack.domain.GameResult.LOSE
import blackjack.domain.GameResult.WIN

class GameProfit(
    private val players: Players,
    private val dealer: Dealer
) {
    private var dealerProfit: Double = 0.0

    fun profitOfParticipants(): List<ParticipantProfit> {
        val participantProfits = mutableListOf<ParticipantProfit>()
        calculateProfitOfParticipant()

        participantProfits.add(ParticipantProfit(dealer.name, dealerProfit))
        participantProfits.addAll(
            players.values.map {
                ParticipantProfit(it.name, it.profit)
            }
        )

        return participantProfits
    }

    private fun calculateProfitOfParticipant() {
        players.values.forEach { player ->
            val playerScore = player.score
            when (resultOfPlayer(player, dealer)) {
                WIN -> handleWin(player, playerScore)
                LOSE -> handleLoss(player)
                else -> {}
            }
        }
    }

    private fun handleWin(player: Player, playerScore: Int) {
        when (playerScore) {
            BLACK_JACK -> player.plusMoney(player.betAmount * BONUS_PERCENTAGE)
            else -> player.plusMoney(player.betAmount)
        }
        dealerProfit -= player.profit
    }

    private fun handleLoss(player: Player) {
        dealerProfit += player.betAmount
        player.loseAllMoney()
    }

    companion object {
        const val BONUS_PERCENTAGE = 1.5
    }
}
