package blackjack.domain

class Players(val list: List<Player>) {
    init {
        require(list.size <= MAX_PLAYER_COUNT) { "게임 정원을 초과하였습니다." }
    }

    fun initialCard(deck: Deck): Players {
        return Players(list.map { it.initialCard(deck) })
    }

    companion object {
        const val MAX_PLAYER_COUNT = 8
    }
}
