package blackjack.view

import blackjack.domain.BlackjackGame.Companion.GAME_START_CARD_COUNT
import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.result.GameResults

fun printLine() {
    println()
}

fun printUserNames(names: String) {
    println("딜러와 ${names}에게 ${GAME_START_CARD_COUNT}장의 카드를 나누었습니다.")
}

fun printUserCards(userName: String, cards: Cards) {
    println("${userName}카드: ${cardsNames(cards.cards)}")
}

fun printResults(name: String, cards: List<Card>, cardValues: Int) {
    println("${name}카드: ${cardsNames(cards)} - 결과: $cardValues")
}

fun printBlackjackResult(blackjackResult: GameResults) {
    val dealerResult = blackjackResult.dealerResult

    println("## 최종 수익")
    println("딜러: ${dealerResult.finalRevenue}")
    for (playerResult in blackjackResult.playerResults) {
        println("${playerResult.name}: ${playerResult.finalRevenue}")
    }
}

private fun cardsNames(cards: List<Card>): String {
    return cards.joinToString { card ->
        "${card.denominationName}${card.suit.suitName}"
    }
}
