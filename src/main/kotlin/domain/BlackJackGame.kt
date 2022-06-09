package domain

import view.InputView
import view.InputView.displayCardDivide
import view.InputView.isYesOrNo

object BlackJackGame {

    fun initialIssue(): MutableList<Card> {
        return CardDeck.pop(2)
    }

    fun issue(): MutableList<Card> {
        return CardDeck.pop(1)
    }

    fun execute(players: List<Player>) {
        displayCardDivide(players.joinToString { it.name }, "2")
        players.forEach {
            val cards = initialIssue()
            it.offer(cards)
            InputView.displayHaveCard(it)
            println()
        }

        println()

        do {
            val noCnt = askIssueCards(players)
        } while (noCnt != players.size)
    }

    private fun askIssueCards(players: List<Player>): Int {
        var noCnt = 0

        players.forEach {
            val enableToIssue = isYesOrNo(it.name)

            if (enableToIssue) {
                val cards = issue()
                it.offer(cards)
            }
            InputView.displayHaveCard(it)
            println()
            println()

            if (!enableToIssue) noCnt++
        }
        return noCnt
    }
}
