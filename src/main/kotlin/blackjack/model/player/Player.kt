package blackjack.model.player

import blackjack.model.card.Card
import blackjack.model.card.Cards

class Player(
    val name: String,
    private val hitDecisionMaker: HitDecisionMaker
) : CardRecipient {

    val canHit: Boolean
        get() = hitDecisionMaker.doYouWantToHit(this)

    val cards: Cards
        get() = Cards(this._cardList.toList())

    val cardCount: Int
        get() = this._cardList.size

    private val _cardList = mutableListOf<Card>()

    override fun addCard(card: Card) {
        this._cardList.add(card)
    }

    fun clearCard() {
        this._cardList.clear()
    }
}

class Players(playerList: List<Player>) : List<Player> by playerList {
    fun clearCard() {
        this.forEach { it.clearCard() }
    }

    companion object {
        fun List<Player>.toPlayers() = Players(this)
        fun Player.toPlayers() = Players(listOf(this))
    }
}
