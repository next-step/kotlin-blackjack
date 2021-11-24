package blackjack.domain.result

import blackjack.domain.gamer.Gamers
import blackjack.domain.gamer.Player

class PlayerResult(
    private val gamers: Gamers,
) {

    fun judgePlayerResult(): List<Player> {
        val playerResults = mutableListOf<Player>()
        val dealer = gamers.getDealer()
        val players = gamers.getPlayers()
        for (player in players) {
            val playerResult = when (dealer.judgeGameResult(player)) {
                DealerResultType.WIN -> player.lose()
                DealerResultType.PUSH -> player.push()
                DealerResultType.LOSE -> player.win()
            }
            playerResults.add(playerResult)
        }
        return playerResults.toList()
    }
}
