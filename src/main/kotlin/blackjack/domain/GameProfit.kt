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
        participantProfits.addAll(players.values.map {
            ParticipantProfit(it.name, it.betAmount)
        })

        return participantProfits
    }

    private fun calculateProfitOfParticipant() {
        players.values.forEach { player ->
            val playerScore = player.score
            when (resultOfPlayer(player, dealer)) {
                WIN -> handleWin(player, playerScore)
                LOSE -> handleLoss(player, playerScore)
                else -> {}
            }
        }
    }

    private fun handleWin(player: Player, playerScore: Int) {
        if (playerScore == BLACK_JACK) {
            player.plusMoney(player.betAmount * BONUS_PERCENTAGE)
        }
        dealerProfit -= playerScore
    }

    private fun handleLoss(player: Player, playerScore: Int) {
        dealerProfit += playerScore
        player.loseAllMoney()
    }

    companion object {
        const val BONUS_PERCENTAGE = 0.5
    }
}