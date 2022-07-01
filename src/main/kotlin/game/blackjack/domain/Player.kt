package game.blackjack.domain

open class Player(val name: String) {
    val cards: Cards = Cards()
    private var status: Status = Status.HIT
    protected val winningRecord = WinningRecord()

    fun determine(response: Boolean): Status {
        status = if (response) Status.HIT else Status.STAY
        return status
    }

    fun score(): Score = cards.score()

    fun isBust(): Boolean = cards.isBust()

    fun init(cards: List<Card>): Score {
        this.cards.add(cards)
        if (this.cards.isBlackJack()) {
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

    fun canReceive(): Boolean = status == Status.HIT

    open fun receiveUntilHit(
        action: (name: String) -> Boolean,
        showPlayerCard: (player: Player) -> Unit,
        drawCard: () -> Card
    ) {
        while (canReceive()) {
            determine(action(name))
            if (canReceive()) {
                receive(drawCard())
            }
            showPlayerCard(this)
        }
    }

    fun record(dealer: Dealer) {
        if (!dealer.isBust() && dealer.score() > score()) {
            dealer.recordWin()
            winningRecord.recordLose()
        } else {
            dealer.recordLose()
            winningRecord.recordWin()
        }
    }

    fun winningRecord() = winningRecord
}
