package blackjack.domain.game

import blackjack.domain.player.Player
import blackjack.domain.rate.EarningRates

class GameResult(private val game: Game) {
    fun getDealerProfit(): Double {
        return game.playersOutOfGame.map { calculatePlayerProfitFor(it) }.sum() * -1
    }

    fun calculatePlayerProfitFor(player: Player): Double {
        require(game.playersOutOfGame.contains(player)) {"게임이 종료되지 않았거나, 참가지하지 않은 플레이어 입니다."}
        val earningRate = EarningRates.getEarningRate(game.dealer, player)
        return player.bettingMoney * earningRate.getEarningRate(game.dealer, player)
    }
}
