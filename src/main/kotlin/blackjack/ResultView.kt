package blackjack

import blackjack.domain.Dealer
import blackjack.domain.Game.Companion.INITIAL_CARDS_COUNT
import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.model.Card
import blackjack.model.CardShape
import blackjack.model.CardShape.CLOVER
import blackjack.model.CardShape.DIAMOND
import blackjack.model.CardShape.HEART
import blackjack.model.CardShape.SPADE

object ResultView {
    fun printInitialCards(players: Players, dealer: Dealer) {
        println("딜러와 ${players.value.joinToString { it.name }}에게 ${INITIAL_CARDS_COUNT}장의 카드를 나누었습니다.")
        println("딜러: ${getPlayerInfo("딜러", dealer.cards.value.filterIndexed { index, _ -> index != 0 })}")
        players.value.forEach { println(getPlayerInfo(it.name, it.cards.value)) }
        println()
    }

    fun printPlayerCards(player: Player) = println(getPlayerInfo(player.name, player.cards.value))

    fun printResult(players: Players, dealer: Dealer) {
        println("${getPlayerInfo("딜러", dealer.cards.value)} - 결과: ${dealer.sumCards()}")
        players.value.forEach { println("${getPlayerInfo(it.name, it.cards.value)} - 결과: ${it.sumCards()}") }
    }

    private fun getPlayerInfo(name: String, cards: List<Card>) =
        "${name}카드: ${cards.joinToString { "${it.type.value}${it.shape.string()}" }}"

    private fun CardShape.string() = when (this) {
        SPADE -> "스페이드"
        HEART -> "하트"
        DIAMOND -> "다이아"
        CLOVER -> "클로버"
    }
}
