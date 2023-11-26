package blackjack

fun main() {
    startOutput()
    val names = namesInput()
    val players = names.map { Player(it) }
    val deck = RandomCardDeck()

    giveCardsOutput(players.joinToString(separator = ",") { it.name })
    initPlayer(players, deck)

    playGame(players, deck)

    resultOutput(players)
}

fun initPlayer(players: List<Player>, deck: CardDeck) {
    players.forEach {
        it.go(deck.drawCard())
        it.go(deck.drawCard())
        whatCardsOutput(it.name, it.cards.joinToString(separator = ",") { card -> card.name })
    }
}

fun playGame(players: List<Player>, deck: CardDeck) {
    players.forEach {
        playerGoOrStop(it, deck)
    }
}

private fun playerGoOrStop(player: Player, deck: CardDeck) {
    while (true) {
        if (isGo(player, deck)) break
    }
}

private fun isGo(player: Player, deck: CardDeck): Boolean {
    oneMoreCardOutput(player.name)
    val isGo = oneMoreCardInput()
    if (isGo) {
        player.go(deck.drawCard())
        drawResultOutput(player.name, player.cards.joinToString(separator = ",") { card -> card.name })
    } else {
        return true
    }
    return false
}