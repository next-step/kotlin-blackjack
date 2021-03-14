package blackjack.controller

import blackjack.domain.CardPack
import blackjack.ui.CardView
import blackjack.ui.PlayerInputView

object BlackJackController {
    fun run() {
        val cardPack = CardPack()
        val players = PlayerInputView.askPlayerNames()

        repeat(2) {
            for (player in players) {
                player.takeCard(cardPack.pickCard())
            }
        }
        CardView.printCardsOf(players)

        for (player in players) {
            while (true) {
                val hasAgreed = PlayerInputView.askMoreCard(player)
                if (!hasAgreed) break
                player.takeCard(cardPack.pickCard())
                CardView.printCardsOf(player)
            }
        }
        println()

        for (player in players) {
            CardView.printResult(player, player.calculateCardSum())
        }
    }
}