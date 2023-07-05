package blackjack.view

import blackjack.domain.game.Game
import blackjack.domain.game.GameResult
import blackjack.domain.player.Dealer
import blackjack.domain.player.Gamer
import blackjack.domain.player.Gamers
import blackjack.domain.player.Player

object OutputView {

    fun printInputGameParticipant() {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
    }

    fun printDivideIntoTwoPieces(gamers: Gamers) {
        val names = gamers.gamers.map { it.name }
        val result = names.joinToString(", ")
        println("\n딜러와 ${result}에게 ${Game.INIT_CARD_COUNT}장을 나누었습니다.")
    }

    fun printCardsDealerHas(dealer: Dealer) {
        val deck = dealer.deck.getCards().joinToString(", ")
        println("${dealer.name}: $deck")
    }

    fun printCardsGamerHas(gamer: Gamer) {
        val deck = gamer.deck.getCards().joinToString(", ")
        println("${gamer.name}카드: $deck")
    }

    fun printOneMoreCard(gamer: Gamer) {
        println("${gamer.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
    }

    fun printMessage(message: String) {
        println(message)
    }

    fun printGameResult(game: Game) {
        println()
        printDealerResult(game)
        printGamerResult(game)
    }

    private fun printDealerResult(game: Game) {
        val dealer = game.dealer
        println("${dealer.name}카드: ${joinToString(dealer)} - 결과: ${dealer.deck.getScore()}")
    }

    private fun joinToString(player: Player) = player.deck.getCards().joinToString(", ")

    private fun printGamerResult(game: Game) {
        for (gamer in game.gamers.gamers) {
            val deck = joinToString(gamer)
            println("${gamer.name}카드: $deck - 결과: ${gamer.deck.getScore()}")
        }
    }

    fun printDealerDrawsCard() {
        println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.")
    }

    fun printFinalResult(dealerResult: MutableList<GameResult>, gamerResults: Map<String, GameResult>) {
        println("\n## 최종 승패")
        getDealerFinalResult(dealerResult)
        getGamersFinalResult(gamerResults)
    }

    private fun getDealerFinalResult(dealerResult: MutableList<GameResult>) {
        val result = dealerResult.groupingBy { it }
            .eachCount()
            .map { (result, count) ->
                "$count${result.message}"
            }
            .joinToString(" ")
        println("딜러: $result")
    }

    private fun getGamersFinalResult(gamerResults: Map<String, GameResult>) {
        gamerResults.map { (key, value) ->
            println("$key: ${value.message}")
        }
    }

    fun printNewLine() {
        println()
    }
}
