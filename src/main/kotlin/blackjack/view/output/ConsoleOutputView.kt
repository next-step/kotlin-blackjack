package blackjack.view.output

import blackjack.model.Config
import blackjack.model.card.Card
import blackjack.model.card.CardShape
import blackjack.model.player.Player
import blackjack.model.player.Players

class ConsoleOutputView : OutputView {

    override fun printInitialMessage(players: Players) {
        val playerNames = players.joinToString(",") { it.name }
        println("${playerNames}에게 ${Config.INITIAL_CARD_COUNT_OF_PLAYER}장씩 카드를 나누었습니다.")
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
        private val CardShape.displayName: String
            get() = when (this) {
                CardShape.SPADES -> "스페이드"
                CardShape.DIAMONDS -> "다이아몬드"
                CardShape.HEARTS -> "하트"
                CardShape.CLUBS -> "클로버"
            }

        val Card.displayName: String
            get() = "${denomination.displayName}${shape.displayName}"
    }
}
