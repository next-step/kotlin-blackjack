package blackjack.domain

import blackjack.ui.model.PlayerDto

class Player(
    val name: String
) : Participant {
    private val cards = mutableSetOf<Card>()
    val cardNames: List<String>
        get() = cards.map { it.toString() }

    constructor(name: String, cards: Set<Card>) : this(name) {
        this.cards.addAll(cards)
    }

    override fun takeCard(card: Card) {

        check(cardPointSum() <= BLACK_JACK_TWENTY_ONE) { "21점이 넘어서 더 이상 카드를 받을 수 없습니다." }

        cards.add(card)
    }

    override fun cardPointSum(): Int {
        var cardPointSum = cards.sumBy { it.point }
        val aceCount = cards.count { it.isAce }

        repeat(aceCount) {
            cardPointSum = changeAcePointToOneToWin(cardPointSum)
        }
        return cardPointSum
    }

    private fun changeAcePointToOneToWin(cardPointSum: Int): Int =
        if (cardPointSum > BLACK_JACK_TWENTY_ONE) cardPointSum - CardType.DECREMENTABLE_POINT_OF_ACE else cardPointSum

    override fun toPlayerDto(): PlayerDto {
        return PlayerDto(name, cards)
    }

    companion object {
        const val BLACK_JACK_TWENTY_ONE = 21
    }
}
