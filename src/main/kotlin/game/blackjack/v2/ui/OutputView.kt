package game.blackjack.v2.ui

import game.blackjack.v2.domain.Participants
import game.blackjack.v2.domain.participant.Dealer
import game.blackjack.v2.domain.participant.GameResult
import game.blackjack.v2.domain.participant.Player

object OutputView {
    private const val DRAW_INITIAL_CARDS_PROMPT = "%s와 %s에게 각각 2장을 나누었습니다."
    private const val DEALER_DRAW_ADDITIONAL_CARD_PROMPT = "딜러는 16이하라 한장의 카드를 더 받았습니다."
    private const val FINAL_GAME_RESULT_PROMPT = "## 최종 승패"

    fun printDrawInitialCardsPrompt(participants: Participants) {
        println(
            String.format(
                DRAW_INITIAL_CARDS_PROMPT,
                participants.dealer.name,
                participants.players.joinToString(", ") { it.name })
        )
    }

    fun printDealerHandCards(dealer: Dealer) {
        println("딜러: ${
            dealer.getHandCards().joinToString(", ") { card ->
                "${card.number.value}${card.shape.value}"
            }
        }"
        )
    }

    fun printPlayersHandCard(players: List<Player>) {
        println(players.joinToString("\n") { player ->
            "${player.name}카드: ${
                player.getHandCards().joinToString(", ") { card ->
                    "${card.number.value}${card.shape.value}"
                }
            }"
        })
    }

    fun printPlayerHandCard(player: Player) {
        println("${player.name}카드: ${
            player.getHandCards().joinToString(", ") { card ->
                "${card.number.value}${card.shape.value}"
            }
        }")
    }

    fun printDealerDrawAdditionalCardPrompt() {
        println(DEALER_DRAW_ADDITIONAL_CARD_PROMPT)
    }

    fun printDealerHandCardsAndScore(dealer: Dealer) {
        println("딜러: ${
            dealer.getHandCards().joinToString(", ") { card ->
                "${card.number.value}${card.shape.value}"
            }
        }" + " - 결과: ${dealer.getScore()}")
    }

    fun printPlayersHandCardsAndScore(players: List<Player>) {
        println(players.joinToString("\n") { player ->
            "${player.name}카드: ${
                player.getHandCards().joinToString(", ") { card ->
                    "${card.number.value}${card.shape.value}"
                }
            }" + " - 결과: ${player.getScore()}"
        })
    }

    fun printFinalGameResult(participants: Participants) {
        println(FINAL_GAME_RESULT_PROMPT)
        println(
            String.format(
                "딜러: %d승 %d패",
                participants.dealer.gameResultRecord[GameResult.WIN],
                participants.dealer.gameResultRecord[GameResult.LOSE]
            )
        )
        participants.players.forEach {
            println(
                String.format("%s: %s", it.name, it.gameResultRecord.description)
            )
        }
    }
}
