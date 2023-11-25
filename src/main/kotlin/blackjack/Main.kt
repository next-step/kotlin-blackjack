package blackjack

fun main() {
    val cardDeck = deck().iterator()
    val names = InputView.inputNames()
    val players = names.map { Player(it, cardDeck.next(), cardDeck.next()) }

    OutputView.printPlayersCards(players)
    players.forEach { obtainCard(it, cardDeck) }
    OutputView.printPlayerResult(players)
}

private fun obtainCard(player: Player, cardDeck: Iterator<Card>) {
    while (isObtainCard(player)) {
        player.obtain(cardDeck.next())
        OutputView.printPlayerCards(player)
    }
}

private fun isObtainCard(player: Player): Boolean {
    return player.isObtainable() && InputView.inputIsObtainCard(player.name)
}

private fun deck(): List<Card> {
    return (0 until 4)
        .flatMap { cards() }
        .shuffled()
}

private fun cards(): List<Card> {
    return  Shape.values().flatMap { shape ->
        Number.values().map { number ->
            Card(number, shape)
        }
    }
}
