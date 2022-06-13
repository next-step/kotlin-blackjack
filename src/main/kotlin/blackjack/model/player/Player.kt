package blackjack.model.player

import blackjack.model.CardDistributor
import blackjack.model.card.Card
import blackjack.model.card.Cards
import blackjack.model.card.State
import blackjack.model.card.State.BlackJack
import blackjack.model.card.State.Running

sealed class Player(val name: String, private val hitDecisionMaker: HitDecisionMaker) :
    CardRecipient {

    class Guest(name: String, hitDecisionMaker: HitDecisionMaker, val betMoney: Int = MIN_BET_MONEY) :
        Player(name, hitDecisionMaker)

    class Dealer(name: String, hitDecisionMaker: HitDecisionMaker = DealerHitDecisionMaker) :
        Player(name, hitDecisionMaker)

    private val cardList = mutableListOf<Card>()

    val state: State
        get() = State.of(this.cardList)

    val cards: Cards
        get() = Cards(this.cardList.toList())

    val cardCount: Int
        get() = this.cardList.size

    override fun addCard(card: Card) {
        this.cardList.add(card)
    }

    fun clearCard() {
        this.cardList.clear()
    }

    fun hitWhileWants(cardDistributor: CardDistributor, onHitBlock: ((Player) -> Unit)? = null) {
        while (this.canHit(cardDistributor)) {
            this.hit(cardDistributor)
            onHitBlock?.invoke(this)
        }
    }

    fun canHit(cardDistributor: CardDistributor): Boolean {
        return (this.state is Running) && hitDecisionMaker.shouldHit(
            player = this,
            cardDistributor = cardDistributor
        )
    }

    private fun hit(cardDistributor: CardDistributor) {
        cardDistributor.giveCardsTo(this)
    }

    companion object {
        const val MIN_BET_MONEY = 10000
    }
}

class Players<T : Player>(playerList: List<T>) : List<T> by playerList {

    val blackJackPlayer: Player?
        get() = this.find { it.state is BlackJack }

    fun clearCard() {
        this.forEach { it.clearCard() }
    }

    companion object {
        fun <T : Player> List<T>.toPlayers() = Players(this)
    }
}
