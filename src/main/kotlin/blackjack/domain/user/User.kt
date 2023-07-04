package blackjack.domain.user

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.result.Result

fun interface UserDrawInterface {
    fun canDraw(user: User): Boolean
}

fun interface GetUserBetMoneyInterface {
    fun getBetMoney(userName: String): Int
}

enum class PlayerStatus {
    HIT, STAND, BLACKJACK, BUST;

    companion object {
        val HITTABLE_STATUS = listOf(HIT, BLACKJACK)
    }
}

abstract class Player(
    val name: String,
    val cards: Cards,
) {
    var score: Int
        private set

    var status: PlayerStatus = PlayerStatus.HIT
        private set

    init {
        require(name.isNotBlank()) { EMPTY_NAME_ERROR_MESSAGE }
        score = cards.score()
        if (cards.isBlackjack()) {
            status = PlayerStatus.BLACKJACK
        }
    }

    fun getCardsSize() = cards.size

    fun addCard(card: Card) {
        check(status in PlayerStatus.HITTABLE_STATUS) { INVALID_ADD_CARD_STATUS_ERROR_MESSAGE }
        cards.addCard(card)
        score = cards.score()
        setPlayerStatus()
    }

    private fun setPlayerStatus() {
        status = when {
            cards.isBust() -> PlayerStatus.BUST
            else -> PlayerStatus.HIT
        }
    }

    fun isBust(): Boolean {
        return status == PlayerStatus.BUST
    }

    fun match(player: Player): Result {
        if (isBust()) {
            return Result.LOSE
        }
        if (player.isBust()) {
            return Result.WIN
        }

        return when {
            score > player.score -> Result.WIN
            score < player.score -> Result.LOSE
            else -> Result.DRAW
        }
    }

    fun isHit(): Boolean {
        val isHit = checkHit()
        if (!isHit && !isBust()) { // 버스트가 아닌데 카드를 뽑지 않는다는건 스탠드다
            status = PlayerStatus.STAND
        }
        return isHit
    }

    protected abstract fun checkHit(): Boolean

    companion object {
        private const val EMPTY_NAME_ERROR_MESSAGE = "이름이 비어있을 수 없습니다"
        private const val INVALID_ADD_CARD_STATUS_ERROR_MESSAGE = "카드를 더 받을 수 없는 상태입니다"
    }
}

class User(
    name: String,
    cards: Cards,
    private val userDrawInterface: UserDrawInterface,
    private val betMoney: Int,
) : Player(name, cards) {

    init {
        require(betMoney > 0) { INVALID_BET_MONEY_ERROR_MESSAGE }
    }

    override fun checkHit(): Boolean {
        return !isBust() && userDrawInterface.canDraw(this)
    }

    companion object {
        private const val INVALID_BET_MONEY_ERROR_MESSAGE = "배팅 금액은 0보다 커야합니다"
    }
}

class Dealer(cards: Cards) : Player(DEALER_NAME, cards) {

    override fun checkHit(): Boolean {
        return score <= DEALER_HIT_THRESHOLD
    }

    companion object {
        private const val DEALER_NAME = "딜러"
        private const val DEALER_HIT_THRESHOLD = 16
    }
}
