package blackjack.view

import blackjack.BlackJackGame
import blackjack.GameResult.LOSE
import blackjack.GameResult.WIN
import blackjack.ParticipantResult
import blackjack.participant.Player

object OutputView {
    fun printPlayersStartCardPack(game: BlackJackGame) {
        val dealer = game.dealer
        val gamePlayer = game.gamePlayers.player
        println("\n${dealer.name.value}와 ${gamePlayer.joinToString { it.name.value }}에게 2장의 카드를 나누었습니다.")
        println("${dealer.name.value}: ${dealer.cards.getOpenCard()}")
        gamePlayer.forEach { println("$it") }
        println()
    }

    fun printPlayerCard(player: Player) {
        println(player)
    }

    fun printParticipantCardsResult(players: List<Player>) {
        println()
        players.forEach { println("$it - 결과:${it.score()}") }
    }

    fun printBlackJackResult(results: List<ParticipantResult>) {
        println("\n## 최종 승패")
        val groupedResults = results.groupBy { it.participant.name }
            .mapValues { entry ->
                val wins = entry.value.count { it.result == WIN }
                val losses = entry.value.count { it.result == LOSE }
                wins to losses
            }
        groupedResults.forEach { (playerName, winLoss) ->
            val (wins, losses) = winLoss
            println("${playerName.value}: ${wins}승, ${losses}패")
        }
    }
}
