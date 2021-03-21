package blackjack.domain

import blackjack.ui.model.PlayerDto

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
        val aceCount = CardType.findAceCount(cards)

        repeat(aceCount) {
            cardPointSum = changeAcePointToOneToWin(cardPointSum)
        }
        return cardPointSum
    }

    private fun changeAcePointToOneToWin(cardPointSum: Int) =
        if (cardPointSum > BLACK_JACK_TWENTY_ONE) cardPointSum - CardType.DECREMENTABLE_POINT_OF_ACE else cardPointSum

    fun toPlayerDTO(): PlayerDto {
        return PlayerDto(name, cards.toMutableSet())
    }

    companion object {
        private const val BLACK_JACK_TWENTY_ONE = 21
    }
}
