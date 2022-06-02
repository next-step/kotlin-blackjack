package blackjack.domain

@JvmInline
value class Dealer(private val deck: Deck) {

    fun checkCardDrawable(player: Player): Boolean {
        val score = player.score

        return !score.isBlackjack && !score.isBust
    }
}
