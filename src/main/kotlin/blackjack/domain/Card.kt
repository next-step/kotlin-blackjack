package blackjack.domain

/**
 * 카드 데이터 클래스
 * Created by Jaesungchi on 2022.06.07..
 */
data class Card(val type: CardType, val number: CardNumber) {
    override fun toString(): String {
        return number.title + type.title
    }

    companion object {
        fun take(type: String? = null, number: String? = null): Card {
            return Card(CardType.pick(type), CardNumber.pick(number))
        }
    }
}
