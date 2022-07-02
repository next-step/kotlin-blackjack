package game.blackjack.domain

open class Player(val name: String, private val money: Int) {
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

    fun getProfit(dealer: Dealer): Int = when {
        isBust() -> WinningRecord.LOSE.scale * money
        dealer.isBust() -> WinningRecord.WIN.scale * money
        isBlackJack() && dealer.isBlackJack() -> WinningRecord.TIE.scale * money
        isBlackJack() -> (WinningRecord.WIN.scale * 1.5 * money).toInt()
        dealer.isBlackJack() -> WinningRecord.LOSE.scale * money
        score() > dealer.score() -> WinningRecord.WIN.scale * money
        score() < dealer.score() -> WinningRecord.LOSE.scale * money
        else -> WinningRecord.TIE.scale * money
    }
}
