package blackjack.domain.participant.state.result

import blackjack.domain.participant.Participants
import blackjack.domain.participant.state.role.Role

sealed class Result {
    object Win : Result()
    object Lose : Result()
    object Draw : Result()

    companion object {
        fun calculateResult(participants: Participants): Map<Role, Result> {
            val dealer = participants.getDealer()
            val players = participants.getPlayers()
            val result = mutableMapOf<Role, Result>()

            if (dealer.isBlackjack) {
                players.filter(Role::isBlackjack).forEach { result[it] = Draw }
                players.filterNot(Role::isBlackjack).forEach { result[it] = Lose }
                return result
            }
            if (dealer.isBust) {
                players.filter(Role::isBust).forEach { result[it] = Lose }
                players.filterNot(Role::isBust).forEach { result[it] = Win }
                return result
            }
            players.forEach { result[it] = it.calculateResult(dealer.score) }
            return result
        }

        fun calculateProfit(participants: Participants): Map<Role, Double> {
            val result = calculateResult(participants)
            val playersProfit = mutableMapOf<Role, Double>()
            result.forEach { (role, result) ->
                when (result) {
                    is Win -> playersProfit[role] = role.earningRate
                    is Lose -> playersProfit[role] = role.money.value * -1.0
                    is Draw -> playersProfit[role] = 0.0
                }
            }
            val dealerProfit = mutableMapOf<Role, Double>(participants.getDealer() to playersProfit.values.sum() * -1.0)
            return dealerProfit + playersProfit
        }
    }
}
