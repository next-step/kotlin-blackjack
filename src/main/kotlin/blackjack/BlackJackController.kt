package blackjack

import blackjack.BlackJackController.initPlayer
import blackjack.BlackJackController.initSetting
import blackjack.BlackJackController.progressGame
import blackjack.domain.Deck
import blackjack.domain.Player
import blackjack.view.InputView
import blackjack.view.OutputView

object BlackJackController {
    fun initPlayer(names: List<String>): List<Player> {
        return names.map { Player(it) }
    }

    fun initSetting(players: List<Player>, deck: Deck) {
        players.forEach {
            it.addCard(deck.draw(2))
        }
    }

    fun progressGame(player: Player, deck: Deck) {
        while (player.canDraw() && InputView.inputHitOrStand(player.name)) {
            player.addCard(deck.draw())
            OutputView.printCards(player.name, player.cards)
        }
    }
}

fun main() {
    val deck = Deck.create()
    val players = initPlayer(InputView.inputNames())

    initSetting(players, deck)
    OutputView.printDrawTwoCards(players)

    players.forEach { progressGame(it, deck) }
    OutputView.printPlayersScore(players)
}
