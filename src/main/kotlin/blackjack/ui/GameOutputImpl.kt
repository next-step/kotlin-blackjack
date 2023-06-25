package blackjack.ui

import blackjack.domain.GameResult
import blackjack.domain.Player
import blackjack.domain.Players

object GameOutputImpl : GameOutput {

    override fun printDealInitialCards(players: Players) {
        buildString {
            append("\n${players.map(Player::name)}에게 2장의 나누었습니다.\n")
            players.forEach { player ->
                append("${player.name}카드: ${getCardResponse(player)}\n")
            }
        }.run(::println)
    }

    override fun printDeckStatus(player: Player) {
        val cardResponse = getCardResponse(player)
        println("${player.name}카드: $cardResponse")
    }

    override fun printAllDeckStatus(players: Players, dealer: Player) {
        buildString {
            append("\n")
            append("${dealer.name} 카드: ${getCardResponse(dealer)} - 결과 ${dealer.calculateScore()}\n")
            players.forEach { player ->
                append("${player.name}카드: ${getCardResponse(player)} - 결과: ${player.calculateScore()}\n")
            }
        }.run(::println)
    }

    private fun getCardResponse(player: Player) =
        player.currentDeck().toList().map { symbol -> "${symbol.name()}${symbol.symbol.korName}" }

    override fun printDealerOneMorePick() =
        println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.")

    override fun printOutcome(gameResult: GameResult) {
        val dealerRecord = gameResult.dealerRecord
        val playerRecords = gameResult.playerRecords
        val result = buildString {
            append("\n## 최종 수익\n")
            append("딜러: ${dealerRecord}원\n")
            playerRecords.forEach { entry ->
                append("${entry.first}: ${entry.second}원\n")
            }
        }

        println(result)
    }
}
