package blackjack.view

import blackjack.dto.GameResultResponse
import blackjack.dto.ParticipantsResponse
import blackjack.dto.SinglePlayerResponse

class OutputView {
    fun printInitialCards(participantsResponse: ParticipantsResponse) {
        println("\n" + participantsResponse.toFormattedStringPlayerNames() + "에게 2장씩 나누었습니다.")
        println(participantsResponse.toFormattedStringInitialParticipantsCard())
    }

    fun printPlayerCannotDrawCard(singlePlayerResponse: SinglePlayerResponse) {
        val playerName = singlePlayerResponse.toFormattedStringPlayerName()
        println("${playerName}는 카드를 더 받을 수 없습니다.")
        println("${playerName}카드: ${singlePlayerResponse.toFormattedStringPlayerCards()}")
    }

    fun printSinglePlayerCards(singlePlayerResponse: SinglePlayerResponse) {
        println(singlePlayerResponse.toFormattedStringPlayerCards())
    }

    fun printPlayResult(participantsResponse: ParticipantsResponse) {
        println("\n" + participantsResponse.toFormattedStringPlayerResults())
    }

    fun printDealerDrawAnnounceMessage() {
        println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.")
    }

    fun printGameResult(gameResultResponse: GameResultResponse) {
        println("\n" + gameResultResponse.toFormattedStringGameResult())
    }
}
