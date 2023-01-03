package blackjack.domain

interface Player {
    val name: Name
    val cards: Cards
    val score: Int
        get() = cards.getScore()

    fun drawInitialCards(deck: Deck): Player {
        return this.copy(cards = deck.drawInitCards())
    }
    fun hit(card: Card) {
        cards.add(card)
    }

    fun isHit() = cards.getScore() <= TWENTY_ONE

    fun isBlackJack() = cards.isBlackJack()

    fun copy(name: Name = this.name, cards: Cards): Player

    fun match(score: Int): ResultStatus {
        if (this.score == score) return ResultStatus.DRAW

        return when (this.score > score) {
            true -> ResultStatus.WIN
            false -> ResultStatus.LOSE
        }
    }

    companion object {
        private const val TWENTY_ONE = 21
    }
}
