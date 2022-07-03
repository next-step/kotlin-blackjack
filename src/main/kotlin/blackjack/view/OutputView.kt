package blackjack.view

import blackjack.domain.deck.Card
import blackjack.domain.deck.CardPattern
import blackjack.domain.deck.CardPattern.CLOVER
import blackjack.domain.deck.CardPattern.DIAMOND
import blackjack.domain.deck.CardPattern.HEART
import blackjack.domain.deck.CardPattern.SPADE
import blackjack.domain.participant.Player

class OutputView {

    fun printInitHands(players: List<Player>) {
        println()
        println("${joinPlayerNames(players)}에게 2장의 카드를 나누었습니다.")
        players.forEach { player -> printPlayerHand(player) }
    }

    fun printPlayerHand(player: Player) {
        printHand(player.getPlayerNameValue(), player.cards())
        println()
    }

    fun printResult(players: List<Player>) {
        println()
        players.forEach { player ->
            printHand(player.getPlayerNameValue(), player.cards())
            println(" - 결과: ${player.getScore()}")
        }
    }

    private fun printHand(name: String, cards: List<Card>) {
        print("${name}카드: ${joinPlayerHandCards(cards)}")
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
