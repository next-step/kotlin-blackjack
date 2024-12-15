package blackjack.view

import blackjack.domain.Player
import blackjack.domain.Players

object OutputView {
    fun showGameStart(players: Players) {
        val playerNames = players.allPlayers().map { it.name }

        println(String.format(DEAL_RESULT_MESSAGE, playerNames))
        players.allPlayers().forEach {
            println("${it.name}카드: ${it.ownedCards}")
        }
        println()
    }

    fun printPlayerCards(player: Player) {
        println(String.format(CURRENT_CARD_STATUS, player.name, player.ownedCards))
    }

    fun showGameResult(players: Players) {
        players.allPlayers().forEach {
            println(String.format(GAME_RESULT_MESSAGE, it.name, it.ownedCards, it.sumOfCard()))
        }
    }

    fun dealerHitResult() {
        println(DEALER_HIT_MESSAGE)
    }

    private const val DEAL_RESULT_MESSAGE = "%s 에게 2장의 카드를 카드를 나누었습니다."
    private const val CURRENT_CARD_STATUS = "%s카드: %s"
    private const val GAME_RESULT_MESSAGE = "%s카드: %s - 결과 %d"
    private const val DEALER_HIT_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다."
}
