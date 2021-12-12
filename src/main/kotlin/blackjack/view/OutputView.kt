package blackjack.view

import blackjack.domain.Dealer
import blackjack.domain.GameResult
import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.domain.state.GameResultState

object OutputView {

    private const val SEPARATOR = ", "
    private const val DEALER_MESSAGE = "%s와 "
    private const val DRAW_MESSAGE = "%s에게 2장을 나누었습니다."
    private const val DEALER_DRAW_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다."
    private const val PLAYER_CARD_OUTPUT_MESSAGE = "%s 카드: %s"
    private const val PLAYER_SCORE_OUTPUT_MESSAGE = "결과: %s"
    private const val FINAL_RESULT_MESSAGE = "## 최종 승패"

    fun printHowManyCardsPlayerDrawn(dealer: Dealer, players: Players) {
        val output = buildString {
            append(System.lineSeparator())
            append(DEALER_MESSAGE.format(dealer.name.name))
            append(DRAW_MESSAGE.format(players.players.joinToString(SEPARATOR) { it.name.name }))
        }
        println(output)
    }

    fun printPlayersDrawnCards(dealer: Dealer, players: Players) {
        val output = buildString {
            append("${dealer.name.name}카드: ")
            append(dealer.cards.cards.joinToString { card -> "${card.denomination.displayName}${card.suitType.displayName}" })
            append(System.lineSeparator())
            players.players.forEach { player ->
                append("${player.name.name}카드: ")
                append(player.cards.cards.joinToString { card -> "${card.denomination.displayName}${card.suitType.displayName}" })
                append(System.lineSeparator())
            }
        }
        println(output)
    }

    fun printPlayerDrawnCard(player: Player) {
        val output = buildString {
            append("${player.name.name}카드: ")
            append(player.cards.cards.joinToString { card -> "${card.denomination.displayName}${card.suitType.displayName}" })
        }
        println(output)
    }

    fun printDealerDraw() {
        println()
        println(DEALER_DRAW_MESSAGE)
    }

    fun printScoreResult(dealer: Dealer, players: Players) {
        val output = buildString {
            append(System.lineSeparator())
            append(
                PLAYER_CARD_OUTPUT_MESSAGE.format(
                    dealer.name.name,
                    dealer.cards.cards.joinToString { card -> "${card.denomination.displayName}${card.suitType.displayName}" }
                )
            )
            append(" - ")
            append(PLAYER_SCORE_OUTPUT_MESSAGE.format(dealer.score.score))
            append(System.lineSeparator())
            players.players.forEach {
                append(
                    PLAYER_CARD_OUTPUT_MESSAGE.format(
                        it.name.name,
                        it.cards.cards.joinToString { card -> "${card.denomination.displayName}${card.suitType.displayName}" }
                    )
                )
                append(" - ")
                append(PLAYER_SCORE_OUTPUT_MESSAGE.format(it.score.score))
                append(System.lineSeparator())
            }
        }
        println(output)
    }

    fun printDealerResult(gameResult: GameResult) {
        val output = buildString {
            append(FINAL_RESULT_MESSAGE)
            append(System.lineSeparator())
            append("딜러: ")
            gameResult.gameResultMap.keys.forEach {
                append("${gameResult.gameResultMap[it]}${it.displayName} ")
            }
        }
        println(output)
    }

    fun printPlayerResult(player: Player, resultState: GameResultState) {
        println("${player.name.name}: ${resultState.displayName}")
    }
}
