package blackjack.view

import blackjack.domain.DEALER_NAME
import blackjack.domain.Player
import blackjack.domain.Players

object ResultView {
    fun showCardDistribution(players: Players) {
        val cardsDistributionStringBuilder = StringBuilder()
        players.players.forEach { cardsDistributionStringBuilder.append(it.name).append(", ") }
        cardsDistributionStringBuilder.delete(
            cardsDistributionStringBuilder.lastIndex - 1,
            cardsDistributionStringBuilder.lastIndex
        )
        cardsDistributionStringBuilder.append("에게 2장의 카드를 나누었습니다.")
        println(cardsDistributionStringBuilder.toString())

        players.players.forEach { showPlayerCard(it) }
    }

    fun showPlayerCard(player: Player, withResult: Boolean = false) {
        val playerCardsStringBuilder = StringBuilder()
        playerCardsStringBuilder.append("${player.name} 카드:")
        if (player.name == DEALER_NAME && !withResult) {
            player.cards.drop(player.cards.size - 1)
                .forEach { playerCardsStringBuilder.append(" ${it.value}${it.suit.suitName}, ") }
        } else {
            player.cards.forEach { playerCardsStringBuilder.append(" ${it.value}${it.suit.suitName}, ") }
        }
        playerCardsStringBuilder.delete(playerCardsStringBuilder.lastIndex - 1, playerCardsStringBuilder.lastIndex)
        if (withResult) print(playerCardsStringBuilder) else println(playerCardsStringBuilder)
    }

    fun showResult(players: Players) {
        println("")
        players.players.forEach {
            showPlayerCard(it, true)
            println(" - 결과 ${it.calculatePoint()}")
        }
    }
}
