package blackjack.service

import blackjack.domain.BlackjackGame
import blackjack.domain.Dealer
import blackjack.domain.Deck
import blackjack.domain.Player
import blackjack.domain.enums.Condition
import blackjack.domain.enums.MatchResult
import blackjack.dto.BlackjackGameResult

class BlackjackService {

    fun initBlackjackGame(players: List<String>): BlackjackGame {
        val deck = Deck()
        val dealer = Dealer(deck = deck, cards = deck.drawCard(BASIC_CARD_COUNT))
        val blackJackPlayers = players.map { player ->
            val cards = dealer.draw(BASIC_CARD_COUNT)
            Player(name = player, cards = cards)
        }
        return BlackjackGame(blackJackPlayers, dealer)
    }

    fun raceBlackjack(player: Player, blackjackGame: BlackjackGame, answer: String) {

        if (answer == Condition.PLAY.raceFlag.name.lowercase() && player.currentCondition() == Condition.PLAY) {
            val card = blackjackGame.dealer.draw(ONE_MORE_CARD_COUNT).pick()
            player.hit(card)
            checkCondition(player)
        } else if (answer == Condition.STAY.raceFlag.name.lowercase()) {
            player.changeCondition(Condition.STAY)
        }
    }

    fun resultBlackjackGame(players: List<Player>, dealer: Dealer): List<BlackjackGameResult> {
        val result = mutableListOf<BlackjackGameResult>()
        val dealerScore = dealer.cards.calculateScore()
        var (dealerWinCount, dealerDrawCount, dealerLoseCount) = listOf(0, 0, 0)

        players.forEach { player ->
            val playerScore = player.cards.calculateScore()
            when {
                dealerScore > playerScore -> {
                    dealerWinCount++
                    result.add(BlackjackGameResult(name = player.name, result = MatchResult.LOSE.match))
                }
                dealerScore < playerScore -> {
                    dealerLoseCount++
                    result.add(BlackjackGameResult(name = player.name, result = MatchResult.WIN.match))
                }
                else -> {
                    dealerDrawCount++
                    result.add(BlackjackGameResult(name = player.name, result = MatchResult.DRAW.match))
                }
            }
        }
        result.add(0, BlackjackGameResult(name = dealer.name, result = "${dealerWinCount}${MatchResult.WIN.match} ${dealerDrawCount}${MatchResult.DRAW.match} ${dealerLoseCount}${MatchResult.LOSE.match}"))
        return result
    }

    fun raceDealer(dealer: Dealer) {
        val card = dealer.draw(Dealer.ONE_DRAW_COUNT).pick()
        dealer.hit(card)
    }

    private fun checkCondition(player: Player) {
        if (player.cards.calculateScore() == BlackjackGame.BLACK_JACK_NUMBER) {
            player.changeCondition(Condition.BLACKJACK)
        } else if (player.cards.calculateScore() > BlackjackGame.BLACK_JACK_NUMBER) {
            player.changeCondition(Condition.BUST)
        }
    }

    companion object {
        const val BASIC_CARD_COUNT = 2
        private const val ONE_MORE_CARD_COUNT = 1
    }
}
