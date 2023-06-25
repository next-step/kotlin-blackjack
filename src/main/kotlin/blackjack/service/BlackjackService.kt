package blackjack.service

import blackjack.domain.BlackjackGame
import blackjack.domain.Dealer
import blackjack.domain.Player
import blackjack.utils.DeckGenerator
import blackjack.utils.StringUtils
import blackjack.view.InputView
import blackjack.view.ResultView

class BlackjackService {

    fun initBlackjackGame(): BlackjackGame {

        val inputPlayers = InputView.inputPlayers()
        ResultView.printEnter()
        val players = StringUtils.replaceWhiteSpaceAndSplitByComma(inputPlayers)
        val dealer = Dealer(DeckGenerator.generateDeck())

        val blackJackPlayers = players.map { player ->
            val cards = dealer.getCardsByCount(BASIC_CARD_COUNT)
            Player(name = player, cards = cards.toMutableList())
        }
        ResultView.printPlayers(players)
        ResultView.printPlayersAndCards(blackJackPlayers)
        ResultView.printEnter()
        return BlackjackGame(blackJackPlayers, dealer)
    }

    companion object {
        private const val BASIC_CARD_COUNT = 2
    }
}
