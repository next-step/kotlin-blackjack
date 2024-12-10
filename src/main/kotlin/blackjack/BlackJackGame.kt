package blackjack

import blackjack.domain.CardDeck
import blackjack.domain.CardsDeckGenerator
import blackjack.domain.GamePlayers
import blackjack.domain.Player
import blackjack.view.ResultView

class BlackJackGame {
    private val cardDeck = CardDeck.from(CardsDeckGenerator().generate())

    fun hit(player: Player) {
        player.addCard(cardDeck.pickCard())
        ResultView.printPlayerCards(player)
    }

    fun distributeTwoCards(players: GamePlayers) {
        repeat(REPEAT_TIMES_TO_DISTRIBUTE) {
            distributeToPlayersCards(players, cardDeck)
        }
    }

    private fun distributeToPlayersCards(
        players: GamePlayers,
        cardDeck: CardDeck,
    ) {
        players.forEach { player ->
            player.addCard(cardDeck.pickCard())
        }
    }

    companion object {
        private const val REPEAT_TIMES_TO_DISTRIBUTE = 2
        const val BLACK_JACK_NUMBER = 21
        const val BLACK_JACK_DEALER_MORE_CARD_NUMBER = 17
    }
}
