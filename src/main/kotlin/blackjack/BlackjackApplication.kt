package blackjack

import blackjack.domain.Dealer
import blackjack.domain.Deck
import blackjack.domain.Player
import blackjack.domain.RecordType
import blackjack.util.InputUtils
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    val deck = Deck()
    val dealer = Dealer()
    val players = players()

    init(deck, players, dealer)
    additionalDeal(deck, players, dealer)
    result(players, dealer)
}

private fun players(): List<Player> {
    val playerNames = InputUtils.retryInput { InputView.playerNames() }
    val players = playerNames.map { Player(it) }
    return players
}

private fun init(
    deck: Deck,
    players: List<Player>,
    dealer: Dealer,
) {
    OutputView.initialDeal(players.map { it.name })

    (players + dealer).forEach { participant ->
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
    players.forEach {
        while (InputView.decideDealCard(it)) {
            it.addCard(deck.pop())
            OutputView.cardList(it)
        }
    }

    if (dealer.isAdditionalDealCondition()) {
        OutputView.dealerAdditionalCondition()
        dealer.addCard(deck.pop())
    }
}

private fun result(
    players: List<Player>,
    dealer: Dealer,
) {
    (players + dealer).forEach { OutputView.scoreResult(it) }

    val matchResults: Map<String, RecordType> =
        players.associateBy({ player -> player.name }, { player -> player.match(dealer) })
    val dealerRecords = matchResults.values.map { it.reverse() }

    OutputView.winOrLoseTitle()
    OutputView.playersRecords(matchResults)
    OutputView.dealerRecords(dealerRecords)
}
