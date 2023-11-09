package blackjack.view

import blackjack.domain.Card
import blackjack.domain.Player

object OutputView {

    fun printInitCard(players: List<Player>) {
        println("${players.joinToString(", ") { it.name }}$PRINT_INIT_CARD")
    }

    fun printPlayersCard(players: List<Player>) {
        players.forEach {
            printPlayerCard(it)
        }
    }

    fun printPlayerCard(player: Player) {
        println(player.cardDisplay)
    }

    fun printBlackjackGameResult(result: BlackJackGameResult) {
        result.gameResult().forEach {
            println("${it["name"]}카드: ${it["cards"]}, 결과: ${it["score"]}")
        }
    }

    private const val PRINT_INIT_CARD = "에게 2장의 나누었습니다."
    private val Player.cardDisplay get() = "${name}카드: ${playerCard.cards.joinToString(", ") { it.display }}"
    val Card.display get() = "${rank.rankName}${suit.suitName}"
}
