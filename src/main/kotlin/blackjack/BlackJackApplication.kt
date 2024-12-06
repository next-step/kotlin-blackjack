package blackjack

fun main() {
    val playerNames = InputView.inputPlayerNames()
    val players = playerNames.map { Player(PlayerName(it)) }

    val deck = Deck()
    val players1 = Players(players)
    players1.players.forEach { player -> player.take(listOf(deck.pick(), deck.pick())) }
    OutputView.printPlayersStartCardPack(players1.players)

    players1.players.forEach { player ->
        while (player.isNotBust() && InputView.inputMoreCard(player.name)) {
            player.take(deck.pick())
            OutputView.printPlayerCard(player)
        }
    }
    OutputView.printBlackJackResult(players1.players)
}