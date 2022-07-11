package game.blackjack.domain

abstract class Participant(val name: String) {

    val hand: Hand = Hand()

    fun score(): Score = hand.score()

    fun isBlackJack(): Boolean = hand.isBlackJack()

    fun isBust(): Boolean = hand.isBust()

    fun init(cards: List<Card>) = hand.init(cards)

    fun receive(card: Card) = hand.receive(card)

    abstract fun receiveUntilHit(
        action: (name: String) -> Boolean,
        showPlayerCard: (participant: Participant) -> Unit,
        drawCard: () -> Card
    )
}
