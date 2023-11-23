package blackjack.view

import blackjack.domain.game.BlackJackGameResult
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerGroup

object ResultView {
    private const val GAME_START_MESSAGE = "에게 2장의 카드를 나눠주었습니다."

    fun printStartMessage(playerGroup: PlayerGroup) {
        println(playerGroup.players.joinToString(separator = ",") { it.name } + GAME_START_MESSAGE)
        playerGroup.players.forEach {
            println(it.view())
        }
        println()
    }

    fun printPlayerState(player: Player) {
        println(player.view())
    }

    fun printGameResult(blackJackGameResult: BlackJackGameResult) {
        println(blackJackGameResult.view())
    }
}
