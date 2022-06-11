package blackjack.domain

import blackjack.view.InputView
import blackjack.view.PrintView

const val START_CARD_NUM = 2

object BlackJackGame {

    fun run() {
        val players = getPlayers()

        val servedInitialPlayers = offerInitialCards(players)

        val gameEndPlayers = proceedGame(servedInitialPlayers)

        gameEnd(gameEndPlayers)
    }

    private fun getPlayers(): List<Player> {
        PrintView.printInputUserNamesDesc()
        val userNames = InputView.getUserNames()

        return userNames.map { Player(it) }
    }

    private fun offerInitialCards(players: List<Player>): List<Player> {
        val servedInitialPlayers = List(players.size) { players[it] }

        PrintView.printOfferInitialCardsWithNames(servedInitialPlayers.map { it.name })

        servedInitialPlayers.forEach { player ->
            val servedCards = List(START_CARD_NUM) { Dealer.popOneCard() }

            PrintView.printHaveCardsWithName(player.name, servedCards)

            player.offer(Cards(servedCards))
        }

        return servedInitialPlayers
    }

    private fun proceedGame(players: List<Player>): List<Player> {
        val endPlayers = List(players.size) { players[it] }
        do {
            val noCount = askPlayerGetNoCount(endPlayers)
        } while (noCount != players.size)
        return endPlayers
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
