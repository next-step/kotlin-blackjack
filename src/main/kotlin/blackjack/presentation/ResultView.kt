package blackjack.presentation

import blackjack.core.player.Dealer
import blackjack.core.player.MatchResult
import blackjack.core.player.Player
import blackjack.core.player.Players

object ResultView {
    fun printStandby(
        dealer: Dealer,
        players: Players,
    ) {
        val stringBuilder = StringBuilder()
        stringBuilder.append(dealer.name)
        stringBuilder.append(STRING_SEPARATOR)
        stringBuilder.append(getPlayerNames(players))
        stringBuilder.append(STRING_STANDBY)
        stringBuilder.append(STRING_NEWLINE)
        println(stringBuilder.toString())
        println(getPlayerCards(dealer))
        players.forEach {
            println(getPlayerCards(it))
        }
        println()
    }

    private fun getPlayerNames(players: Players): String {
        return players.map { it.name }
            .joinToString(STRING_SEPARATOR, STRING_BLANK, STRING_BLANK)
    }

    fun printPlayerCards(player: Player) {
        println(getPlayerCards(player))
        println()
    }

    fun printResult(
        dealer: Dealer,
        players: Players,
    ) {
        printPlayersScore(dealer, players)
        printMatchResult(dealer, players)
    }

    fun printProfits(
        dealer: Dealer,
        players: Players,
    ) {
        println(STRING_PROFIT)
        printProfit(dealer)
        players.forEach {
            printProfit(it)
        }
    }

    private fun printProfit(player: Player) {
        val stringBuilder = StringBuilder()
        stringBuilder.append(player.name)
        stringBuilder.append(STRING_COLON)
        stringBuilder.append(player.profitAmount)
        println(stringBuilder.toString())
    }

    private fun printPlayersScore(
        dealer: Dealer,
        players: Players,
    ) {
        players.forEach {
            printPlayerScore(it)
        }

        printPlayerScore(dealer)
    }

    private fun printMatchResult(
        dealer: Dealer,
        players: Players,
    ) {
        val stringBuilder = StringBuilder()
        stringBuilder.append(STRING_MATCH_RESULT)
        stringBuilder.append(STRING_NEWLINE)
        printDealerMatchResult(dealer, players, stringBuilder)
        players.forEach {
            printPlayerMatchResult(dealer, it, stringBuilder)
        }
        println(stringBuilder)
    }

    private fun printDealerMatchResult(
        dealer: Dealer,
        players: Players,
        stringBuilder: StringBuilder,
    ) {
        val matchResult =
            players.map { dealer.checkMatch(it) }
                .groupingBy { it }
                .eachCount()

        stringBuilder.append(dealer.name)
        stringBuilder.append(STRING_COLON)
        stringBuilder.append(MatchResult.WIN.result)
        stringBuilder.append(matchResult[MatchResult.WIN] ?: 0)
        stringBuilder.append(" ")

        stringBuilder.append(MatchResult.DRAW.result)
        stringBuilder.append(matchResult[MatchResult.DRAW] ?: 0)
        stringBuilder.append(" ")

        stringBuilder.append(MatchResult.LOSE.result)
        stringBuilder.append(matchResult[MatchResult.LOSE] ?: 0)
        stringBuilder.append(STRING_NEWLINE)
    }

    private fun printPlayerMatchResult(
        dealer: Dealer,
        player: Player,
        stringBuilder: StringBuilder,
    ) {
        stringBuilder.append(player.name)
        stringBuilder.append(STRING_COLON)
        stringBuilder.append(dealer.checkMatch(player).reverse().result)
        stringBuilder.append(STRING_NEWLINE)
    }

    private fun printPlayerScore(player: Player) {
        val stringBuilder = StringBuilder()
        stringBuilder.append(getPlayerCards(player))
        stringBuilder.append(STRING_RESULT)
        stringBuilder.append(player.point)
        println(stringBuilder.toString())
    }

    private fun getPlayerCards(player: Player): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append(player.name)
        stringBuilder.append(STRING_CARD)
        stringBuilder.append(getCardNames(player))
        return stringBuilder.toString()
    }

    fun getCardNames(player: Player): String {
        return player.cards.joinToString(",", "", "")
    }

    fun printDealerDraw() {
        println(STRING_DEALER_DRAW)
    }

    private const val STRING_CARD = "카드:"
    private const val STRING_RESULT = "- 결과:"

    private const val STRING_STANDBY = "에게 2장의 나누었습니다."
    private const val STRING_NEWLINE = "\n"
    private const val STRING_SEPARATOR = ","
    private const val STRING_BLANK = ""
    private const val STRING_COLON = ":"

    private const val STRING_DEALER_DRAW = "딜러는 16이하라 한장의 카드를 더 받았습니다."
    private const val STRING_MATCH_RESULT = "## 최종 승리##"
    private const val STRING_PROFIT = "## 최종 수익"
}
