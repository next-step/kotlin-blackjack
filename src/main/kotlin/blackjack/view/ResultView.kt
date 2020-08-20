package blackjack.view

import blackjack.domain.BlackjackGame
import blackjack.domain.Dealer
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
        if (!withResult) playerCardsStringBuilder.append("\n")
        print(playerCardsStringBuilder)
    }

    fun showGameResultWithBetMoney(blackjackGame: BlackjackGame) {
        val players = blackjackGame.players
        println("")
        players.players.forEach {
            showPlayerCard(it, true)
            println(" - 결과 ${it.point}")
        }
        val showGameResult = StringBuilder("\n##최종 수익\n")
        showGameResult.append(getPlayersProfitMoney(blackjackGame))
        println(showGameResult)
    }

    private fun getPlayersProfitMoney(blackjackGame: BlackjackGame): StringBuilder {
        val players = blackjackGame.players
        val playersResult = StringBuilder()
        val dealerProfitMoney = players.players
            .filterNot { it is Dealer }
            .sumBy {
                val profitMoney = blackjackGame.getPlayerProfitMoney(it)
                playersResult.append("${it.name}: ${profitMoney}\n")
                -profitMoney
            }
        playersResult.insert(0, "${players.dealer.name}: ${dealerProfitMoney}\n")
        return playersResult
    }

    fun showErrorMessage(e: Exception) {
        println(e.message)
    }
}
