package blackjack.domain

interface BlackjackMember {
    val hand: Hand
    val state: State

    fun getCardList(): List<Card> = hand.cards

    fun canDraw(): Boolean

    fun draw(deck: Deck)

    fun getScore(): Int = hand.getScore()
}
