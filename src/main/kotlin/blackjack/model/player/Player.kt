package blackjack.model.player

import blackjack.model.Money
import blackjack.model.card.Card
import blackjack.model.card.CardDeck
import blackjack.model.card.PlayerCards
import blackjack.model.status.PlayerStatus
import blackjack.view.OutputView

const val BLACKJACK_MAX_NUMBER = 21
const val FIRST_TURN_CARD_COUNT = 2

interface Player {
    val name: String
    var cards: PlayerCards
    var status: PlayerStatus
    var money: Money

    fun call(): Boolean

    fun add(addMoney: Money) {
        money.add(addMoney)
    }

    fun subtract(subtractMoney: Money) {
        money.subtract(subtractMoney)
    }

    fun drawCard(card: Card) {
        cards.setCardStatus()
        status = PlayerStatus.getStatus(cards)
        cards.add(card)
    }

    fun getDisplayCards(): String {
        return cards.getDisplayCards()
    }

    fun getBlackJackPoint(): Int {
        return cards.getPoint()
    }

    fun progressTurnForEach(cardDeck: CardDeck) {
        while (call()) {
            drawCard(cardDeck.pick())
            OutputView.printCardForPlayers(Players(listOf(this)))
        }
        status = PlayerStatus.DONE
    }
}
