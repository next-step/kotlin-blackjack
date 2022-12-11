package blackjack.domain

class Player(val name: String, val cards: Cards = Cards()) {
    init {
        require(name.isNotBlank()) { "Player 이름은 공백이 될 수 없습니다. 유효한 Player 명을 입력해주세요." }
    }

    fun readyToPlay(initialCards: List<Card>) {
        require(initialCards.size == INITIAL_CARDS_COUNT) { "잘못된 초기 카드 개수 입니다. 최초 2장만 카드를 받을 수 있습니다." }
        initialCards.forEach(cards::add)
    }

    companion object {
        private const val INITIAL_CARDS_COUNT = 2
    }
}
