package blackjack.domain.blackjack

import blackjack.domain.common.Money
import blackjack.domain.player.Dealer
import blackjack.domain.player.Participant
import blackjack.domain.player.Player
import blackjack.domain.player.Players

data class ParticipantProfits(private val participantProfits: List<ParticipantProfit>) {

    init {
        require(containsPlayer() && containsDealer()) {
            "참가자 수익금액들에는 딜러와 참가자가 모두 포함되어야 합니다"
        }
    }

    private fun containsPlayer() = participantProfits.any { it.participant is Player }

    private fun containsDealer() = participantProfits.any { it.participant is Dealer }

    fun dealerProfit(): ParticipantProfit {
        return participantProfits.find { it.participant is Dealer }
            ?: throw IllegalStateException("딜러가 존재하지 않습니다")
    }

    fun playersProfit(): List<ParticipantProfit> {
        return participantProfits.filter { it.participant is Player }
    }

    companion object {
        fun of(players: Players, dealer: Dealer): ParticipantProfits {
            val playersProfit = ParticipantProfit.listOf(players.profit(dealer))
            val dealerProfit = ParticipantProfit(dealer, dealer.profit(players))
            return ParticipantProfits(playersProfit + dealerProfit)
        }
    }
}

data class ParticipantProfit(
    val participant: Participant,
    val profit: Money,
) {
    companion object {
        fun listOf(profitByPlayer: Map<Player, Money>): List<ParticipantProfit> {
            return profitByPlayer.map { ParticipantProfit(it.key, it.value) }
        }
    }
}
