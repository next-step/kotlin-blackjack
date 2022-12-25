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
import blackjack.model.GameResult
import blackjack.model.PlayerGameResult
import blackjack.model.PlayerGameResults

object ResultView {
    fun printInitialCards(players: Players, dealer: Dealer) {
        println("${dealer.name}와 ${players.value.joinToString { it.name }}에게 ${INITIAL_CARDS_COUNT}장의 카드를 나누었습니다.")
        println(
            "${dealer.name}: ${
                dealer.play
                    .cards
                    .value[0]
                    .let { "${it.type.value}${it.shape.string()}" }
            }"
        )
        players.value.forEach { println(getPlayerInfo(it.name, it.play.cards.value)) }
        println()
    }

    fun printPlayerCards(player: Player) {
        println(getPlayerInfo(player.name, player.play.cards.value))
    }

    fun printPlayerResult(players: Players, dealer: Dealer) {
        println()
        println("${getPlayerInfo(dealer.name, dealer.play.cards.value)} - 결과: ${dealer.play.score()}")
        players.value.forEach { println("${getPlayerInfo(it.name, it.play.cards.value)} - 결과: ${it.play.score()}") }
    }

    fun printGameResult(results: PlayerGameResults) {
        println()
        println("## 최종 승패")
        results.value
            .forEach {
                when (it) {
                    is PlayerGameResult.Player -> println("${it.name}: ${it.gameResult.string()}")
                    is PlayerGameResult.Dealer -> println("${it.name}: ${it.win}승 ${it.push}무 ${it.lose}패")
                }
            }
    }

    fun printDealerHit() {
        println()
        println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
    }

    private fun getPlayerInfo(name: String, cards: List<Card>) =
        "${name}카드: ${cards.joinToString { "${it.type.value}${it.shape.string()}" }}"

    private fun GameResult.string() = when (this) {
        GameResult.WIN -> "승"
        GameResult.PUSH -> "무"
        GameResult.LOSE -> "패"
    }

    private fun CardShape.string() = when (this) {
        SPADE -> "스페이드"
        HEART -> "하트"
        DIAMOND -> "다이아"
        CLOVER -> "클로버"
    }
}
