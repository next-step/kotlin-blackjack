package blackjack.view

import blackjack.domain.game.BlackJack
import blackjack.domain.player.Dealer
import blackjack.domain.player.Player
import blackjack.view.CardView.cardsToString

class GameView(
    private val blackJack: BlackJack,
    private val dealer: Dealer,
    private val players: List<Player>,
) {

    fun firstRoundState() {
        println()
        dealerCards(dealer)
        for (player in players) {
            playerCards(player)
        }
    }

    fun run() {
        println()
        players.forEach { hitCard(it) }
        hitCardByDealer()
    }

    private fun hitCard(player: Player) {
        println("${player.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        val input = readlnOrNull() ?: throw IllegalArgumentException()
        when (input) {
            "y" -> {
                if (blackJack.canHitPlayer(player)) {
                    blackJack.giveCard(player)
                } else {
                    println("더 이상 카드를 받을 수 없습니다.")
                    return
                }
                playerCards(player)
                hitCard(player)
            }
            "n" -> {
                playerCards(player)
                return
            }
            else -> {
                println("다시 입력해주세요. (예는 y, 아니오는 n)")
                hitCard(player)
            }
        }
    }

    private fun hitCardByDealer() {
        blackJack.giveCardToDealer()
        if (dealer.cards.size >= 3) {
            println()
            println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
        }
    }

    private fun dealerCards(dealer: Dealer) {
        println("${dealer.name}: ${cardsToString(dealer.cards).joinToString(", ")}")
    }

    private fun playerCards(player: Player) {
        println("${player.name}카드: ${cardsToString(player.cards).joinToString(", ")}")
    }
}
