package blackjack.domain

class Player private constructor(
    val name: PlayerName,
    val cards: Cards
) {

    fun isBust(): Boolean {
        return cards.bust()
    }

    fun addPlayerCard(card: Card): Cards {
        if(isBust()) throw IllegalArgumentException("플레이어가 버스트되어 카드를 추가할 수 없습니다.")
        cards.addCard(card)
        return cards
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