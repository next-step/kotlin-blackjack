package blackjack.view

import blackjack.domain.Dealer
import blackjack.domain.PlayResultType
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
        if (player is Dealer && !withResult) {
            player.cards.drop(player.cards.size - 1)
                .forEach { playerCardsStringBuilder.append(" ${it.value}${it.suit.suitName}, ") }
        } else {
            player.cards.forEach { playerCardsStringBuilder.append(" ${it.value}${it.suit.suitName}, ") }
        }
        playerCardsStringBuilder.delete(playerCardsStringBuilder.lastIndex - 1, playerCardsStringBuilder.lastIndex)
        if (withResult) print(playerCardsStringBuilder) else println(playerCardsStringBuilder)
    }

    fun showGameResult(players: Players) {
        println("")
        players.players.forEach {
            showPlayerCard(it, true)
            println(" - 결과 ${it.point}")
        }
        val showGameResult = StringBuilder("\n##최종승패\n")
        val dealer = players.dealer
        showGameResult.append(
            "${dealer.name}: " +
                PlayResultType.values().joinToString {
                    "${it.typeName} ${dealer.dealerResult.getStatic()[it]}"
                } + "\n"
        )

        players.players.filterNot { it is Dealer }
            .forEach { showGameResult.append("${it.name}: ${it.playResult.typeName}\n") }
        println(showGameResult)
    }
}
