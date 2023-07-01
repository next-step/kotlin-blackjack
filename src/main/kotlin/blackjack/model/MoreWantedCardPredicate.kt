package blackjack.model

fun interface MoreWantedCardPredicate {

    fun isWantedMorePredicate(name: PlayerName): Boolean
}
