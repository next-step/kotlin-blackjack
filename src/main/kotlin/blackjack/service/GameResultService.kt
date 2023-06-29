package blackjack.service

import blackjack.domain.Dealer
import blackjack.domain.FightResult
import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.vo.GameResultVO

class GameResultService {
    private val fightService = FightService()
    private val dealerWinMap: MutableMap<FightResult, Int> = mutableMapOf()
    private val playersWinMap: MutableMap<Player, FightResult> = mutableMapOf()

    fun allResult(dealer: Dealer, players: Players): GameResultVO {
        players.players.forEach {
            when (fightService.go(dealer, it)) {
                FightResult.DRAW -> draw(it)
                FightResult.WIN -> dealerLoseAndPlayerWin(it)
                FightResult.LOSE -> dealerWinAndPlayerLose(it)
            }
        }

        return GameResultVO(dealerWinMap, playersWinMap)
    }

    private fun draw(player: Player) {
        dealerWinMap[FightResult.DRAW] = dealerWinMap.getOrDefault(FightResult.DRAW, 0) + 1
        playersWinMap[player] = FightResult.DRAW
    }

    private fun dealerLoseAndPlayerWin(player: Player) {
        dealerWinMap[FightResult.LOSE] = dealerWinMap.getOrDefault(FightResult.LOSE, 0) + 1
        playersWinMap[player] = FightResult.WIN
    }

    private fun dealerWinAndPlayerLose(player: Player) {
        dealerWinMap[FightResult.WIN] = dealerWinMap.getOrDefault(FightResult.WIN, 0) + 1
        playersWinMap[player] = FightResult.LOSE
    }
}
