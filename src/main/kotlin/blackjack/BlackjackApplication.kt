package blackjack

import blackjack.domain.Dealer
import blackjack.domain.Deck
import blackjack.domain.Player
import blackjack.domain.Result
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    val deck = Deck()
    val dealer = Dealer()
    val players = createPlayers()

    initialBetMoney(players)
    initialDeal(deck, players, dealer)
    additionalDeal(deck, players, dealer)
    showResult(players, dealer)
}

private fun createPlayers(): List<Player> = InputView.playerNames().map { Player(it) }

private fun initialBetMoney(players: List<Player>) {
    players.forEach { player ->
        player.placeBet(InputView.betMoney(player))
    }
}

private fun initialDeal(
    deck: Deck,
    players: List<Player>,
    dealer: Dealer,
) {
    OutputView.initialDeal(players.map { it.name })

    val participants = players + dealer
    participants.forEach { participant ->
        repeat(2) {
            participant.addCard(deck.pop())
        }
    }

    OutputView.dealerInitialCardList(dealer)
    players.forEach { OutputView.cardList(it) }
}

private fun additionalDeal(
    deck: Deck,
    players: List<Player>,
    dealer: Dealer,
) {
    dealToPlayers(deck, players)
    dealToDealer(deck, dealer)
}

private fun dealToPlayers(
    deck: Deck,
    players: List<Player>,
) {
    players.forEach { player ->
        while (InputView.decideDealCard(player)) {
            player.addCard(deck.pop())
            OutputView.cardList(player)
        }
    }
}

private fun dealToDealer(
    deck: Deck,
    dealer: Dealer,
) {
    while (dealer.isAdditionalDealCondition()) {
        OutputView.dealerAdditionalCondition()
        dealer.addCard(deck.pop())
    }
}

private fun showResult(
    players: List<Player>,
    dealer: Dealer,
) {
    val participants = players + dealer
    OutputView.scoreResult(participants)
    OutputView.finalProfit(Result(dealer, players))
}
