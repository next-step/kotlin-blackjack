
import blackjack.domain.Result
import blackjack.domain.card.Score
import blackjack.domain.card.state.BlackJack
import blackjack.domain.card.state.StateFactory
import blackjack.domain.deck.Deck
import blackjack.domain.deck.DeckFactory
import blackjack.domain.player.Dealer
import blackjack.domain.player.Name
import blackjack.domain.player.Player
import blackjack.view.inputAnswer
import blackjack.view.inputPlayerNames
import blackjack.view.printParticipantsResult
import blackjack.view.printPlayerCards
import blackjack.view.printResult
import blackjack.view.printStartMessage

fun main() {
    val deck = DeckFactory.create()
    val dealer = Dealer(StateFactory.create(deck.draw(), deck.draw()))

    val playerNames = inputPlayerNames().map { Name(it) }
    val players = playerNames.map { Player(name = it, state = StateFactory.create(deck.draw(), deck.draw())) }
    printStartMessage(dealer, players)

    players.forEach {
        askTakeCard(deck, it)
    }

    while (dealer.score < Score.of(16)) {
        println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
        dealer.take(deck.draw())
    }

    val result = Result(
        players.map { it to it.match(dealer) }
            .toMap()
    )

    printParticipantsResult(listOf(dealer).plus(players))
    printResult(result)
}

private fun askTakeCard(deck: Deck, it: Player) {
    while (it.score < BlackJack.SCORE && inputAnswer(it)) {
        it.take(deck.draw())
        printPlayerCards(it)
    }
}
