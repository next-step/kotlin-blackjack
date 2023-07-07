package blackjack.domain.user

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.result.MatchResult

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
    private var isHit: Boolean = true
    val score: Int
        get() = cards.score

    val status: PlayerStatus
        get() {
            return when {
                cards.isBlackjack() -> PlayerStatus.BLACKJACK
                cards.isBust() -> PlayerStatus.BUST
                isHit -> PlayerStatus.HIT
                else -> PlayerStatus.STAND
            }
        }

    init {
        require(name.isNotBlank()) { EMPTY_NAME_ERROR_MESSAGE }
    }

    fun getCardsSize() = cards.size

    fun addCard(card: Card) {
        check(status in PlayerStatus.HITTABLE_STATUS) { INVALID_ADD_CARD_STATUS_ERROR_MESSAGE }
        cards.addCard(card)
    }

    fun isBust(): Boolean {
        return status == PlayerStatus.BUST
    }

    fun match(player: Player): MatchResult {
        return when {
            player.isBlackjack() -> getBlackJackMatchResult()
            isBlackjack() -> MatchResult.BLACKJACK_WIN
            isBust() -> MatchResult.LOSE
            player.isBust() -> MatchResult.WIN
            score > player.score -> MatchResult.WIN
            score < player.score -> MatchResult.LOSE
            else -> MatchResult.DRAW
        }
    }

    private fun getBlackJackMatchResult(): MatchResult {
        return when {
            isBlackjack() -> MatchResult.DRAW
            else -> MatchResult.LOSE
        }
    }

    fun checkDraw(): Boolean {
        isHit = checkHit()
        return isHit
    }

    private fun isBlackjack(): Boolean {
        return status == PlayerStatus.BLACKJACK
    }

    protected abstract fun checkHit(): Boolean

    companion object {
        private const val EMPTY_NAME_ERROR_MESSAGE = "이름이 비어있을 수 없습니다"
        private const val INVALID_ADD_CARD_STATUS_ERROR_MESSAGE = "카드를 더 받을 수 없는 상태입니다"
    }
}
