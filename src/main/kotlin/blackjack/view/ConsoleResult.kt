package blackjack.view

import blackjack.domain.player.Dealer
import blackjack.domain.player.Player

object ConsoleResult {
    fun drawAllFirstTwoCards(participants: List<Player>) {
        println()
        println("딜러와 ${participants.joinToString { it.name }}에게 2장의 카드를 나누었습니다.")
    }

    fun printCardsOfPlayers(players: List<Player>) {
        players.forEach { printCardsOfPlayer(it) }
    }

    fun printCardsOfPlayer(player: Player) {
        println("${player.name} 카드: ${player.cards.cards.joinToString { card -> card.character.mark + card.shape.korean }}")
    }

    fun printCardsAndTotalScoreOfPlayers(players: List<Player>) {
        println()
        players.forEach {
            println("${it.name} 카드: ${it.cards.cards.joinToString { card -> card.character.mark + card.shape.korean }} - 결과: ${it.totalScore}")
        }
    }

    fun notifyDealerMoreOneCard(dealer: Player) {
        println("${dealer.name}는 16이하라 한장의 카드를 더 받았습니다.")
    }

    fun printGameResults(dealer: Dealer, participants: List<Player>) {
        println()
        println("## 최종 승패")
        println("${dealer.name}: ${dealer.profit}")

        participants.forEach { println("${it.name}: ${it.profit}") }
    }
}
