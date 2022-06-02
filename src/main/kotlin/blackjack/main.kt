package blackjack

const val START_CARD_NUM = 2

fun main() {
    val players = getPlayers()

    offerInitialCards(players)
}

fun getPlayers(): List<Player> {
    PrintView.printInputUserNamesDesc()
    val userNames = InputView.getUserNames()

    return userNames.map { Player(it) }
}

fun offerInitialCards(players: List<Player>) {
    PrintView.printOfferInitialCardsWithNames(players.map { it.name })

    players.forEach { player ->
        val servedCards = List(START_CARD_NUM) { Dealer.popOneCard() }

        PrintView.printOfferedCardsWithName(player.name, servedCards)

        player.offer(Dealer.popOneCard())
    }
}