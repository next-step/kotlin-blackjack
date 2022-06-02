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
        var noCount = 0

        players.forEach {
            PrintView.askOneMoreCard(it.name)

            when (InputView.getYorN()) {
                YES -> {
                    val servedCard = Dealer.popOneCard()

                    it.offer(servedCard)
                    PrintView.printHaveCardsWithName(it.name, it.cards)
                }

                NO -> noCount++
            }
        }
    } while (noCount != players.size)
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
