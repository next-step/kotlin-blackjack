package blackjack.domain

import blackjack.vo.GameResultVO

class GameResult(
    private val dealer: Dealer,
    private val players: Players,
) {
    private val dealerWinMap: MutableMap<Result, Int> = mutableMapOf()
    private val playersWinMap: MutableMap<Player, Result> = mutableMapOf()

    fun calculate(): GameResultVO {
        val dealerState = dealer.findStateBySum()

        players.players.forEach {
            when (dealerState) {
                PlayerState.BLACK_JACK -> dealerBlackjack(it)
                PlayerState.BUST -> dealerBust(it)
                PlayerState.STAND -> dealerStand(it)
                else -> throw IllegalStateException()
            }
        }

        return GameResultVO(dealerWinMap, playersWinMap)
    }

    private fun dealerBlackjack(player: Player) {
        if (player.findStateBySum() == PlayerState.BLACK_JACK) {
            draw(player)
            return
        }

        dealerWinAndPlayerLose(player)
    }

    private fun dealerBust(player: Player) {
        if (player.findStateBySum() == PlayerState.BUST) {
            dealerWinAndPlayerLose(player)
            return
        }

        if (player.sumOfMyCards() == dealer.sumOfMyCards()) {
            draw(player)
            return
        }

        dealerLoseAndPlayerWin(player)
    }

    private fun dealerStand(player: Player) {
        if (player.findStateBySum() == PlayerState.BUST) {
            dealerWinAndPlayerLose(player)
            return
        }

        if (player.sumOfMyCards() == dealer.sumOfMyCards()) {
            draw(player)
            return
        }

        if (player.sumOfMyCards() > dealer.sumOfMyCards()) {
            dealerLoseAndPlayerWin(player)
            return
        }

        dealerWinAndPlayerLose(player)
    }

    private fun draw(player: Player) {
        dealerWinMap[Result.DRAW] = dealerWinMap.getOrDefault(Result.DRAW, 0) + 1
        playersWinMap[player] = Result.DRAW
    }

    private fun dealerLoseAndPlayerWin(player: Player) {
        dealerWinMap[Result.LOSE] = dealerWinMap.getOrDefault(Result.LOSE, 0) + 1
        playersWinMap[player] = Result.WIN
    }

    private fun dealerWinAndPlayerLose(player: Player) {
        dealerWinMap[Result.WIN] = dealerWinMap.getOrDefault(Result.WIN, 0) + 1
        playersWinMap[player] = Result.LOSE
    }
}
