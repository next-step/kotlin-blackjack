import model.CardVendor
import model.Dealer
import model.Deck
import model.GameResultStateGenerator
import model.Names
import model.Players
import view.InputView
import view.ResultView

fun main() {
    val dealer = Dealer()
    val players = Players()
    val deck = Deck()
    val cardVendor = CardVendor(deck)
    val names = inputNames()

    createPlayer(names, players)
    giveCardToDealer(dealer, cardVendor)
    giveCardToPlayers(players, cardVendor)
    giveExtraCardToPlayer(players, cardVendor)
    giveExtraCardToDealer(dealer, cardVendor)
    printDealerCard(dealer)
    printPlayerCard(players)
    decideGameResult(dealer, players)
    printGameResult(dealer, players)
}

private fun inputNames(): Names {
    return Names(InputView.getUserName())
}

private fun giveCardToDealer(dealer: Dealer, cardVendor: CardVendor) {
    cardVendor.dealOut(dealer)
    ResultView.showPlayerCardState(dealer)
}

private fun createPlayer(names: Names, players: Players) {
    players.create(names)
    ResultView.showDistributedCard(names)
}

private fun giveCardToPlayers(players: Players, cardVendor: CardVendor) {
    players.values.forEach {
        cardVendor.dealOut(it)
    }
    ResultView.showPlayersCardState(players.values)
}

private fun giveExtraCardToPlayer(players: Players, cardVendor: CardVendor) {
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

private fun giveExtraCardToDealer(dealer: Dealer, cardVendor: CardVendor) {
    cardVendor.giveExtraCardToDealer(dealer)
}

private fun printDealerCard(dealer: Dealer) {
    ResultView.showPlayerCardStateResult(dealer)
}

private fun printPlayerCard(players: Players) {
    ResultView.showPlayersCardStateResult(players.values)
}

private fun decideGameResult(dealer: Dealer, players: Players) {
    GameResultStateGenerator().generate(dealer, players.values)
}

private fun printGameResult(dealer: Dealer, players: Players) {
    ResultView.showGameResult(dealer, players.values)
}
