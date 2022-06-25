package blackjack.view

import blackjack.domain.deck.Card
import blackjack.domain.deck.CardPattern
import blackjack.domain.deck.CardPattern.CLOVER
import blackjack.domain.deck.CardPattern.DIAMOND
import blackjack.domain.deck.CardPattern.HEART
import blackjack.domain.deck.CardPattern.SPADE
import blackjack.domain.player.Player

class OutputView {

    fun printInitHands(players: List<Player>) {
        println()
        println("${joinPlayerNames(players)}에게 2장의 카드를 나누었습니다.")
        players.forEach { player ->
            print(player.getPlayerNameValue())
            println("카드: ${joinPlayerHandCards(player.hand)}")
        }
    }

    private fun joinPlayerNames(players: List<Player>) = players.joinToString { it.getPlayerNameValue() }

    private fun joinPlayerHandCards(hand: List<Card>): String =
        hand.joinToString { card -> card.number.expression + convertCardPattern(card.pattern) }

    private fun convertCardPattern(cardPattern: CardPattern) = when (cardPattern) {
        HEART -> "하트"
        DIAMOND -> "다이아"
        CLOVER -> "클로버"
        SPADE -> "스페이드"
    }
}
