package blackjack.domain

interface Participant {
    val name: String
    val bet: Int
    val cards: List<Card>
    val score: Int

    fun addCards(newCards: List<Card>)

    fun canContinue(): Boolean

    fun isBlackjack(): Boolean
}

open class Player(override val name: String, override val bet: Int) : Participant {
    private var hand: Hand = Hand()

    override val cards: List<Card>
        get() = hand.getCards()

    override val score: Int
        get() = hand.calculateScore()

    override fun addCards(newCards: List<Card>) {
        hand = hand.plus(newCards)
    }

    override fun canContinue(): Boolean {
        return score <= 21
    }

    override fun isBlackjack(): Boolean {
        return score == 21 && cards.size == 2
    }
}

class Dealer(private val deck: Deck, bet: Int) : Player("딜러", bet) {
    fun initialDraw() {
        addCards(deck.drawCards(2))
    }

    fun shouldDrawCard(): Boolean {
        return score <= 16
    }

    fun drawCard() {
        addCards(deck.drawCards(1))
    }
}
