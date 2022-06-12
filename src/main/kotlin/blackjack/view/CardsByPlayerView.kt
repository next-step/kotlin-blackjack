package blackjack.view

import blackjack.domain.game.strategy.CardsByPlayerViewStrategy
import blackjack.domain.player.Player

class CardsByPlayerView : CardsByPlayerViewStrategy {

    override fun print(player: Player, withScore: Boolean) {
        if (withScore) {
            println("${player.name}카드: ${player.receivedCards.getCardDescription()} - 결과: ${player.score}")
            return
        }

        println("${player.name}카드: ${player.receivedCards.getCardDescription()}")
    }
}
