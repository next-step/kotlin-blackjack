package step2.blackjack.model

import step2.blackjack.model.vo.CardNumber
import step2.blackjack.model.vo.CardSign

/**
 * 카드
 * */
data class Card(val cardSign: CardSign, val cardNumber: CardNumber) {

    companion object {

        /**
         * 지정한 값으로 생성
         * */
        fun from(cardSign: String, cardNumber: String): Card = Card(CardSign.from(cardSign), CardNumber.from(cardNumber))

        /**
         * 랜덤 값으로 생성
         * */
        fun random(): Card = Card(CardSign.random(), CardNumber.random())
    }
}
