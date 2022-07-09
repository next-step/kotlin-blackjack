package game.blackjack.domain

class Hand {
    private val cards: Cards = Cards()
    var status: Status = Status.HIT

    fun setStatus(status: Status): Status {
        this.status = status
        return this.status
    }

    fun score(): Score = cards.score()

    fun isBust(): Boolean = cards.isBust()

    fun isBlackJack(): Boolean = cards.isBlackJack()

    fun isHit(): Boolean = status == Status.HIT

    fun init(cards: List<Card>): Score {
        this.cards.add(cards)
        if (isBlackJack()) {
            status = Status.BLACKJACK
        }
        return this.cards.score()
    }

    fun receive(card: Card): Score {
        cards.add(card)
        if (cards.isBust()) {
            status = Status.BUST
        }
        return cards.score()
    }

    fun size() = cards.size()

    fun cards() = cards.get()
}
