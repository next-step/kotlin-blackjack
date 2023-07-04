package blackjack.domain.player

import blackjack.domain.card.Cards

data class Gamers(
    val gamers: List<Gamer>
) {
    init {
        validateDefaultGamerCount()
        validateDuplicate()
    }

    private fun validateDefaultGamerCount() {
        require(gamers.isNotEmpty())
    }

    private fun validateDuplicate() {
        require(gamers.size == gamers.distinct().count())
    }

    fun drawCards(cards: Cards) {
        for (gamer in gamers) {
            gamer.addCardToDeck(cards.next())
        }
    }

    fun findGamer(name: String) = gamers.find { it.name == name }
        ?: throw IllegalArgumentException("존재하지 않는 이름입니다.")
}
