package blackjack.view

import blackjack.domain.BlackJackResult
import blackjack.domain.card.Card
import blackjack.domain.player.Player

object ResultView {

    fun printlnBlackJackInit(players: List<Player>) {
        println("${players.map(Player::name).joinToString(",")}에게 2장의 나누었습니다.")
    }

    fun printlnPlayersWithCards(players: List<Player>) {
        players.forEach { player ->
            printlnPlayerWithCards(player.name, player.cards)
        }.also { println() }
    }

    fun printlnPlayerWithCards(name: String, cards: List<Card>) {
        printPlayerWithCards(name, cards).also { println() }
    }

    fun printResult(result: BlackJackResult) {
        println()
        result.playerResults.forEach {
            printPlayerWithCards(it.playerName, it.cards)
            println(" - 결과: ${it.score.value}")
        }
    }

    private fun printPlayerWithCards(name: String, cards: List<Card>) {
        print("${name}카드: ${cards.joinToString(", ") { "${it.denomination.name}${it.suit.description}" }}")
    }
}
