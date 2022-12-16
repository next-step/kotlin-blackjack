package blackjack.application

import blackjack.domain.BlackJackGame
import blackjack.domain.CardGenerator
import blackjack.domain.CardManager
import blackjack.domain.Player

class Application {
    fun run() {
        val dealer = Player("pobi")
        val player = Player("jason")
        val cardManager = CardManager(CardGenerator())
        BlackJackGame(cardManager, listOf(dealer, player))
    }
}
