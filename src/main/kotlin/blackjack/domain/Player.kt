package blackjack.domain

interface Player {
    val info: PlayerInfo
    val cards: Cards

    operator fun plus(players: List<Player>): List<Player> = listOf(this) + players

    fun isBiggerScoreThan(other: Player): Boolean {
        return this.getScore() > other.getScore()
    }

    fun getScore(): Int {
        return cards.totalScore
    }

    fun deal(deck: Deck): Player

    fun validateDealCallOnce() {
        require(this.cards.isEmpty()) { "deal은 처음 한 번만 할 수 있습니다." }
    }

    fun hit(deck: Deck): Player

    fun validateCanHit() {
        require(cards.isNotBusted()) { "총점(${cards.totalScore})이 21점을 초과해 카드를 더 가져올 수 없습니다. 카드목록 : $cards" }
    }

    fun canPlay(): Boolean
}
