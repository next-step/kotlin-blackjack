package blackjack.domain

import blackjack.enums.CardType
import blackjack.ui.model.PlayerDTO

class Player(
    val name: String
) {
    private val cards = mutableSetOf<Card>()
    val cardNames: List<String>
        get() = cards.map { it.toString() }

    fun takeCard(card: Card): Boolean {

        check(calculateCardSum() <= BLACK_JACK_TWENTY_ONE) { "21점이 넘어서 더 이상 카드를 받을 수 없습니다." }

        return cards.add(card)
    }

    fun calculateCardSum(): Int {
        var cardPointSum = cards.sumBy { it.point }
        val aceCount = cards.count { it.type == CardType.Ace }

        repeat(aceCount) {
            cardPointSum = if (cardPointSum > BLACK_JACK_TWENTY_ONE) cardPointSum - CardType.DECREMENTABLE_POINT_OF_ACE else cardPointSum
        }
        return cardPointSum
    }

    fun toPlayerDTO(): PlayerDTO {
        return PlayerDTO(name, cards.toMutableSet())
    }

    companion object {
        private const val BLACK_JACK_TWENTY_ONE = 21
    }
}
