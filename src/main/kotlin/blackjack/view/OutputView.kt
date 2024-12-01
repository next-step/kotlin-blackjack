package blackjack.view

import blackjack.dto.PlayersResponse
import blackjack.dto.SinglePlayerResponse

class OutputView {
    fun printInitialPlayersCards(playersResponse: PlayersResponse) {
        println("\n" + playersResponse.toFormattedStringPlayerNames() + "에게 2장씩 나누었습니다.")
        println(playersResponse.toFormattedStringPlayerCards())
    }

    fun printPlayerCannotDrawCard(singlePlayerResponse: SinglePlayerResponse) {
        val playerName = singlePlayerResponse.toFormattedStringPlayerName()
        println("${playerName}는 카드를 더 받을 수 없습니다.")
        println("${playerName}카드: ${singlePlayerResponse.toFormattedStringPlayerCards()}")
    }

    fun printSinglePlayerCards(singlePlayerResponse: SinglePlayerResponse) {
        println(singlePlayerResponse.toFormattedStringPlayerCards())
    }

    fun printPlayResult(playersResponse: PlayersResponse) {
        println("\n" + playersResponse.toFormattedStringPlayerResults())
    }
}
