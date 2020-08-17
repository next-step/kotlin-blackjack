package blackjack

import blackjack.domain.BlackJackGame
import blackjack.domain.Card
import blackjack.domain.Deck
import blackjack.domain.Player
import blackjack.view.BlackJackView

fun main() {
    BlackJackController().execute(BlackJackView())
}

class BlackJackController {
    fun execute(view: BlackJackView) {
        val players = view.askPlayerNames().map { Player(it) }
        val game = BlackJackGame(players, DECK)
        game.startWithTwoCards()
        view.startGame(players)
        val result = game
            .startGame(eachStep = { view.printPlayer(it) }) { player ->
                view.askPlayerToHit(player)
            }
        view.printResult(result)
    }

    companion object {
        // 블랙잭은 6팩의 카드뭉치를 덱으로 사용한다.
        val DECK = Deck.of(
            listOf(
                Card.PACK,
                Card.PACK,
                Card.PACK,
                Card.PACK,
                Card.PACK,
                Card.PACK
            )
        )
    }
}
