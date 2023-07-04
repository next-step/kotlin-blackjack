package blackjack.view

import blackjack.domain.BlackjackGame.Companion.GAME_START_CARD_COUNT
import blackjack.domain.Card
import blackjack.domain.Cards
import blackjack.domain.User

fun printLine() {
    println()
}

fun printUserNames(names: String) {
    println("${names}에게 ${GAME_START_CARD_COUNT}장의 카드를 나누었습니다.")
}

fun printUserCards(userName: String, cards: Cards) {
    println("${userName}카드: ${cardsNames(cards.cards)}")
}

fun printResults(name: String, cards: List<Card>, cardValues: Int) {
    println("${name}카드: ${cardsNames(cards)} - 결과: $cardValues")
}

private fun cardsNames(cards: List<Card>): String {
    return cards.joinToString { card ->
        "${card.denominationName}${card.suit.suitName}"
    }
}
