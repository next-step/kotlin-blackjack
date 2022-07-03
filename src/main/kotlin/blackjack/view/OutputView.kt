package blackjack.view

import blackjack.domain.deck.Card
import blackjack.domain.deck.CardPattern
import blackjack.domain.deck.CardPattern.CLOVER
import blackjack.domain.deck.CardPattern.DIAMOND
import blackjack.domain.deck.CardPattern.HEART
import blackjack.domain.deck.CardPattern.SPADE
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Player

class OutputView {

    fun printInitCards(dealer: Dealer, players: List<Player>) {
        println()
        println("딜러와 ${joinPlayerNames(players)}에게 2장의 카드를 나누었습니다.")
        printCards("딜러", dealer.cards())
        println()
        players.forEach { player -> printPlayerCards(player) }
    }

    fun printPlayerCards(player: Player) {
        printCards(player.getPlayerNameValue(), player.cards())
        println()
    }

    private fun printCards(name: String, cards: List<Card>) {
        print("${name}카드: ${joinCards(cards)}")
    }

    private fun joinPlayerNames(players: List<Player>) = players.joinToString { it.getPlayerNameValue() }

    private fun joinCards(cards: List<Card>): String =
        cards.joinToString { card -> card.number.expression + convertCardPattern(card.pattern) }

    private fun convertCardPattern(cardPattern: CardPattern) = when (cardPattern) {
        HEART -> "하트"
        DIAMOND -> "다이아"
        CLOVER -> "클로버"
        SPADE -> "스페이드"
    }

    fun printDealerReceiveCardNotOverLimitScore() {
        println()
        println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
    }

    fun printResult(dealer: Dealer, players: List<Player>) {
        println()
        println("딜러 카드: ${joinCards(dealer.cards())} - 결과: ${dealer.getScore()}")
        players.forEach { player ->
            printCards(player.getPlayerNameValue(), player.cards())
            println("- 결과: ${player.getScore()}")
        }
    }
}
