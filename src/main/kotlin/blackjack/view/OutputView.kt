package blackjack.view

import blackjack.BlackJackGame
import blackjack.GameResult
import blackjack.GameResult.*
import blackjack.ParticipantResult
import blackjack.participant.Player

object OutputView {
    fun printPlayersStartCardPack(game: BlackJackGame) {
        val dealer = game.dealer
        val gamePlayer = game.gamePlayers.players
        val playerNames = gamePlayer.joinToString { it.getName().value }
        println("\n${dealer.getName().value}와 ${playerNames}에게 2장의 카드를 나누었습니다.")
        println("${dealer.getName().value}: ${dealer.cards.getOpenCard()}")
        gamePlayer.forEach { println("$it") }
        println()
    }

    fun printPlayerCard(player: Player) {
        println(player)
    }

    fun printParticipantCardsResult(game: BlackJackGame) {
        println("${game.dealer} - 결과:${game.dealer.score()}")
        game.gamePlayers.players.forEach { println("$it - 결과:${it.score()}") }
    }

    fun printBlackJackResult(results: List<ParticipantResult>) {
        println("\n## 최종 승패")

        val dealerResults = getDealerResult(results)
        val dealerWins = dealerResults.count { it == WIN }
        val dealerLosses = dealerResults.count { it == LOSE }
        println("딜러: ${dealerWins}승 ${dealerLosses}패")

        val playerResults = results.filter { it.participant.getName().value != "딜러" }
            .groupBy { it.participant.getName() }
            .mapValues { entry ->
                entry.value.map { it.result }
            }
        playerResults.forEach { (playerName, resultList) ->
            val result = when {
                resultList.contains(WIN) -> "승"
                resultList.contains(LOSE) -> "패"
                else -> "무"
            }
            println("${playerName.value}: $result")
        }
    }

    private fun getDealerResult(results: List<ParticipantResult>): List<GameResult> {
        return results.filter { it.participant.getName().value == "딜러" }.map { it.result }
    }
}
