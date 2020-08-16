package blackjack.model.player

import blackjack.model.card.Card
import blackjack.model.card.CardDeck
import blackjack.model.card.Cards
import blackjack.view.OutputView

const val WIN_TEXT = "승"
const val LOSE_TEXT = "패"
const val BLACKJACK_MAX_NUMBER = 21

interface Player {
    val name: String
    var cards: Cards
    var winLoseResult: String

    fun call(): Boolean
    fun checkWinOrLose(players: List<Player>)

    fun drawCard(card: Card) {
        cards = cards.plus(card)
    }

    fun getDisplayCards(): String {
        return cards.getDisplayCards()
    }

    fun getTotalPointForBlackJack(): Int {
        return cards.getBlackjackPoint()
    }

    fun progressTurnForEach(cardDeck: CardDeck) {
        while (call()) {
            drawCard(cardDeck.pick())
            OutputView.printCardForPlayers(Players(listOf(this)))
        }
    }
}
