package blackjack.view

import blackjack.domain.Player

object ResultView {

    fun printPlayers(players: List<String>) {
        println("${players}에게 2장의 카드를 나누었습니다.")
    }

    fun printPlayersAndCards(players: List<Player>) {
        players.forEach { player ->
            val cardsInfo = player.cards.extractCardsInfoAsString()
            println("${player.name}카드: $cardsInfo")
        }
    }

    fun printPlayerAndCards(player: Player) {
        val cardsInfo = player.cards.extractCardsInfoAsString()
        println("${player.name}카드: $cardsInfo")
    }

    fun printResultGame(players: List<Player>) {
        players.forEach { player ->
            val cardsInfo = player.cards.extractCardsInfoAsString()
            val cardsTotalValue = player.cards.calculateCardsTotalValue()
            println("${player.name}카드: $cardsInfo - 결과: $cardsTotalValue")
        }
    }

    fun printEnter() {
        println()
    }
}
