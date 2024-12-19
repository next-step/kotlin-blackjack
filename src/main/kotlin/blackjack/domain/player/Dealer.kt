package blackjack.domain.player

import blackjack.domain.CardDeck

class Dealer(name: String = DEALER_NAME) : Participant(name) {
    private fun shouldDrawCard(): Boolean {
        return calculateCard() <= DRAW_THRESHOLD
    }

    override fun startTurn(
        onTurnStarted: ((Participant) -> String)?,
        onPrintResultCallback: (Participant) -> Unit,
    ) {
        if (!isBust() && shouldDrawCard()) {
            val card = CardDeck.drawCard()
            drawCard(card)
            onPrintResultCallback(this)
            onTurnStarted?.invoke(this)
        }
    }

    override fun showCards() {
        println("$name: ${getAllCards()[0].printCard()}")
    }

    override fun showGameResult() {
        println("${gameResult.getWinCount()}승 ${gameResult.getLoseCount()}패")
    }

    fun showAllCards() {
        print("${name}카드: ")

        val lastIndex = getAllCards().lastIndex
        getAllCards().forEachIndexed { index, card ->
            print(card.printCard())
            if (index != lastIndex) print(", ") else println()
        }
    }

    companion object {
        const val DEALER_NAME = "딜러"
        const val DRAW_THRESHOLD = 16
    }
}
