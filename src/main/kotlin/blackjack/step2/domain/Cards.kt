package blackjack.step2.domain

class Cards private constructor(private val cards: List<Card>) {
    val all: List<Card>
        get() = cards // 불변 리스트 그대로 반환

    fun add(card: Card): Cards {
        // 새로운 Card가 추가된 새로운 Cards 객체를 반환
        return Cards(cards.plus(card))
    }

    companion object {
        fun of(cards: List<Card>): Cards {
            return Cards(cards) // 입력 리스트를 불변 리스트로 변환
        }
    }
}
