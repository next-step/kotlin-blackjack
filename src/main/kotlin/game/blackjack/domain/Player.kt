package game.blackjack.domain

open class Player(val name: String, val money: Int) {

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
}
