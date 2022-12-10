package ui

import domain.Card
import domain.Player
import domain.Players

object OutputView {

    fun printGameStartMsg(playerNames: List<String>) {
        println("${playerNames.joinToString()} 에게 2장의 나누었습니다.")
    }

    fun printCardStatus(players: Players) {
        players.players.forEach {
            this.printCardStatus(it)
        }
        println()
    }

    fun printCardStatus(player: Player) {
        val cardViews = player.cards.cards.map {
            cardView(it)
        }
        print("${player.name.name}카드 : ${cardViews.joinToString()}")
    }

    fun printCardStatusWithResult(players: List<Player>) {
        players.forEach {
            printCardStatus(it)
            println("- 결과: ${it.choiceBestScore()}")
        }
    }

    private fun cardView(card: Card) = CardNumberView.valueOf(card.number) + CardShapeView.valueOf(card.shape)
}
