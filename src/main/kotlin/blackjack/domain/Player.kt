package blackjack.domain

interface Player {
    val name: Name
    val cards: Cards

    fun initialCard(deck: Deck): Player {
        return this.copy(cards = this.cards.plus(deck.drawInitCards()))
    }

    fun hit(deck: Deck): Player {
        check(canHit()) { "카드를 받을 수 없습니다." }
        return this.copy(cards = this.cards.plus(deck.draw()))
    }

    fun canHit(): Boolean = !isBurst()

    fun countingCard(): Int = cards.countingCard()

    fun compareTo(player: Player): GameResult {
        val score = getScore(this) - getScore(player)
        return GameResult.of(score)
    }

    fun copy(name: Name = this.name, cards: Cards): Player

    private fun isBurst(): Boolean = countingCard() > BLACKJACK_SCORE

    private fun getScore(player: Player): Int {
        return if (player.isBurst()) 0 else player.countingCard()
    }
}
