package blackjack.domain.user

import blackjack.constant.ErrorMessages
import blackjack.domain.InputInterface
import blackjack.domain.Money
import blackjack.domain.OutputInterface
import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.card.Deck

/**
 * 게임에 참여하는 플레이어의 추상화 클래스
 * Created by Jaesungchi on 2022.07.02..
 */
abstract class Player(val name: String, initCards: List<Card>) {
    private val _cards: Cards = Cards().apply {
        initCards.map {
            addCard(it)
        }
    }
    val cards: Cards
        get() = _cards

    init {
        require(name.isNotEmpty()) { ErrorMessages.NAME_IS_EMPTY }
    }

    fun hit(card: Card) {
        _cards.addCard(card)
    }

    protected fun isBust(): Boolean {
        return _cards.getScore().isBust()
    }

    private fun isBlackJack(): Boolean {
        return _cards.isBlackJack()
    }

    protected fun match(user: Player): Match {
        return when {
            isBust() && user.isBust() -> Match.DRAW
            user.isBust() -> Match.WIN
            isBust() -> Match.LOSE
            isBlackJack() && user.isBlackJack() -> Match.DRAW
            user.isBlackJack() -> Match.LOSE_BLACKJACK
            isBlackJack() -> Match.WIN_BLACKJACK
            user._cards.getScore() < _cards.getScore() -> Match.WIN
            user._cards.getScore() > _cards.getScore() -> Match.LOSE
            else -> Match.DRAW
        }
    }

    abstract fun hitStage(deck: Deck, input: InputInterface, output: OutputInterface)

    companion object {
        const val BLACKJACK_WIN_PROFIT_MARGIN = 1.5
    }
}

enum class Match {
    WIN, LOSE, DRAW, WIN_BLACKJACK, LOSE_BLACKJACK;

    fun earningMoney(money: Money): Money {
        return when (this) {
            WIN -> money
            WIN_BLACKJACK -> money.times(Player.BLACKJACK_WIN_PROFIT_MARGIN)
            LOSE -> -money
            else -> ZERO_MONEY
        }
    }

    companion object {
        private val ZERO_MONEY = Money()
    }
}
