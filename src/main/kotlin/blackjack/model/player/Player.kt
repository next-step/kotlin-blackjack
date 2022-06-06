package blackjack.model.player

import blackjack.model.card.Card
import blackjack.model.card.Cards
import blackjack.model.card.Score

class Player(
    val name: String,
    private val hitDecisionMaker: HitDecisionMaker,
    cardList: List<Card> = listOf()
) : CardRecipient {

    private val cardList = MutableList(cardList.size) { cardList[it] }

    var score = Score(this.cardList)
        private set

    val canHit: Boolean
        get() = !this.score.isBustOrBlackJack && hitDecisionMaker.doYouWantToHit(this)

    val cards: Cards
        get() = Cards(this.cardList.toList())

    val cardCount: Int
        get() = this.cardList.size

    override fun addCard(card: Card) {
        this.cardList.add(card)
        this.score = Score(this.cardList)
    }

    fun clearCard() {
        this.cardList.clear()
    }
}

class Players(playerList: List<Player>) : List<Player> by playerList {

    val blackJackPlayer: Player?
        get() = this.find { it.score.isBlackJack }

    fun clearCard() {
        this.forEach { it.clearCard() }
    }

    companion object {
        fun List<Player>.toPlayers() = Players(this)
        fun Player.toPlayers() = Players(listOf(this))
    }
}
