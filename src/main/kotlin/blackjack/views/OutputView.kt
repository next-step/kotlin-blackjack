package blackjack.views

import blackjack.domain.game.Bettings
import blackjack.domain.game.Score
import blackjack.domain.game.ScoreResult
import blackjack.domain.player.Dealer
import blackjack.domain.player.Gamer
import blackjack.domain.player.Player
import blackjack.domain.player.Players

object OutputView {

    fun printInitPhase(players: Players) {
        val initPhasePlayers = players.players
        val names = initPhasePlayers.joinToString() { it.getPlayerName().name.toString() }
        println()
        println("$names$PRINT_CARD_INIT_PHASE")
        printPlayersCards(initPhasePlayers)
        println()
    }

    fun printPlayingPhase(players: Players) {
        val playingPhasePlayers = players.players
        printCardWithPoint(playingPhasePlayers)
    }

    fun printCards(player: Player) {
        val (playerName, cardResult) = getCardResult(player)
        println("$playerName: $cardResult")
    }

    fun printDealerCardReceived() {
        println(PRINT_DEALER_CARD_RECEIVED)
    }

    fun printGameResult(scoreResult: ScoreResult) {
        println(GAME_RESULT)
        for (entry in scoreResult.score) {
            val person = entry.key
            printDealer(person, scoreResult.score)
        }
        for (entry in scoreResult.score) {
            val person = entry.key
            printGamer(person, scoreResult.score)
        }
    }

    fun printProfit(bettings: Bettings) {
        println(FINAL_PROFIT)
        for (betting in bettings.bettings) {
            println("${betting.player.getPlayerName()}: ${betting.credit.value}")
        }
    }

    private fun printDealer(
        person: Player,
        result: Map<Player, List<Score>>
    ) {
        if (person is Dealer) {
            val results = result[person].orEmpty()
            val scores = results.groupingBy { it }.eachCount()
            printDealerScore(scores)
        }
    }

    private fun printDealerScore(
        scores: Map<Score, Int>
    ) {
        val sb = StringBuilder()
        sb.append(DEALER)
        for (score in scores) {
            sb.append("${score.value}${score.key.value} ")
        }
        println(sb)
    }

    private fun printGamer(
        person: Player,
        result: Map<Player, List<Score>>
    ) {
        if (person is Gamer) {
            val score = result[person].orEmpty()
            println("${person.profile.name}: ${score[GAMER_SCORE].value}")
        }
    }

    private fun printPlayersCards(players: List<Player>) {
        players.forEach {
            val (playerName, cardResult) = getCardResult(it)
            println("$playerName: $cardResult")
        }
    }

    private fun printCardWithPoint(players: List<Player>) {
        println()
        players.forEach {
            val (playerName, cardResult) = getCardResult(it)
            println("$playerName: $cardResult $PRINT_POINT_RESULT ${it.getHighestPoint()}")
        }
    }

    private fun getCardResult(player: Player): Pair<String?, StringBuilder> {
        val playerName = player.getPlayerName().name
        val playerCards = player.openCards().cards
        val cardResult = StringBuilder()
        cardResult.apply {
            append(playerCards.joinToString() { it.denomination.mark + it.suit.value })
        }
        return Pair(playerName, cardResult)
    }

    private const val PRINT_CARD_INIT_PHASE = "에게 2장을 나누었습니다."
    private const val PRINT_POINT_RESULT = "- 결과:"
    private const val PRINT_DEALER_CARD_RECEIVED = "딜러는 16이하라 한장의 카드를 더 받았습니다."
    private const val GAME_RESULT = "\n## 최종 승패"
    private const val GAMER_SCORE = 0
    private const val DEALER = "딜러:"
    private const val FINAL_PROFIT = "## 최종수익"
}
