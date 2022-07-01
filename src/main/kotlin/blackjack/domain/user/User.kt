package blackjack.domain.user

import blackjack.constant.ErrorMessages
import blackjack.domain.InputInterface
import blackjack.domain.Money
import blackjack.domain.OutputInterface
import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.card.Deck

/**
 * 유저데이터를 갖고 있는 클래스
 * Created by Jaesungchi on 2022.06.07..
 */
open class User(val name: String, initCards: List<Card>) {
    private val _cards: Cards = Cards().apply {
        initCards.map {
            addCard(it)
        }
    }
    val cards: Cards
        get() = _cards

    var money = Money()
        private set

    init {
        require(name.isNotEmpty()) { ErrorMessages.NAME_IS_EMPTY }
    }

    fun hit(card: Card) {
        _cards.addCard(card)
    }

    fun setBatMoney(money: Int) {
        this.money += money
    }

    private fun isBust(): Boolean {
        return _cards.getScore().isBust()
    }

    private fun isBlackJack(): Boolean {
        return _cards.getSize() == Users.INIT_CARD_SIZE &&
            _cards.getScore().isBlackJackScore()
    }

    open fun getBatResult(user: User): Money {
        return when (match(user)) {
            Match.WIN -> money
            Match.WIN_BLACKJACK -> money.times(BLACKJACK_WIN_PROFIT_MARGIN)
            Match.LOSE -> -money
            else -> ZERO_MONEY
        }
    }

    protected fun match(user: User): Match {
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

    open fun hitStage(deck: Deck, input: InputInterface, output: OutputInterface) {
        while (!isBust()) {
            output.drawMoreCard(this)
            if (!input.getYesOrNo()) return
            hit(deck.takeCard())
            output.drawUserCard(this)
        }
    }

    companion object {
        const val BLACKJACK_WIN_PROFIT_MARGIN = 1.5
        private val ZERO_MONEY = Money(0)
    }
}

enum class Match {
    WIN, LOSE, DRAW, WIN_BLACKJACK, LOSE_BLACKJACK
}
