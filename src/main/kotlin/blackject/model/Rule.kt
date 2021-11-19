package blackject.model

import blackject.model.card.CardNumber

/**
 * 게임 룰 관리 클래스
 * */
object Rule {
    const val MAX_TOTAL_NUMBER = 21
    const val MAX_NUMBER_DEALER = 16
    val EXCEPT_NUMBER = CardNumber.ACE
}
