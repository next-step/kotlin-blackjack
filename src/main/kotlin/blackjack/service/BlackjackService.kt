package blackjack.service

import blackjack.domain.BlackjackGame
import blackjack.domain.Dealer
import blackjack.domain.Deck
import blackjack.domain.Player
import blackjack.domain.enums.Condition
import blackjack.utils.StringUtils
import blackjack.view.InputView
import blackjack.view.ResultView

class BlackjackService {

    fun initBlackjackGame(): BlackjackGame {

        val inputPlayers = InputView.inputPlayers()
        ResultView.printEnter()
        val players = StringUtils.replaceWhiteSpaceAndSplitByComma(inputPlayers)
        val dealer = Dealer(Deck())

        val blackJackPlayers = players.map { player ->
            val cards = dealer.drawCardsFromDeck(BASIC_CARD_COUNT)
            Player(name = player, cards = cards)
        }
        ResultView.printPlayers(players)
        ResultView.printPlayersAndCards(blackJackPlayers)
        ResultView.printEnter()
        return BlackjackGame(blackJackPlayers, dealer)
    }

    fun playBlackjackGame(blackjackGame: BlackjackGame) {
        blackjackGame.players.forEach { player ->
            raceBlackjack(player, blackjackGame)
        }

        ResultView.printEnter()
        ResultView.printResultGame(blackjackGame.players)
    }

    private fun raceBlackjack(player: Player, blackjackGame: BlackjackGame) {
        do {
            val answer = InputView.askForCardChoice(player)
            if (answer == Condition.PLAY.raceFlag && player.currentCondition() == Condition.PLAY) {
                val card = blackjackGame.dealer.drawCardsFromDeck(ONE_MORE_CARD_COUNT).getOneCard()
                player.hit(card)
                ResultView.printPlayerAndCards(player)
                checkCondition(player)
            } else if (answer == Condition.STAY.raceFlag) {
                player.changeCondition(Condition.STAY)
                ResultView.printPlayerAndCards(player)
            }
        } while (player.currentCondition() == Condition.PLAY)
    }

    private fun checkCondition(player: Player) {
        if (player.cards.calculateCardsTotalValue() == BLACK_JACK_NUMBER) {
            player.changeCondition(Condition.BLACKJACK)
        } else if (player.cards.calculateCardsTotalValue() > BLACK_JACK_NUMBER) {
            player.changeCondition(Condition.BUST)
        }
    }

    companion object {
        private const val BLACK_JACK_NUMBER = 21
        private const val BASIC_CARD_COUNT = 2
        private const val ONE_MORE_CARD_COUNT = 1
    }
}
