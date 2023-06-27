package blackjack.view

import blackjack.domain.DealerGameResult
import blackjack.domain.GameResult
import blackjack.domain.Hands
import blackjack.domain.PlayerGameResult
import blackjack.domain.card.Card

private const val PLAYER_NAME_DELIMITER = ", "
private const val CARD_DELIMITER = ", "

fun printFirstDrawResult(hands: List<Hands>) {
    println("${parsePlayerNames(hands)}에게 2장의 카드를 나누었습니다.")
    hands.forEach { printPlayerDrawResult(it) }
}

fun printCurrentDrawResult(hands: Hands) = println("${hands.playerName}카드: ${parseCardsResult(hands.cards)}")

fun printDealerGetDraw() = println("딜러는 16이하라 한장의 카드를 더 받았습니다.\n")

fun printGameResult(gameResult: GameResult) {
    printGameScoreResult(gameResult)
    println()
    println("## 최종 승패")
    printGameCompetition(gameResult.dealerGameResult)
    printCameCompetition(gameResult.playerGameResults)
}

private fun parsePlayerNames(hands: List<Hands>) = hands.joinToString(PLAYER_NAME_DELIMITER) { it.playerName }

private fun printPlayerDrawResult(hands: Hands) = println("${hands.playerName}카드: ${parseCardsResult(hands.cards)}")

private fun printGameScoreResult(gameResult: GameResult) {
    printGameScoreResult(gameResult.dealerGameResult)
    printGameScoreResult(gameResult.playerGameResults)
}

private fun printGameScoreResult(dealerGameResult: DealerGameResult)
    = printGameScoreResult(dealerGameResult.playerName, dealerGameResult.cards, dealerGameResult.score)

private fun printGameScoreResult(playerGameResults: List<PlayerGameResult>)
    = playerGameResults.forEach { printGameScoreResult(it) }

private fun printGameScoreResult(playerGameResult: PlayerGameResult)
    = printGameScoreResult(playerGameResult.playerName, playerGameResult.cards, playerGameResult.score)

private fun printGameScoreResult(playerName: String, cards: Set<Card>, score: Int) =
    println("${playerName}카드: ${parseCardsResult(cards)} - 결과: $score")

private fun parseCardsResult(cards: Set<Card>) = cards.joinToString(CARD_DELIMITER) { parseCardView(it) }

private fun printGameCompetition(dealerGameResult: DealerGameResult)
    = printGameCompetition(dealerGameResult.playerName, parseCompetitionView(dealerGameResult.competitions))

private fun printCameCompetition(playerGameResults: List<PlayerGameResult>)
    = playerGameResults.forEach { printGameCompetition(it) }

private fun printGameCompetition(playerGameResult: PlayerGameResult)
    = printGameCompetition(playerGameResult.playerName, parseCompetitionView(playerGameResult.competition))

private fun printGameCompetition(playerName: String, competition: String) = println("${playerName}: $competition")
