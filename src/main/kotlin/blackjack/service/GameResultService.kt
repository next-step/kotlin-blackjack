package blackjack.service

import blackjack.domain.Dealer
import blackjack.domain.FightResult
import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.vo.GameResultVO

class GameResultService {
    private val fightService = FightService()

    fun allResult(dealer: Dealer, players: Players): GameResultVO {
        val dealerWinMap: MutableMap<FightResult, Int> = mutableMapOf()
        val playersWinMap: MutableMap<Player, FightResult> = mutableMapOf()

        players.players.forEach {
            when (fightService.go(dealer, it)) {
                FightResult.DRAW -> draw(it, dealerWinMap, playersWinMap)
                FightResult.WIN -> dealerLoseAndPlayerWin(it, dealerWinMap, playersWinMap)
                FightResult.LOSE -> dealerWinAndPlayerLose(it, dealerWinMap, playersWinMap)
            }
        }

        return GameResultVO(dealerWinMap, playersWinMap)
    }

    private fun draw(
        player: Player,
        dealerWinMap: MutableMap<FightResult, Int>,
        playersWinMap: MutableMap<Player, FightResult>,
    ) {
        dealerWinMap[FightResult.DRAW] = dealerWinMap.getOrDefault(FightResult.DRAW, 0) + 1
        playersWinMap[player] = FightResult.DRAW
    }

    private fun dealerLoseAndPlayerWin(
        player: Player,
        dealerWinMap: MutableMap<FightResult, Int>,
        playersWinMap: MutableMap<Player, FightResult>,
    ) {
        dealerWinMap[FightResult.LOSE] = dealerWinMap.getOrDefault(FightResult.LOSE, 0) + 1
        playersWinMap[player] = FightResult.WIN
    }

    private fun dealerWinAndPlayerLose(
        player: Player,
        dealerWinMap: MutableMap<FightResult, Int>,
        playersWinMap: MutableMap<Player, FightResult>,
    ) {
        dealerWinMap[FightResult.WIN] = dealerWinMap.getOrDefault(FightResult.WIN, 0) + 1
        playersWinMap[player] = FightResult.LOSE
    }
}
