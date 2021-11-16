package blackjack.domain.player

import blackjack.domain.card.Card

class Participant(
    val name: String,
) : CardFunction {

    init {
        require(name.isNotBlank()) { "이름은 공백제외 1글자 이상이어야 합니다." }
    }

    private var _cards = mutableListOf<Card>()

    val cards: List<Card>
        get() {
            return _cards.toList()
        }

    override fun addCard(card: Card) {
        _cards.add(card)
    }

    override fun getCardSum(): Int {
        return _cards
            .sortedByDescending { card -> card.getOrder() }
            .fold(START_INDEX) { sum, card ->
                sum + card.getValue(sum)
            }
    }

    companion object {
        private const val START_INDEX = 0
    }
}
