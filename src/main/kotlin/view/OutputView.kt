package view

import dto.PlayerDto
import dto.PlayersDto
import dto.ResultDto

object OutputView {
    private const val COMMA_SEPARATOR = ", "

    fun printStarted(dto: PlayersDto) {
        val players = dto.players
        val names = players.joinToString(COMMA_SEPARATOR) { it.name }
        println("\n${names}에게 각각 2장의 카드를 나누었습니다.")
        players.forEach { printPlayer(it) }
    }

    fun printResult(dto: ResultDto) {
        printPlayer(dto.dealer)
        dto.players.forEach { printPlayer(it) }
        printPlayersWin(dto)
        printPlayersProfit(dto)
    }

    fun printDealerMessage(drawable: Boolean) {
        val message = if (drawable)
            "딜러는 16이하라 한장의 카드를 더 받았습니다."
        else "딜러가 16초과라 카드를 더 받지 않습니다."
        println("\n$message\n")
    }

    fun printPlayer(player: PlayerDto) {
        val playerName = "${player.name}카드:"
        val playerCards = player.cards.joinToString(COMMA_SEPARATOR)
        val playerScore = "- 결과: ${player.score}"
        println("$playerName $playerCards $playerScore")
    }

    private fun printPlayersWin(dto: ResultDto) {
        println("\n## 최종 승패\n")
        printPlayerWin(dto.dealer.name, dto.dealerWin)
        dto.players.forEach { printPlayerWin(it.name, it.win) }
    }

    private fun printPlayersProfit(dto: ResultDto) {
        println("\n## 최종 수익\n")
        printPlayerProfit(dto.dealer.name, dto.dealerProfit)
        dto.players.forEach { printPlayerProfit(it.name, it.profit) }
    }

    private fun printPlayerWin(name: String, win: String) = println("$name: $win")
    private fun printPlayerProfit(name: String, profit: Double) = println("$name: $profit")
}
