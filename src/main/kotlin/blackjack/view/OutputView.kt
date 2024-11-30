package blackjack.view

import blackjack.dto.PlayerResponse

class OutputView {
    fun printInitialPlayersCards(playerResponse: PlayerResponse) {
        println("\n" + playerResponse.toFormattedStringPlayerNames() + "에게 2장씩 나누었습니다.")
        println(playerResponse.toFormattedStringPlayerCards())
    }

    fun printPlayerCannotDrawCard(
        playerName: String,
        cards: String,
    ) {
        println("${playerName}는 카드를 더 받을 수 없습니다.")
        println("${playerName}카드: $cards")
    }

    fun printSinglePlayerCards(
        playerName: String,
        cards: String,
    ) {
        println("${playerName}카드: $cards")
    }

    fun printPlayResult(playerResponse: PlayerResponse) {
        println("\n" + playerResponse.toFormattedStringPlayerResults())
    }
}
