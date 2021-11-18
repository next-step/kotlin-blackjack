package blackject.model

import blackject.model.card.Cards

/**
 * 참가자 정보 관리 클래스
 * */
data class Person(
    val name: String,
    val cards: Cards = Cards()
) {
    fun isTakeMoreCard(maxInt: Int): Boolean {
        return maxInt > Cards.getTotalMinCount(cards.cardList)
    }
}
