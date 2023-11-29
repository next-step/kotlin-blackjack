package blackjack.view

import blackjack.domain.BlackJackGame
import blackjack.domain.CardList
import blackjack.domain.Player

object OutputView {
    private const val DELIMITER = ", "

    fun printCards(name: String, cards: CardList) {
        println("${name}카드: ${cards.cards.joinToString(DELIMITER)}")
    }

    fun printDrawTwoCards(players: List<Player>) {
        println("\n${players.joinToString(DELIMITER) { it.name }}에게 2장의 카드를 나누었습니다.")
        players.forEach { printCards(it.name, it.cards) }
    }

    fun printPlayersScore(players: List<Player>) {
        println()
        players.forEach { printPlayerScore(it, it.cards) }
    }

    private fun printPlayerScore(player: Player, cards: CardList) {
        println("${player.name}카드 : ${cards.cards.joinToString(DELIMITER)} - 결과: ${BlackJackGame.score(cards)}")
    }
}
