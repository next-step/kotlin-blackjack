package blackjack.domain

class Players(
    private val values: List<Player>
) : List<Player> by values {

    fun giveFirstTwoCardsToAllPlayers(cardPack: CardPack) {
        values.forEach { it.takeFirstTwoCards(cardPack.poll(), cardPack.poll()) }
    }
}
