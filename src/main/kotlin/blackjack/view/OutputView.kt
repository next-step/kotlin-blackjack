package blackjack.view

import blackjack.domain.Players

class OutputView {
    fun dealResult(players: Players) {
        val playerNames = players.allPlayers().map { it.name }

        println(String.format(DEAL_RESULT_MESSAGE, playerNames))
        players.allPlayers().forEach {
            println("${it.name}카드: ${it.ownedCards}")
        }
    }

    companion object {
        private const val DEAL_RESULT_MESSAGE = "%s 에게 2장의 카드를 카드를 나누었습니다."
    }
}
