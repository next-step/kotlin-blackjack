package blackjack

import blackjack.vo.PlayerVO

fun main() {
    val playerNames = InputView.readPlayerNames()

    val deck = Deck.init()
    val players = Players.init(playerNames, deck)

    ResultView.printCardHands(players.map { PlayerVO(it) })

    players.forEach { player -> drawMore(deck, player) }
}

private fun drawMore(deck: Deck, player: Player) {
    while (deck.isNotEmpty()) {
        if (InputView.readDrawMore(player.name)) {
            player.addCard(deck.draw())
        } else {
            return
        }
        ResultView.printPlayer(PlayerVO(player))
    }
}
