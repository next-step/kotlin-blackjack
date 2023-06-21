package blackjack

import blackjack.vo.PlayerVO

fun main() {
    val playerNames = InputView.readPlayers()

    val deck = Deck.init()
    val players = Players(
        playerNames.map {
            Player(it, deck.draw(2))
        }
    )

    ResultView.printCardHands(players.map { PlayerVO(it) })
}
