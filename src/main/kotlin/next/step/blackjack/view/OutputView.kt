package next.step.racing.view

import next.step.blackjack.domain.Player

object OutputView {

    private const val UNKNOWN_ERR_MSG = "알 수 없는 에러가 발생했습니다."

    fun showStart(players: Set<Player>, cardCnt: Int) {
        println()
        val playerNames = players.joinToString(", ") { it.name }
        println("${playerNames}에게 ${cardCnt}장씩 나누었습니다.")
        players.forEach {
            showPlayerCards(it)
        }
        println()
    }

    fun showPlayerCards(it: Player) {
        println(cardDescs(it))
    }

    private fun cardDescs(player: Player): String {
        val cardDescs = player.cardDescs().joinToString(", ")
        val playerCardsFormat = "${player.name}카드: $cardDescs"
        return playerCardsFormat
    }

    fun showResult(players: Set<Player>) {
        println()
        players.forEach {
            println("${cardDescs(it)} - 결과: ${it.point()}")
        }
    }

    fun showError(msg: String?) {
        println(msg ?: UNKNOWN_ERR_MSG)
        println()
    }
}
