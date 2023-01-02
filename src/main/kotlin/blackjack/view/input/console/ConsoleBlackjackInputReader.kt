package blackjack.view.input.console

import blackjack.domain.Blackjack
import blackjack.domain.player.Player
import blackjack.view.BlackjackView
import blackjack.view.input.BlackjackInputReader

class ConsoleBlackjackInputReader(
    private val blackjackView: BlackjackView,
) : BlackjackInputReader {

    override fun printlnPlayerNamesInputPrompt() {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
    }

    override fun printlnPlayerHitYnInputPrompt(player: Player) {
        println("${player.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
    }

    override fun printlnPlayerBettingAmountInputPrompt(playerName: String) {
        println("${playerName}의 베팅 금액은?")
    }

    override fun readPlayerNames(): List<String> {
        printlnPlayerNamesInputPrompt()

        return readln().split(",").map { it.trim() }
    }

    override fun readPlayersHitOrStay(blackjack: Blackjack, playerActionWhenHitYn: (Player, Boolean) -> Unit) {
        println()

        blackjack.getNotFinishedPlayers()
            .forEach { player ->
                printlnPlayerHitYnInputPrompt(player)
                askPlayerChoice(player, playerActionWhenHitYn)
            }

        println()
    }

    override fun readPlayerHitYn(): String =
        readln().trim()

    override fun readPlayerBettingAmounts(playerNames: List<String>): List<Double> =
        playerNames.map { playerName ->
            printlnPlayerBettingAmountInputPrompt(playerName)

            readln().trim().toDouble()
        }

    private fun askPlayerChoice(player: Player, playerActionWhenHitYn: (Player, Boolean) -> Unit) {
        while (player.isNotFinished()) {
            val isHit = when (val playerHitYn = readPlayerHitYn()) {
                "y", "Y" -> true
                "n", "N" -> false
                else -> throw IllegalArgumentException("should input y or n [$playerHitYn]")
            }

            playerActionWhenHitYn(player, isHit)

            blackjackView.printlnPlayer(player)
        }
    }
}
