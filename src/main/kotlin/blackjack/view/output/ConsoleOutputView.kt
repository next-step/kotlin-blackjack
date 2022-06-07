package blackjack.view.output

import blackjack.controller.BlackJackGame
import blackjack.model.card.Card
import blackjack.model.player.Player
import blackjack.model.player.Players

class ConsoleOutputView : OutputView {

    override fun printInitialMessage(players: Players) {
        val playerNames = players.joinToString(",") { it.name }
        println("${playerNames}에게 ${BlackJackGame.INITIAL_CARD_COUNT_OF_PLAYER}장씩 카드를 나누었습니다.")
        this.printCardsOfPlayer(players)
    }

    override fun printCardsOfPlayer(player: Player, withScore: Boolean) {
        print("${player.name} 카드: ")
        print(player.cards.joinToString(", ") { it.displayName })
        if (withScore) {
            print("  - 결과 : :${player.state.finalScore}")
        }
        println()
    }

    companion object {
        val Card.displayName: String
            get() = "${denomination.displayName}${shape.displayName}"
    }
}
