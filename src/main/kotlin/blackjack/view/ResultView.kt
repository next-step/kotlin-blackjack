package blackjack.view

import blackjack.domain.Cards
import blackjack.domain.Player

object ResultView {

    fun printPlayers(players: List<String>) {
        println("${players}에게 2장의 카드를 나누었습니다.")
    }

    fun printPlayersAndCards(players: List<Player>) {
        players.forEach { player ->
            val cardsInfo = getCardsInfo(player.cards)
            println("${player.name}카드: $cardsInfo")
        }
    }

    fun printPlayerAndCards(player: Player) {
        val cardsInfo = getCardsInfo(player.cards)
        println("${player.name}카드: $cardsInfo")
    }

    fun printResultGame(players: List<Player>) {
        players.forEach { player ->
            val cardsInfo = getCardsInfo(player.cards)
            val cardsTotalValue = player.cards.calculateScore()
            println("${player.name}카드: $cardsInfo - 결과: $cardsTotalValue")
        }
    }

    private fun getCardsInfo(cards: Cards): String {
        var cardsInfo = ""

        cards.cards.forEachIndexed { index, card ->
            cardsInfo += "${card.rank.value}${card.symbol.symbolName}"
            if (index != cards.cards.lastIndex) {
                cardsInfo += ", "
            }
        }
        return cardsInfo
    }

    fun printEnter() {
        println()
    }
}
