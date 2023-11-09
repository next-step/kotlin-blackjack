package blackjack.view

import blackjack.domain.Card
import blackjack.domain.Player
import blackjack.domain.Suit

object OutputView {

    fun printInitCard(players: List<Player>) {
        println("${players.joinToString(", ") { it.name }}$PRINT_INIT_CARD")
    }

    fun printPlayersCard(players: List<Player>) {
        players.forEach {
            printPlayerCard(it)
        }
    }

    fun printPlayerBurst(name: String) {
        println("$name$PLAYER_BURST")
    }

    fun printPlayerCard(player: Player) {
        println(player.cardDisplay)
    }

    fun printBlackjackGameResult(result: BlackJackGameResult) {
        result.gameResult().forEach {
            println("${it["name"]}카드: ${it["cards"]}, 결과: ${it["score"]}")
        }
    }

    private fun Suit.suitName(): String {
        return when (this) {
            Suit.SPADE -> "스페이드"
            Suit.HEART -> "하트"
            Suit.DIAMOND -> "다이아몬드"
            Suit.CLUB -> "클로버"
        }
    }

    private const val PLAYER_BURST = "는 21점을 초과했습니다."

    private const val PRINT_INIT_CARD = "에게 2장의 나누었습니다."
    private val Player.cardDisplay get() = "${name}카드: ${cardSet.joinToString(", ") { it.display }}"
    val Card.display get() = "${rank.rankName}${suit.suitName()}"
}
