package blackjack.view

import blackjack.domain.BlackjackGame
import blackjack.domain.Player
import blackjack.domain.card.Card

object OutputView {

    fun printInitGame(blackJackGame: BlackjackGame) {
        printPlayersName(blackJackGame)
        blackJackGame.players.forEach { player -> printCardsInHand(player) }
    }

    private fun printPlayersName(blackJackGame: BlackjackGame) {
        println(blackJackGame.players.joinToString(transform = Player::name) + "에게 2장의 카드를 나누었습니다.")
    }

    fun printCardsInHand(player: Player) {
        print("${player.name}카드: ")
        println(player.cards.joinToString(transform = Card::print))
    }
}
