package game.blackjack.domain

open class Player(val name: String, val money: Int) {
    val cards: Cards = Cards()
    private var status: Status = Status.HIT

    constructor(name: String) : this(name, 0)

    fun determine(response: Boolean): Status {
        status = if (response) Status.HIT else Status.STAY
        return status
    }

    fun score(): Score = cards.score()

    fun isBust(): Boolean = cards.isBust()

    fun isBlackJack(): Boolean = cards.isBlackJack()

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

    fun getWinningRecord(dealer: Dealer): WinningRecord = when {
        isBust() -> WinningRecord.LOSE
        dealer.isBust() -> WinningRecord.WIN
        isBlackJack() && dealer.isBlackJack() -> WinningRecord.TIE
        isBlackJack() -> WinningRecord.WIN
        dealer.isBlackJack() -> WinningRecord.LOSE
        score() > dealer.score() -> WinningRecord.WIN
        score() < dealer.score() -> WinningRecord.LOSE
        else -> WinningRecord.TIE
    }
}
