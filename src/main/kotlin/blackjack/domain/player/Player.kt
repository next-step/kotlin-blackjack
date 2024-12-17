package blackjack.domain.player

import blackjack.domain.CardDeck

class Player(name: String) : Participant(name) {
    override fun startTurn(
        onTurnStarted: ((Participant) -> String)?,
        onPrintResultCallback: (Participant) -> Unit,
    ) {
        while (!isBust() && onTurnStarted?.invoke(this) == YES) {
            val card = CardDeck.drawCard()
            drawCard(card)
            onPrintResultCallback(this)
        }
    }

    override fun showCards() {
        print("${name}카드: ")

        val lastIndex = getAllCards().lastIndex
        getAllCards().forEachIndexed { index, card ->
            print(card.printCard())
            if (index != lastIndex) print(", ") else println()
        }
    }
}
