package blackjack.model

fun interface MoreWantedCardPredicate {

    fun isWantedMorePredicate(name: String): Boolean
}
