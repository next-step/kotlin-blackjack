package blackjack.model.game

import blackjack.model.card.CardDeck
import blackjack.model.player.Dealer
import blackjack.model.player.Player
import blackjack.model.player.Players

class BlackjackGame(
    val dealer: Dealer,
    val players: Players,
    val cards: CardDeck,
) {
    fun initDraw(printInitStatus: (Dealer, List<Player>) -> Unit) {
        repeat(2) {
            dealer.draw(cards.pop())
        }

        players.draw(cards)

        printInitStatus(dealer, players.lists)
    }

    fun play(
        inputPlayerChoice: (String) -> Boolean,
        printPlayerCards: (Player) -> Unit
    ) {
        players.lists.forEach { player ->
            while (!player.isFinished()) {
                hitOrStay(player, inputPlayerChoice)
                printPlayerCards(player)
            }
        }
    }

    fun playDealer(printDealerPop: () -> Unit) {
        if (dealer.hasPassedHurdle()) {
            dealer.draw(cards.pop())
            printDealerPop()
        }
    }

    fun result(
        printResult: (Dealer, List<Player>) -> Unit,
        printMatchResult: (List<Rank>, Map<Player, Rank>) -> Unit,
        printBettingResult: (Double, Map<Player, Double>) -> Unit,
    ) {
        printResult(dealer, players.lists)

        val matchResult = MatchResult.toResult(dealer, players.lists)
        printMatchResult(matchResult.dealerResult, matchResult.playerResults)

        printBettingResult(matchResult.dealerBenefit, matchResult.playerBenefits())
    }

    private fun hitOrStay(player: Player, inputPlayerChoice: (String) -> Boolean) {
        when (inputPlayerChoice(player.name)) {
            true -> player.draw(cards.pop())
            false -> player.stay()
        }
    }
}
