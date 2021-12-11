package blackjack.entity

class Player(
    val name: String,
    val cards: MutableList<Card> = mutableListOf()
) {

    fun hits(card: Card) {
        cards.add(card)
    }

    // TODO: 어떤 행위를 하면 좋을지 생각해보기 아무것도 없으면 의미가 없는 함수
    fun stay() {
    }
}
