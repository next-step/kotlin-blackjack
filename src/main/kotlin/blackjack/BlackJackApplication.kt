package blackjack

fun main() {
    val playerNames = InputView.inputPlayerNames()

    // TODO: 줄어든 덱을 가져와야 함
    val players = BlackJack.start(playerNames, Deck())
    OutputView.printPlayersStartCardPack(players)

    players.forEach { player ->
        while (player.isNotBust() && InputView.inputMoreCard(player.name)) {
            player.take(Deck().pick())
            OutputView.printPlayerCard(player)
        }
    }
    OutputView.printBlackJackResult(players)
}