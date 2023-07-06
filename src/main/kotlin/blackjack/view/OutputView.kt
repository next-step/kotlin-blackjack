package blackjack.view

import blackjack.domain.BlackJackGame
import blackjack.domain.Card
import blackjack.domain.Player

object OutputView {

    fun printInitGame(blackJackGame: BlackJackGame) {
        printPlayersName(blackJackGame)
        blackJackGame.players.forEach { player -> printCardsInHand(player) }
    }

    private fun printPlayersName(blackJackGame: BlackJackGame) {
        println(blackJackGame.players.joinToString(transform = Player::name) + "에게 2장의 카드를 나누었습니다.")
    }

    private fun printCardsInHand(player: Player) {
        print("${player.name}카드: ")
        println(player.cards.joinToString(transform = Card::print))
    }
}
