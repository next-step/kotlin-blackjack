package blackject.model

import blackject.model.card.Card

/**
 * 참가자 정보 관리 클래스
 * */
data class Person(
    val name: String,
    val cardList: MutableList<Card>? = mutableListOf()
)
