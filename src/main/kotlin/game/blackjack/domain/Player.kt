package game.blackjack.domain

open class Player(val name: String, private val money: Int) {

    val hand: Hand = Hand()

    constructor(name: String) : this(name, 0)

    fun determine(response: Boolean) = hand.setStatus(if (response) Status.HIT else Status.STAY)

    fun score(): Score = hand.score()

    fun isBust(): Boolean = hand.isBust()

    fun isBlackJack(): Boolean = hand.isBlackJack()

    fun init(cards: List<Card>) = hand.init(cards)

    fun receive(card: Card) = hand.receive(card)

    fun canReceive(): Boolean = hand.isHit()

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
