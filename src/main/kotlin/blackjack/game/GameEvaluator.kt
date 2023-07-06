package blackjack.game

import blackjack.dealer.Dealer
import blackjack.player.Player

class GameEvaluator {
    fun evaluate(dealer: Dealer, players: List<Player>): GameResult {
        val matchResult = mutableMapOf<String, Result>()
        matchResult[Dealer.DEALER_NAME] = Result()
        if (dealer.isBust) {
            players.map { matchResult[it.name] = Result(it.bettingMoney) }
            dealer.increaseBettingMoney(0)
        } else {
            evaluateScores(dealer, players, matchResult)
        }
        return GameResult(matchResult)
    }
    private fun evaluateScores(dealer: Dealer, players: List<Player>, matchResult: MutableMap<String, Result>) {
        var dealerEarnings = dealer.bettingMoney

        players.forEach { player ->
            val playerFinalEarnings: Int = when {
                player.isBlackjack && dealer.isBlackjack -> player.bettingMoney
                player.isBlackjack -> {
                    dealerEarnings -= (player.bettingMoney * 1.5).toInt()
                    player.bettingMoney + (player.bettingMoney * 0.5).toInt()
                }
                player.isBust -> {
                    dealerEarnings += player.bettingMoney
                    -player.bettingMoney
                }
                player.totalValue > dealer.totalValue -> {
                    dealerEarnings -= player.bettingMoney
                    player.bettingMoney
                }
                player.totalValue < dealer.totalValue -> {
                    dealerEarnings += player.bettingMoney
                    -player.bettingMoney
                }
                else -> player.bettingMoney
            }
            matchResult[player.name] = Result(playerFinalEarnings)
        }
        matchResult[dealer.name] = Result(dealerEarnings)
    }
}
