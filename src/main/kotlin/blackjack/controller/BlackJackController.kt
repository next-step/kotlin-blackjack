package blackjack.controller

import blackjack.domain.CardPack
import blackjack.domain.Player
import blackjack.ui.CardView
import blackjack.ui.PlayerInputView
import blackjack.ui.model.BlackJackResult

object BlackJackController {
    fun run() {
        val cardPack = CardPack()
        val players = PlayerInputView.askPlayerNames()

        for (player in players) {
            giveTwoCards(cardPack, player)
        }

        CardView.printCardsOf(players)

        for (player in players) {
            giveCardsUntilStop(player, cardPack)
        }

        CardView.printResults(players.map { BlackJackResult.from(it) })

    }

    private fun giveCardsUntilStop(player: Player, cardPack: CardPack) {
        while (true) {
            val hasAgreed = PlayerInputView.askMoreCard(player)
            if (!hasAgreed) break

            player.takeCard(cardPack.pickCard())
            CardView.printCardsOf(player)
        }
    }

    private fun giveTwoCards(cardPack: CardPack, player: Player) {
        repeat(2) {
            player.takeCard(cardPack.pickCard())
        }
    }
}