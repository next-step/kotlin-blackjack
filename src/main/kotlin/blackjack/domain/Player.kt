package blackjack.domain

data class Player(val name: String) {
    private val cards: Cards = Cards()

    init {
        require(name.isNotBlank()) { WRONG_PLAYER_NAME_MESSAGE }
    }

    fun getCards(): List<Card> {
        return cards.cards.toList()
    }

    fun receiveCard(card: Card) {
        cards.add(card)
    }

    fun getCardsString(): String {
        return cards.toString()
    }

    fun canReceiveAdditionalCard(): Boolean {
        return cards.canReceiveAdditionalCard()
    }

    fun getResult(): Int {
        return cards.getResult()
    }

    companion object {
        private const val WRONG_PLAYER_NAME_MESSAGE = "잘못된 플레이어 이름입니다.(1글자 이상, 공백문자 금지)"
    }
}
