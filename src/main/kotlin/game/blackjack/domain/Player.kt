package game.blackjack.domain

open class Player(val name: String) {
    private val _cards: Cards = Cards()
    private var status: Status = Status.HIT
    protected val winningRecord = WinningRecord()

    val cards: Cards
        get() = _cards

    fun determine(response: Boolean): Status {
        status = if (response) Status.HIT else Status.STAY
        return status
    }

    fun score(): Score = cards.score()

    fun isBust(): Boolean = cards.isBust()

    fun receive(card: Card): Score {
        _cards.add(card)
        val score = _cards.score()
        if (score.isBust()) {
            status = Status.BUST
        }
        return score
    }

    open fun canReceive(): Boolean = status == Status.HIT

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
        if (dealer.isBust()) {
            dealer.recordLose()
            winningRecord.recordWin()
        } else {
            dealer.recordWin()
            winningRecord.recordLose()
        }
    }

    fun winningRecord() = winningRecord
}
