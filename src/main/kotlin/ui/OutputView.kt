package ui

import domain.Card
import domain.Player
import domain.Players

object OutputView {

    fun printGameStartMsg(playerNames: List<String>) {
        println("${playerNames.joinToString()} 에게 2장의 나누었습니다.")
    }

    fun printCardStatus(players: Players, showResult: Boolean = false) {
        players.players.forEach {
            this.printCardStatus(it, showResult)
        }
    }

    fun printCardStatus(player: Player, showResult: Boolean = false) {
        val cardViews = player.cards.cards.map {
            cardView(it)
        }
        println("${player.name.name}카드 : ${cardViews.joinToString()} ${resultView(showResult, player)}")
    }

    private fun resultView(showResult: Boolean, player: Player) =
        if (showResult) "- 결과:" + player.choiceBestScore() else ""

    private fun cardView(card: Card) = CardNumberView.valueOf(card.number) + CardShapeView.valueOf(card.shape)
}
