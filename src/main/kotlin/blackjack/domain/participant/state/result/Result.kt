package blackjack.domain.participant.state.result

import blackjack.domain.participant.Participants
import blackjack.domain.participant.state.role.Role

sealed class Result {
    object Win : Result()
    object Lose : Result()
    object Draw : Result()

    override fun toString(): String {
        return when (this) {
            is Win -> "승"
            is Lose -> "패"
            is Draw -> "무"
        }
    }

    companion object {
        fun calculateResult(participants: Participants): Map<Role, Result> {
            if (participants.getDealer().isBlackjack()) {
                return participants.getPlayers().associateWith { Lose }
            }

            if (participants.getDealer().isBust()) {
                return participants.getPlayers().associateWith { Win }
            }
            return participants.getPlayers().associateWith { it.calculateResult(participants.getDealer().getScore()) }
        }

        fun calculateProfit(participants: Participants): Map<Role, Double> {
            if (participants.getDealer().isBlackjack()) {
                return calculateProfitFromDealerBlackjack(participants)
            }
            if (participants.getDealer().isBust()) {
                return calculateProfitFromDealerBust(participants)
            }
            return calculateProfitFromPlayersResult(participants)
        }

        private fun calculateProfitFromDealerBlackjack(participants: Participants): Map<Role, Double> {
            val playersResult: MutableMap<Role, Double> = mutableMapOf()
            var dealerMoney = 0.0

            participants.getPlayers().filter { it.isBlackjack() }.forEach {
                playersResult += it to 0.0
            }
            participants.getPlayers().filter { !it.isBlackjack() }.forEach {
                playersResult += it to it.bet.toDouble() * -1
                dealerMoney += it.bet.toDouble()
            }

            val dealerResult = mutableMapOf(participants.getDealer() to dealerMoney)
            return dealerResult + playersResult
        }

        private fun calculateProfitFromDealerBust(participants: Participants): Map<Role, Double> {
            val playersResult: MutableMap<Role, Double> = mutableMapOf()
            var dealerMoney = 0.0

            participants.getPlayers().filter { it.isBlackjack() }.forEach {
                playersResult += it to it.state.earningRate(it.bet.toInt())
                dealerMoney += it.state.earningRate(it.bet.toInt()) * -1
            }
            participants.getPlayers().filter { it.isStay() }.forEach {
                playersResult += it to it.bet.toDouble()
                dealerMoney += it.bet.toDouble() * -1
            }
            participants.getPlayers().filter { it.isBust() }.forEach {
                playersResult += it to it.bet.toDouble() * -1
                dealerMoney += it.bet.toDouble()
            }

            val dealerResult = mutableMapOf(participants.getDealer() to dealerMoney)
            return dealerResult + playersResult
        }

        private fun calculateProfitFromPlayersResult(participants: Participants): Map<Role, Double> {
            val playersResult: MutableMap<Role, Double> = mutableMapOf()
            val dealer = participants.getDealer()
            var dealerMoney = 0.0

            participants.getPlayers().filter { it.calculateResult(dealer.getScore()) is Win }.forEach {
                playersResult += it to it.state.earningRate(it.bet.toInt())
                dealerMoney += it.state.earningRate(it.bet.toInt()) * -1
            }
            participants.getPlayers().filter { it.calculateResult(dealer.getScore()) is Lose }.forEach {
                playersResult += it to it.bet.toDouble() * -1
                dealerMoney += it.bet.toDouble()
            }
            participants.getPlayers().filter { it.calculateResult(dealer.getScore()) is Draw }.forEach {
                playersResult += it to 0.0
            }

            val dealerResult = mutableMapOf(participants.getDealer() to dealerMoney)
            return dealerResult + playersResult
        }
    }
}
