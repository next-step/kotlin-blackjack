package blackjack

import blackjack.vo.PlayerScoreVO
import blackjack.vo.PlayerVO

fun main() {
    val playerNames = InputView.readPlayerNames()

    val deck = Deck.shuffled()
    val players = Players.init(playerNames, deck)

    val playersVOs = players.map { PlayerVO.of(it) }
    ResultView.printCardHands(playersVOs)

    players.forEach { player -> drawMore(deck, player) }

    val playerScoreVOs = players.map { PlayerScoreVO(PlayerVO.of(it), it.calculateScore()) }
    ResultView.printPlayerScores(playerScoreVOs)
}

private fun drawMore(deck: Deck, player: Player) {
    while (deck.isNotEmpty()) {
        if (InputView.readDrawMore(player.name)) {
            player.hit(deck.draw())
        } else {
            return
        }
        ResultView.printPlayer(PlayerVO.of(player))
    }
}
