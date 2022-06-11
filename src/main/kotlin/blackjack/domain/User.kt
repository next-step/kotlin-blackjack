package blackjack.domain

import blackjack.constant.ErrorMessages

/**
 * 유저데이터를 갖고 있는 클래스
 * Created by Jaesungchi on 2022.06.07..
 */
data class User(val name: String) {
    val cards: Cards = Cards()

    init {
        require(name.isNotEmpty()) { ErrorMessages.NAME_IS_EMPTY }
        // 최초 2번 카드를 갖는다.
        cards.addCard()
        cards.addCard()
    }
}
