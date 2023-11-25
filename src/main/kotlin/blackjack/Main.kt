package blackjack

fun main() {
    val cardDeck = deck()
    val names = InputView.inputNames()
    val players = names.map { Player(it, cardDeck) }

    OutputView.printPlayersCards(players)
    players.forEach { obtainCard(it) }
    OutputView.printPlayerResult(players)
}

private fun obtainCard(player: Player) {
    while (isObtainCard(player)) {
        player.obtain()
        OutputView.printPlayerCards(player)
    }
}

private fun isObtainCard(player: Player): Boolean {
    return player.isObtainable() && InputView.inputIsObtainCard(player.name)
}

private fun deck(): Iterator<Card> {
    return (0 until 4)
        .flatMap { cards() }
        .shuffled()
        .iterator()
}

private fun cards(): List<Card> {
    return  Shape.values().flatMap { shape ->
        Number.values().map { number ->
            Card(number, shape)
        }
    }
}
