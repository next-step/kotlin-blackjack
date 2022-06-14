package blackjack.model

class CardProvider(
    private val cardSet: Cards = CardSetGenerator.generateOneCardSet(
        CardSymbol.generateAllKinds(),
        CardNumber.values().toList()
    )
) {

    fun provideOneCardTo(player: Player) {
        val drawnCard = cardSet.removeOne()
        player.receiveCard(drawnCard)
    }
}
