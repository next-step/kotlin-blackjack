package blackject.model

import blackject.model.card.CardNumber

/**
 * 게임 룰 관리 클래스
 * */
object Rule {
    const val MAX_TOTAL_NUMBER = 21
    val EXCEPT_NUMBER = CardNumber.ACE

    fun getMaxNumber(type: PersonType): Int = if (type == PersonType.DEALER) Dealer.MAX_NUMBER_DEALER else MAX_TOTAL_NUMBER
}
