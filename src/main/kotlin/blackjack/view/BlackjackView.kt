package blackjack.view

import blackjack.domain.Card
import blackjack.domain.Cards
import blackjack.domain.Dealer
import blackjack.domain.Dealer.Companion.DEALER_UNDER_NUMBER
import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.domain.forEachPlayer
import blackjack.service.BlackjackService.Companion.DEFAULT_INITIAL_DRAW

object BlackjackView {
    fun printInitial(dealer: Dealer, players: Players) {
        printInitialTurn(dealer.name, players.players.map { it.name })
        printDealerFirstCard(dealer)
        printPlayersCard(players)
    }

    private fun printInitialTurn(dealerName: String, playerNames: List<String>) {
        println("\n${dealerName}와 ${playerNames.joinToString(", ")}에게 ${DEFAULT_INITIAL_DRAW}장을 나누었습니다.")
    }

    fun printDealerExtraHit(dealerName: String) {
        println("\n${dealerName}는 ${DEALER_UNDER_NUMBER}이하라 한장의 카드를 더 받았습니다.")
    }

    private fun printPlayersCard(players: Players) {
        players.forEachPlayer {
            printPlayerCard(it)
        }
        println()
    }

    private fun printDealerFirstCard(dealer: Dealer) {
        val dealerFirstCard = dealer.getMyCards().cards[0]
        println("${dealer.name}: ${printCard(dealerFirstCard)}")
    }

    fun printPlayerCard(player: Player) {
        println("${player.name}카드: ${printCards(player.getMyCards())}")
    }

    fun askDraw(player: Player): Boolean {
        println("${player.name}는 한 장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        return when (readln()) {
            YES -> true
            NO -> false
            else -> throw IllegalArgumentException("잘못된 입력입니다.")
        }
    }

    private fun printCard(card: Card): String = "${card.rank.description}${card.suit.description}"

    fun printCards(cards: Cards): String = cards.cards.joinToString(", ") { printCard(it) }

    private const val YES: String = "y"
    private const val NO: String = "n"
}
