package blackjack.business.card

fun interface CardFactory {
    fun getCards(): List<Card>
}
