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
    }

    fun printCardStatus(player: Player) {
        val cardViews = player.cards.cards.map {
            cardView(it)
        }
        println("${player.name.name}카드 : ${cardViews.joinToString()}")
    }

    private fun cardView(card: Card) = CardNumberView.valueOf(card.number) + CardShapeView.valueOf(card.shape)


}

