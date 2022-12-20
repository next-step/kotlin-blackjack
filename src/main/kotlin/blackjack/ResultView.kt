package blackjack

import blackjack.domain.GameDealer
import blackjack.domain.Game.Companion.INITIAL_CARDS_COUNT
import blackjack.domain.GamePlayer
import blackjack.domain.GamePlayers
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
    fun printInitialCards(gamePlayers: GamePlayers, gameDealer: GameDealer) {
        println("${GameDealer.NAME}와 ${gamePlayers.value.joinToString { it.name }}에게 ${INITIAL_CARDS_COUNT}장의 카드를 나누었습니다.")
        println(
            "${GameDealer.NAME}: ${
                gameDealer.cards
                    .value[0]
                    .let { "${it.type.value}${it.shape.string()}" }
            }"
        )
        gamePlayers.value.forEach { println(getPlayerInfo(it.name, it.cards.value)) }
        println()
    }

    fun printPlayerCards(gamePlayer: GamePlayer) {
        println(getPlayerInfo(gamePlayer.name, gamePlayer.cards.value))
        println()
    }

    fun printPlayerResult(gamePlayers: GamePlayers, gameDealer: GameDealer) {
        println("${getPlayerInfo(GameDealer.NAME, gameDealer.cards.value)} - 결과: ${gameDealer.sumCards()}")
        gamePlayers.value.forEach { println("${getPlayerInfo(it.name, it.cards.value)} - 결과: ${it.sumCards()}") }
        println()
    }

    fun printGameResult(results: PlayerGameResults) {
        println("## 최종 승패")
        results.value
            .forEach {
                when (it) {
                    is PlayerGameResult.Player -> println("${it.name}: ${it.gameResult.string()}")
                    is PlayerGameResult.Dealer -> println("${it.name}: ${it.win}승 ${it.push}무 ${it.lose}패")
                }
            }
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
