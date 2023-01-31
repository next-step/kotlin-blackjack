import model.CardVendor
import model.Dealer
import model.Deck
import model.GameResultReader
import model.Names
import model.Players
import view.InputView
import view.ResultView

private const val DEALER = "딜러"
private val dealer: Dealer = Dealer(DEALER)
private val players: Players = Players()
private val deck = Deck()
private val cardVendor: CardVendor = CardVendor(deck)
fun main() {
    val names = inputNames()
    createPlayer(names)
    giveCardToDealer()
    giveCardToPlayers()
    giveExtraCardToDealer()
    giveExtraCardToPlayer()
    printDealerCard()
    printPlayerCard()
    decideGameResult()
    printGameResult()
}

private fun inputNames(): Names {
    return Names(InputView.getUserName())
}

private fun giveCardToDealer() {
    cardVendor.dealOut(dealer)
    ResultView.showPlayerCardState(dealer)
}

private fun createPlayer(names: Names) {
    players.create(names)
    ResultView.showDistributedCard(names)
}

private fun giveCardToPlayers() {
    players.get().forEach {
        cardVendor.dealOut(it)
    }
    ResultView.showPlayersCardState(players.get())
}

private fun giveExtraCardToPlayer() {
    cardVendor.giveExtraCardToPlayer(
        players,
        { name ->
            InputView.getExtraCard(name)
        },
        { player ->
            ResultView.showPlayerCardState(player)
        }
    )
}

private fun giveExtraCardToDealer() {
    cardVendor.giveExtraCardToDealer(dealer)
}

private fun printDealerCard() {
    ResultView.showPlayerCardStateResult(dealer)
}

private fun printPlayerCard() {
    ResultView.showPlayersCardStateResult(players.get())
}

private fun decideGameResult() {
    GameResultReader().decideResult(dealer, players.get())
}

private fun printGameResult() {
    ResultView.showGameResult(dealer, players.get())
}
