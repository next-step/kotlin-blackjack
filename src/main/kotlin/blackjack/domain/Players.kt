package blackjack.domain

class Players(
    val roster: List<Player>,
) {
    val iterator: Iterator<Player>
    var currentPlayer: Player
        private set
    val isDone: Boolean
        get() = roster.all { it.isDone }

    init {
        requireNamesIsNotEmpty()
        requireNamesAreDistinct()
        iterator = roster.iterator()
        currentPlayer = iterator.next()
    }

    constructor(vararg player: Player) : this(player.toList())

    operator fun get(index: Int): Player = roster[index]

    fun dealRoundOfCardsFrom(deck: Deck) {
        roster.forEach { it.initialDrawFrom(deck) }
    }

    fun hit(deck: Deck) {
        checkIsNotDone()
        currentPlayer.hit(deck)
        skipToNextPlayerIfDone()
    }

    fun stand() {
        checkIsNotDone()
        currentPlayer.stand()
        skipToNextPlayerIfDone()
    }

    fun dealerDealtBlackjack() {
        roster.forEach(Player::dealerDealtBlackjack)
    }

    private fun checkIsNotDone() {
        check(!isDone) { "모든 플레이어가 종료했습니다." }
    }

    private fun requireNamesIsNotEmpty() {
        require(roster.isNotEmpty()) { "플레이어 목록이 비어 있습니다" }
    }

    private fun requireNamesAreDistinct() {
        val names = roster.map { it.name }
        require(names.distinct().size == roster.size) {
            "중복된 이름이 있습니다."
        }
    }

    private fun skipToNextPlayerIfDone() {
        while (currentPlayer.isDone && iterator.hasNext()) {
            currentPlayer = iterator.next()
        }
    }

    companion object {
        fun from(names: List<String>): Players = Players(names.map { Player(it) })

        fun from(vararg names: String): Players = from(names.toList())
    }
}
