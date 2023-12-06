package blackjack.domain

import java.util.EnumMap

class Participants(
    val dealer: Dealer,
    val players: Players
) {
    fun receiveInitialCards(initialCards: () -> Cards) {
        players.receiveInitialCards(initialCards)
        dealer.receiveInitialCards(initialCards.invoke())
    }

    fun playGameByPlayer(willHit: (Player) -> Boolean, card: () -> Card): Player? {
        val player = players.filterReceivable().firstOrNull() ?: return null
        val playerWillHit = willHit.invoke(player)
        if (playerWillHit) {
            player.receiveCard(card.invoke())
        } else {
            player.turnStand()
        }
        return player
    }

    fun playGameByDealer(card: () -> Card): Dealer {
        if (dealer.canReceiveOneMoreCard()) dealer.receiveCard(card.invoke())
        return dealer
    }

    fun getGameResult(): GameResults {
        val playerGameResults: List<PlayerGameResult> = players.map {
            PlayerGameResult(it.name, it versus dealer)
        }

        val dealerGameResults: EnumMap<GameResult, Int> = EnumMap(GameResult::class.java)
        playerGameResults.forEach {
            val dealerGameResult = it.gameResult.opposite()
            dealerGameResults[dealerGameResult] = dealerGameResults.getOrDefault(dealerGameResult, 0) + 1
        }
        return GameResults(playerGameResults, dealerGameResults)
    }
}
