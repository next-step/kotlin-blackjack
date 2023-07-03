package blackjack.ui

import blackjack.domain.Card
import blackjack.domain.Hand
import blackjack.domain.PlayerName

object HandPrinter {
    fun printAll(playerName: PlayerName, hand: Hand) {
        println("${playerName.value}카드: ${cardMessage(hand.getCards())}")
    }

    fun printFirstCard(playerName: PlayerName, hand: Hand) {
        println("${playerName.value}카드: ${displayCard(hand.getCards().first())}")
    }

    private fun cardMessage(cards: List<Card>) = cards.joinToString(
        separator = ", ",
        transform = { displayCard(it) }
    )

    private fun displayCard(card: Card) = "${card.cardNumber.displayName}${card.cardSuit.displayName}"
}
