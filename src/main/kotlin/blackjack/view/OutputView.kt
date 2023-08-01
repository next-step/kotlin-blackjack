package blackjack.view

import blackjack.domain.BlackjackGame
import blackjack.domain.player.Player
import blackjack.domain.card.Card

object OutputView {

    fun printInitGame(blackJackGame: BlackjackGame) {
        printPlayersName(blackJackGame)
        blackJackGame.players.forEach { player -> printCardsInHandWithEmptyLine(player) }
    }

    private fun printPlayersName(blackJackGame: BlackjackGame) {
        println(blackJackGame.players.joinToString(transform = Player::name) + "에게 2장의 카드를 나누었습니다.")
    }

    fun printCardsInHandWithEmptyLine(player: Player) {
        printPlayerCards(player)
        println()
    }

    private fun printPlayerCards(player: Player) {
        print("${player.name}카드: ")
        print(player.cards.joinToString(transform = Card::print))
    }

    fun printResult(players: List<Player>) {
        players.forEach { player ->
            printPlayerCards(player)
            println(" - 결과: ${player.sum()}")
        }
    }
}
