package blackjack.model

class Player(override val id: Int, override val name: String, override val cardHand: CardHand): CardHolder {
    override val role = Role.PLAYER
    fun moreCardOrNot(askToPlayer: (name: String) -> PlayAnswer) = when (askToPlayer(name)) {
        PlayAnswer.Y -> true
        PlayAnswer.N -> false
    }
}
