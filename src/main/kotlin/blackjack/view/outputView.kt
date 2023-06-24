package blackjack.view

import blackjack.domain.GameResult
import blackjack.domain.Hands
import blackjack.domain.card.Card

private const val PLAYER_NAME_DELIMITER = ", "
private const val CARD_DELIMITER = ", "

fun printFirstDrawResult(hands: List<Hands>) {
    println("${parsePlayerNames(hands)}에게 2장의 카드를 나누었습니다.")
    hands.forEach { printPlayerDrawResult(it) }
}

fun printCurrentDrawResult(hands: Hands) = println("${hands.playerName}카드: ${parseCardsResult(hands.cards)}")

fun printGameResults(gameResults: List<GameResult>) = gameResults.forEach { printGameResult(it) }

private fun parsePlayerNames(hands: List<Hands>) = hands.joinToString(PLAYER_NAME_DELIMITER) { it.playerName }

private fun printPlayerDrawResult(hands: Hands) = println("${hands.playerName}카드: ${parseCardsResult(hands.cards)}")

private fun printGameResult(gameResult: GameResult) {
    println("${gameResult.playerName}카드: ${parseCardsResult(gameResult.cards)}} - 결과: ${gameResult.score}")
}

private fun parseCardsResult(cards: List<Card>) = cards.joinToString(CARD_DELIMITER) { parseCardView(it) }
