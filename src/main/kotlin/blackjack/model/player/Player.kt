package blackjack.model.player

import blackjack.model.card.Card
import blackjack.model.card.CardDeck
import blackjack.model.card.Cards
import blackjack.view.OutputView

const val WIN_TEXT = "승"
const val LOSE_TEXT = "패"
const val BLACKJACK_MAX_NUMBER = 21

abstract class Player(open val name: String) {
    var cards = Cards(mutableListOf())
    lateinit var winLoseResult: String

    abstract fun call(): Boolean
    abstract fun checkWinOrLose(players: List<Player>)

    fun drawCard(card: Card) {
        cards.add(card)
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
