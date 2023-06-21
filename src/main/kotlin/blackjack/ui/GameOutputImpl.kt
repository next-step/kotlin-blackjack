package blackjack.ui

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

    override fun printAllDeckStatus(players: Players) {
        buildString {
            append("\n")
            players.forEach { player ->
                append("${player.name}카드: ${getCardResponse(player)} - 결과: ${player.score()}\n")
            }
        }.run(::println)
    }

    private fun getCardResponse(player: Player) =
        player.currentDeck().toList().map { symbol -> "${symbol.name()}${symbol.symbol.korName}" }
}
