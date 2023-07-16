package blackjack.domain

/**
 * ### 블랙잭을 플레이하는 사람을 표현하는 객체 입니다.
 */
sealed class Player {
    abstract val name: String
    abstract val deck: Deck
    abstract val canHit: Boolean

    fun receive(card: Card) {
        check(canHit) { "Can not hit anymore" }
        deck.add(card)
    }

    fun score(): Score {
        return deck.score()
    }

    fun isBlackjack(): Boolean {
        return deck.isBlackjack()
    }

    fun isBurst(): Boolean {
        return deck.isBurst()
    }
}

/**
 * ### 블랙잭 플레이어 중 딜리를 표현하는 객체입니다
 */
data class Dealer(
    override val name: String = "Dealer",
    override val deck: Deck = Deck(),
) : Player() {
    override val canHit: Boolean
        get() = score().isLessThanEqualToDealerHitThreshold && isBlackjack().not()
}

/**
 * ### 블랙잭 플레이어 중 딜러가 아닌 도전자를 표현하는 객체입니다
 */
data class Challenger(
    override val name: String,
    override val deck: Deck = Deck(),
    val bettingAmount: Int = 0
) : Player() {

    private var isStay: Boolean = false
    override val canHit: Boolean
        get() = score().isLessThanEqualToBlackjack && isStay.not() && isBlackjack().not()

    fun stay() {
        isStay = true
    }

    fun isWin(dealer: Dealer): Boolean {
        if (this.isBurst()) {
            return false
        }
        if (dealer.isBurst()) {
            return true
        }
        return this.score() >= dealer.score()
    }

    fun getEarnings(dealer: Dealer): Int {
        return if (isBlackjack() && dealer.isBlackjack()) {
            0
        } else if (isBlackjack()) {
            (bettingAmount * 1.5).toInt()
        } else if (dealer.isBlackjack()) {
            -bettingAmount
        } else {
            if (isWin(dealer)) {
                bettingAmount
            } else {
                -bettingAmount
            }
        }
    }
}
