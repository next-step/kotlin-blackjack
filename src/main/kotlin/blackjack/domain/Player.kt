package blackjack.domain

class Player private constructor(
    val name: PlayerName,
    val cards: Cards
) {

    fun isBust(): Boolean {
        return cards.isBust()
    }

    fun addPlayerCard(card: Card) {
        if (isBust()) throw IllegalArgumentException("플레이어가 버스트되어 카드를 추가할 수 없습니다.")
        cards + card
    }

    companion object {

        fun from(name: PlayerName): Player {
            return Player(
                name,
                Cards.from(listOf())
            )
        }
    }
}
