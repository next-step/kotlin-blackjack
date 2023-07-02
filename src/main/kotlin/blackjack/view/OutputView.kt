package blackjack.view

import blackjack.domain.DealerGameResult
import blackjack.domain.DealerHands
import blackjack.domain.GameResult
import blackjack.domain.HandsDashboard
import blackjack.domain.PlayerGameResult
import blackjack.domain.PlayerHands
import blackjack.domain.card.Card

private const val PLAYER_NAME_DELIMITER = ", "
private const val CARD_DELIMITER = ", "

private const val DEALER_DISPLAY_NAME = "딜러"

fun printFirstDrawResult(handsDashBoard: HandsDashboard) {
    println("${DEALER_DISPLAY_NAME}와 ${parsePlayerNames(handsDashBoard.playerHands)}에게 2장의 카드를 나누었습니다.")
    printDealerDrawResult(handsDashBoard.dealerHands)
    handsDashBoard.playerHands.forEach { printPlayerDrawResult(it) }
}

fun printCurrentDrawResult(playerHands: PlayerHands) =
    println("${playerHands.name}카드: ${parseCardsResult(playerHands.cards)}")

fun printDealerGetDraw() = println("${DEALER_DISPLAY_NAME}는 16이하라 한장의 카드를 더 받았습니다.\n")

fun printGameResult(gameResult: GameResult) {
    printGameScoreResult(gameResult)
    println()
    println("## 최종 승패")
    printGameCompetition(gameResult.dealerGameResult)
    printCameCompetition(gameResult.playerGameResults)
}

private fun parsePlayerNames(hands: List<PlayerHands>) = hands.joinToString(PLAYER_NAME_DELIMITER) { it.name }

private fun printDealerDrawResult(hands: DealerHands) = println("${DEALER_DISPLAY_NAME}카드: ${parseCardsResult(hands.cards)}")

private fun printPlayerDrawResult(hands: PlayerHands) = println("${hands.name}카드: ${parseCardsResult(hands.cards)}")

private fun printGameScoreResult(gameResult: GameResult) {
    printGameScoreResult(gameResult.dealerGameResult)
    printGameScoreResult(gameResult.playerGameResults)
}

private fun printGameScoreResult(dealerGameResult: DealerGameResult) =
    printGameScoreResult(DEALER_DISPLAY_NAME, dealerGameResult.cards, dealerGameResult.score)

private fun printGameScoreResult(playerGameResults: List<PlayerGameResult>)
    = playerGameResults.forEach { printGameScoreResult(it) }

private fun printGameScoreResult(playerGameResult: PlayerGameResult)
    = printGameScoreResult(playerGameResult.playerName, playerGameResult.cards, playerGameResult.score)

private fun printGameScoreResult(playerName: String, cards: Set<Card>, score: Int) =
    println("${playerName}카드: ${parseCardsResult(cards)} - 결과: $score")

private fun parseCardsResult(cards: Set<Card>) = cards.joinToString(CARD_DELIMITER) { parseCardView(it) }

private fun printGameCompetition(dealerGameResult: DealerGameResult) =
    printGameCompetition(DEALER_DISPLAY_NAME, parseCompetitionView(dealerGameResult.competitions))

private fun printCameCompetition(playerGameResults: List<PlayerGameResult>)
    = playerGameResults.forEach { printGameCompetition(it) }

private fun printGameCompetition(playerGameResult: PlayerGameResult)
    = printGameCompetition(playerGameResult.playerName, parseCompetitionView(playerGameResult.competition))

private fun printGameCompetition(playerName: String, competition: String) = println("${playerName}: $competition")
