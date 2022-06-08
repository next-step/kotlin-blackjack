package blackjack.domain

import blackjack.view.InputView
import blackjack.view.PrintView

const val START_CARD_NUM = 2

object BlackJackGame {

    fun run() {
        val players = getPlayers()

        offerInitialCards(players)

        proceedGame(players)

        gameEnd(players)
    }

    private fun getPlayers(): List<Player> {
        PrintView.printInputUserNamesDesc()
        val userNames = InputView.getUserNames()

        return userNames.map { Player(it) }
    }

    private fun offerInitialCards(players: List<Player>) {
        PrintView.printOfferInitialCardsWithNames(players.map { it.name })

        players.forEach { player ->
            val servedCards = List(START_CARD_NUM) { Dealer.popOneCard() }

            PrintView.printHaveCardsWithName(player.name, servedCards)

            player.offer(Cards(servedCards))
        }
    }

    private fun proceedGame(players: List<Player>) {
        do {
            val noCount = askPlayerGetNoCount(players)
        } while (noCount != players.size)
    }

    private fun askPlayerGetNoCount(players: List<Player>): Int {
        var noCount = 0

        players.forEach {
            PrintView.askOneMoreCard(it.name)

            val sayYes = askPlayerNewCard()

            offerNewCardIfSayYes(it, sayYes)

            if (!sayYes) noCount++
        }
        return noCount
    }

    private fun offerNewCardIfSayYes(player: Player, yes: Boolean) {
        if (yes) {
            val servedCard = Dealer.popOneCard()
            player.offer(servedCard)
            PrintView.printHaveCardsWithName(player.name, player.cards.cards)
        }
    }

    private fun askPlayerNewCard(): Boolean {
        return when (UserAnswer.getUserAnswer(InputView.getYorN())) {
            UserAnswer.YES -> true
            UserAnswer.NO -> false
        }
    }

    private fun gameEnd(players: List<Player>) {
        players.forEach {
            PrintView.printHaveCardsWithName(it.name, it.cards.cards, false)

            PrintView.printResultSum(it.getMaxSumLessThan21())
        }
    }
}
