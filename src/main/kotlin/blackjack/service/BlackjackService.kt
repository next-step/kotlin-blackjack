package blackjack.service

import blackjack.domain.BlackjackGame
import blackjack.domain.Dealer
import blackjack.domain.Deck
import blackjack.domain.Player
import blackjack.domain.Score.Companion.BLACK_JACK_SCORE
import blackjack.domain.enums.Condition
import blackjack.dto.PlayerInfo
import blackjack.view.InputView

class BlackjackService {

    fun initBlackjackGame(players: List<PlayerInfo>): BlackjackGame {
        val deck = Deck()
        val dealer = Dealer(deck = deck, cards = deck.drawCard(BASIC_CARD_COUNT))
        dealer.checkBlackjack()
        val blackJackPlayers = players.map { player ->
            val cards = dealer.draw(BASIC_CARD_COUNT)
            Player(name = player.name, cards = cards, betAmount = player.betAmount)
        }
        blackJackPlayers.forEach { it.checkBlackjack() }
        return BlackjackGame(blackJackPlayers, dealer)
    }

    fun raceBlackjack(player: Player, blackjackGame: BlackjackGame, answer: String) {

        if (answer == InputView.go && player.currentCondition() == Condition.PLAY) {
            val card = blackjackGame.dealer.draw(ONE_MORE_CARD_COUNT).pick()
            player.hit(card)
            checkCondition(player)
        } else if (answer == InputView.stop) {
            player.changeCondition(Condition.STAY)
        }
    }

    fun raceDealer(dealer: Dealer) {
        val card = dealer.draw(Dealer.ONE_DRAW_COUNT).pick()
        dealer.hit(card)
    }

    private fun checkCondition(player: Player) {
        if (player.cards.calculateScore() == BLACK_JACK_SCORE) {
            player.changeCondition(Condition.BLACKJACK)
        } else if (player.cards.calculateScore() > BLACK_JACK_SCORE) {
            player.changeCondition(Condition.BUST)
            player.loseAllBets()
        }
    }

    companion object {
        const val BASIC_CARD_COUNT = 2
        private const val ONE_MORE_CARD_COUNT = 1
    }
}
