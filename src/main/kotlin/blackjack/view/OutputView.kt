package blackjack.view

import blackjack.flow.BlackJackGame
import blackjack.participant.GameResult.DRAW
import blackjack.participant.GameResult.LOSE
import blackjack.participant.GameResult.WIN
import blackjack.participant.Participants
import blackjack.participant.Player

object OutputView {
    fun printPlayersStartCardPack(game: BlackJackGame) {
        val dealer = game.participants.dealer
        val gamePlayer = game.participants.gamePlayers.players
        val playerNames = gamePlayer.joinToString { it.getName().value }
        println("\n${dealer.getName().value}와 ${playerNames}에게 2장의 카드를 나누었습니다.")
        println("${dealer.getName().value}: ${dealer.cardHolder.cards.getOpenCard()}")
        gamePlayer.forEach { println("$it") }
        println()
    }

    fun printPlayerCard(player: Player) {
        println(player)
    }

    fun printParticipantCardsResult(game: BlackJackGame) {
        println("${game.participants.dealer} - 결과:${game.participants.dealer.score()}")
        game.participants.gamePlayers.players.forEach { println("$it - 결과:${it.score()}") }
    }

    fun printBlackJackResult(results: Participants) {
        println("\n## 최종 승패")
        val dealerRate = results.getDealerRate()
        val dealerWins = dealerRate.getWinCount()
        val dealerLosses = dealerRate.getLoseCount()
        val dealerDraws = dealerRate.getDrawCount()
        println("딜러: ${dealerWins}승 ${dealerLosses}패 ${dealerDraws}무")

        val playersRate = results.getPlayersRate()
        playersRate.forEach { playerRate ->
            val playerName = playerRate.name.value
            val playerWins = playerRate.getWinCount()
            val playerLosses = playerRate.getLoseCount()

            val result =
                when {
                    playerWins > 0 -> WIN
                    playerLosses > 0 -> LOSE
                    else -> DRAW
                }
            println("$playerName: ${result.description}")
        }
    }

    fun printDealerMoreCard() {
        println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.\n")
    }
}
