package blackjack.view

import blackjack.domain.GameResult
import blackjack.domain.GameResultState
import blackjack.domain.NumberType
import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.domain.SuitType

object OutputView {

    private const val SEPARATOR = ", "
    private const val DEALER_MESSAGE = "%s와 "
    private const val DRAW_MESSAGE = "%s에게 2장을 나누었습니다."
    private const val DEALER_DRAW_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다."
    private const val PLAYER_CARD_OUTPUT_MESSAGE = "%s 카드: %s"
    private const val PLAYER_SCORE_OUTPUT_MESSAGE = "결과: %s"
    private const val CLOVER_DISPLAY_NAME = "클로버"
    private const val DIAMOND_DISPLAY_NAME = "다이아몬드"
    private const val HEART_DISPLAY_NAME = "하트"
    private const val SPADE_DISPLAY_NAME = "스페이드"
    private const val FINAL_RESULT_MESSAGE = "## 최종 승패"

    fun printPlayers(players: Players) {
        val output = buildString {
            append(System.lineSeparator())
            if (players.isContainDealer()) {
                append(DEALER_MESSAGE.format(players.dealer?.name?.name))
            }
            append(DRAW_MESSAGE.format(players.playersExceptedDealer.joinToString(SEPARATOR) { it.name.name }))
        }
        println(output)
    }

    fun printPlayersDrawnCards(players: Players) {
        val output = buildString {
            players.players.forEach { player ->
                append("${player.name.name}카드: ")
                append(player.cards.cards.joinToString { card -> "${card.numberType.toDisplayName()}${card.suitType.toDisplayName()}" })
                append(System.lineSeparator())
            }
        }
        println(output)
    }

    fun printPlayerDrawnCard(player: Player) {
        val output = buildString {
            append("${player.name.name}카드: ")
            append(player.cards.cards.joinToString { card -> "${card.numberType.toDisplayName()}${card.suitType.toDisplayName()}" })
        }
        println(output)
    }

    fun printDealerDraw() {
        println()
        println(DEALER_DRAW_MESSAGE)
    }

    fun printScoreResult(players: Players) {
        val output = buildString {
            append(System.lineSeparator())
            players.players.forEach {
                append(
                    PLAYER_CARD_OUTPUT_MESSAGE.format(
                        it.name.name,
                        it.cards.cards.joinToString { card -> "${card.numberType.toDisplayName()}${card.suitType.toDisplayName()}" }
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
                append("${gameResult.gameResultMap[it]}${it.toDisplayName()} ")
            }
        }
        println(output)
    }

    fun printPlayerResult(player: Player, resultState: GameResultState) {
        println("${player.name.name}: ${resultState.toDisplayName()}")
    }

    private fun GameResultState.toDisplayName(): String {
        return when (this) {
            GameResultState.Win -> {
                "승"
            }
            GameResultState.Draw -> {
                "무"
            }
            GameResultState.Lose -> {
                "패"
            }
        }
    }

    private fun SuitType.toDisplayName(): String {
        return when (this) {
            SuitType.CLOVER -> {
                CLOVER_DISPLAY_NAME
            }
            SuitType.DIAMOND -> {
                DIAMOND_DISPLAY_NAME
            }
            SuitType.HEART -> {
                HEART_DISPLAY_NAME
            }
            SuitType.SPADE -> {
                SPADE_DISPLAY_NAME
            }
        }
    }

    private fun NumberType.toDisplayName(): String {
        return when (this) {
            NumberType.TWO -> {
                "2"
            }
            NumberType.THREE -> {
                "3"
            }
            NumberType.FOUR -> {
                "4"
            }
            NumberType.FIVE -> {
                "5"
            }
            NumberType.SIX -> {
                "6"
            }
            NumberType.SEVEN -> {
                "7"
            }
            NumberType.EIGHT -> {
                "8"
            }
            NumberType.NINE -> {
                "9"
            }
            NumberType.TEN -> {
                "10"
            }
            NumberType.ACE -> {
                "A"
            }
            NumberType.KING -> {
                "K"
            }
            NumberType.QUEEN -> {
                "Q"
            }
            NumberType.JACK -> {
                "J"
            }
        }
    }
}
