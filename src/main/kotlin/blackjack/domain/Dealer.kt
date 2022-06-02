package blackjack.domain

@JvmInline
value class Dealer(private val deck: Deck) {

    fun checkCardDrawable(player: Player): Boolean {
        val score = player.score

        return !score.isBlackjack && !score.isBust
    }

    fun giveCard(player: Player) {
        check(checkCardDrawable(player)) { "카드를 추가할 수 없습니다" }

        player.addCard(deck.draw())
    }
}
