package blackjack.domain.user

import blackjack.constant.ErrorMessages
import blackjack.domain.card.Card
import blackjack.domain.card.Cards

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

    init {
        require(name.isNotEmpty()) { ErrorMessages.NAME_IS_EMPTY }
    }

    fun hit(card: Card) {
        _cards.addCard(card)
    }

    fun isBust(): Boolean {
        return _cards.isBust()
    }

    fun isWin(user: User): Boolean {
        if (isBust())
            return false
        if (user.isBust())
            return true
        return user._cards.getScore() < _cards.getScore()
    }
}
