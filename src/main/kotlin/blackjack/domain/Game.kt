package blackjack.domain

import blackjack.domain.Constants.INITIAL_CARD_COUNT
import blackjack.view.ConsoleInput
import blackjack.view.ConsoleOutput

class Game(val players: Players, val dealer: Dealer = Dealer()) {

    init {
        initialCard()
    }

    fun play() {
        players.list.map { scratch(it) }
        ConsoleOutput().printLine()
    }

    private fun scratch(player: Player) {
        while (!player.isBurst() && ConsoleInput().inputScratch(player)) {
            player.hit(dealer.divide())
            ConsoleOutput().printPlayerCards(player)
        }
    }

    private fun initialCard() {
        dealer.shuffle()

        players.list.forEach { player ->
            repeat(INITIAL_CARD_COUNT) {
                player.cards.add(dealer.divide())
            }
        }
    }
}
