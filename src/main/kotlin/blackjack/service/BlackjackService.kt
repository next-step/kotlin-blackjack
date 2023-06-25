package blackjack.service

import blackjack.domain.BlackjackGame
import blackjack.domain.Dealer
import blackjack.domain.Deck
import blackjack.domain.Player
import blackjack.domain.enums.Condition

class BlackjackService {

    fun initBlackjackGame(players: List<String>): BlackjackGame {
        val dealer = Dealer(Deck())
        val blackJackPlayers = players.map { player ->
            val cards = dealer.drawCardsFromDeck(BASIC_CARD_COUNT)
            Player(name = player, cards = cards)
        }
        return BlackjackGame(blackJackPlayers, dealer)
    }

    fun raceBlackjack(player: Player, blackjackGame: BlackjackGame, answer: String) {

        if (answer == Condition.PLAY.raceFlag && player.currentCondition() == Condition.PLAY) {
            val card = blackjackGame.dealer.drawCardsFromDeck(ONE_MORE_CARD_COUNT).getOneCard()
            player.hit(card)
            checkCondition(player)
        } else if (answer == Condition.STAY.raceFlag) {
            player.changeCondition(Condition.STAY)
        }
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
