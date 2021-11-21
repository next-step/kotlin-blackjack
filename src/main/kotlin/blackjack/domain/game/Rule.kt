package blackjack.domain.game

import blackjack.domain.player.Player
import blackjack.domain.player.Players

class Rule() {

    fun judge(players: Players): Map<Player, List<Score>> {
        val result = mutableMapOf<Player, List<Score>>()
        if (checkDealerLose(players, result)) return result.toMap()
        judgeGamerScore(players, result)
        return result.toMap()
    }

    private fun judgeGamerScore(
        players: Players,
        result: MutableMap<Player, List<Score>>,
    ) {
        val dealer = players.getDealer()
        val gamers = players.getGamers()
        requireNotNull(dealer)
        require(gamers.isNotEmpty())
        val dealerPoint = MAX_SCORE - dealer.getHighestPoint()

        gamers.forEach { gamer ->
            putIfOverMaxScore(dealer, gamer, result)
            putIfGamerWin(dealer, dealerPoint, gamer, result)
            putIfDealerWin(dealer, dealerPoint, gamer, result)
            putIfDraw(dealer, dealerPoint, gamer, result)
        }
    }

    private fun putIfDraw(
        dealer: Player,
        dealerPoint: Int,
        gamer: Player,
        result: MutableMap<Player, List<Score>>
    ) {
        val gamerPoint = MAX_SCORE - gamer.getHighestPoint()
        if (dealerPoint == gamerPoint) {
            result[dealer] = result.getOrDefault(dealer, emptyList()) + Score.DRAW
            result[gamer] = listOf(Score.DRAW)
        }
    }

    private fun putIfDealerWin(
        dealer: Player,
        dealerPoint: Int,
        gamer: Player,
        result: MutableMap<Player, List<Score>>
    ) {
        val gamerPoint = MAX_SCORE - gamer.getHighestPoint()
        if (dealerPoint < gamerPoint) {
            result[dealer] = result.getOrDefault(dealer, emptyList()) + Score.WIN
            result[gamer] = listOf(Score.LOSE)
        }
    }

    private fun putIfGamerWin(
        dealer: Player,
        dealerPoint: Int,
        gamer: Player,
        result: MutableMap<Player, List<Score>>
    ) {
        val gamerPoint = MAX_SCORE - gamer.getHighestPoint()
        if (gamerPoint in 1 until dealerPoint) {
            result[dealer] = result.getOrDefault(dealer, emptyList()) + Score.LOSE
            result[gamer] = listOf(Score.WIN)
        }
    }

    private fun putIfOverMaxScore(
        dealer: Player,
        gamer: Player,
        result: MutableMap<Player, List<Score>>
    ) {
        if (gamer.getHighestPoint() > MAX_SCORE) {
            result[dealer] = result.getOrDefault(dealer, emptyList()) + Score.WIN
            result[gamer] = listOf(Score.LOSE)
        }
    }

    private fun checkDealerLose(
        players: Players,
        result: MutableMap<Player, List<Score>>
    ): Boolean {
        val dealer = players.getDealer()
        val gamers = players.getGamers()
        requireNotNull(dealer)

        if (dealer.getHighestPoint() > MAX_SCORE) {
            return setGamerWin(gamers, dealer, result)
        }
        return false
    }

    private fun setGamerWin(
        gamers: List<Player>,
        dealer: Player,
        result: MutableMap<Player, List<Score>>
    ): Boolean {
        for (player in gamers) {
            result[player] = listOf(Score.WIN)
            result[dealer] = result.getOrDefault(dealer, emptyList()) + Score.LOSE
        }
        return true
    }

    companion object {
        private const val MAX_SCORE = 21
    }
}
