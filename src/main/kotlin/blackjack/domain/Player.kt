package blackjack.domain

class Player(val name: String, val whetherHit: (Player) -> Boolean) {
    private val _cards: MutableSet<Card> = mutableSetOf()
    val cards: Set<Card>
        get() = _cards

    private val hasAceCard: Boolean
        get() = _cards.any { it.isAce }

    private val sumScore: Int
        get() = _cards
            .map { it.number.score }
            .sum()

    private val isAdditionAceScore: Boolean
        get() = Game.WINNER_SCORE - sumScore >= Game.ACE_ADDITIONAL_SCORE

    private val additionAceCardScore: Int
        get() = if (hasAceCard && isAdditionAceScore) {
            Game.ACE_ADDITIONAL_SCORE
        } else {
            0
        }

    val score: Int
        get() = sumScore + additionAceCardScore

    val isDrawable: Boolean
        get() = Game.WINNER_SCORE > sumScore

    init {
        require(name.isNotBlank()) { "player name is not blank." }
    }

    fun receiveCard(card: Card) {
        require(isDrawable) { "player can not receive card." }
        _cards.add(card)
    }

    fun hit(drawCard: () -> Card, printPlayer: (Player) -> Unit) {
        while (isDrawable && whetherHit(this)) {
            receiveCard(drawCard())
            printPlayer(this)
        }
    }
}
