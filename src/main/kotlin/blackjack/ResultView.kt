package blackjack

import blackjack.domain.Dealer
import blackjack.domain.Game.Companion.INITIAL_CARDS_COUNT
import blackjack.domain.GamePlayer
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
        println("${Dealer.NAME}와 ${players.value.joinToString { it.name }}에게 ${INITIAL_CARDS_COUNT}장의 카드를 나누었습니다.")
        println(
            "${Dealer.NAME}: ${
                getPlayerInfo(
                    Dealer.NAME,
                    dealer.cards.value.filterIndexed { index, _ -> index != 0 })
            }"
        )
        players.value.forEach { println(getPlayerInfo(it.name, it.cards.value)) }
        println()
    }

    fun printPlayerCards(gamePlayer: GamePlayer) {
        println(getPlayerInfo(gamePlayer.name, gamePlayer.cards.value))
    }

    fun printPlayerResult(players: Players, dealer: Dealer) {
        println("${getPlayerInfo(Dealer.NAME, dealer.cards.value)} - 결과: ${dealer.sumCards()}")
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
