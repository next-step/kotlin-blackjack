package blackjack

const val START_CARD_NUM = 2
const val YES = "y"
const val NO = "n"

fun main() {
    val players = getPlayers()

    offerInitialCards(players)

    proceedGame(players)

    gameEnd(players)
}

fun gameEnd(players: List<Player>) {
    players.forEach {
        PrintView.printHaveCardsWithName(it.name, it.cards, false)

        PrintView.printResultSum(it.getCardSum())
    }
}

fun proceedGame(players: List<Player>) {
    do {
        val noCount = askPlayerGetNoCount(players)
    } while (noCount != players.size)
}

fun askPlayerGetNoCount(players: List<Player>): Int {
    var noCount = 0

    players.forEach {
        PrintView.askOneMoreCard(it.name)

        val sayYes = askPlayerNewCard()

        offerNewCardIfSayYes(it, sayYes)

        if (!sayYes) noCount++
    }
    return noCount
}

fun offerNewCardIfSayYes(player: Player, yes: Boolean) {
    if (yes) {
        val servedCard = Dealer.popOneCard()
        player.offer(servedCard)
        PrintView.printHaveCardsWithName(player.name, player.cards)
    }
}

fun askPlayerNewCard(): Boolean {
    return when (InputView.getYorN()) {
        YES -> true
        NO -> false
        else -> false
    }
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

        PrintView.printHaveCardsWithName(player.name, servedCards)

        player.offer(servedCards)
    }
}
