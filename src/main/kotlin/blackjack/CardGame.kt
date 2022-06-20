package blackjack

import blackjack.domain.BetResult
import blackjack.domain.BetResultDiscriminator
import blackjack.domain.CardDeck
import blackjack.domain.Dealer
import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.domain.RandomShuffleStrategy
import blackjack.domain.User
import blackjack.ui.InputReceiver
import blackjack.ui.UI

object CardGame {
    fun run() {
        val dealer = Dealer()
        val players = InputReceiver.receivePlayers()
        val cardDeck = CardDeck.new(RandomShuffleStrategy)

        playFirstTurn(dealer, players, cardDeck)

        UI.drawDivider()
        players.forEach {
            playTurn(it, cardDeck)
        }

        UI.drawDivider()
        playTurn(dealer, cardDeck)

        drawResult(dealer, players)

        val results = BetResultDiscriminator.discrimination(dealer, players)
        drawRecord(results)
    }

    private fun drawResult(dealer: Dealer, players: Players) {
        UI.drawDivider()
        UI.drawResult(dealer)
        players.forEach {
            UI.drawResult(it)
        }
    }

    private fun drawRecord(results: List<BetResult>) {
        UI.drawDivider()
        UI.drawRecordTitle()
        results.forEach {
            UI.drawResult(it)
        }
    }

    private fun playFirstTurn(dealer: Dealer, players: Players, cardDeck: CardDeck) {
        repeat(2) {
            dealer.draw(cardDeck)
            players.drawAllPlayer(cardDeck)
        }

        UI.drawFirstTurnMessage(dealer, players)

        UI.drawCardList(dealer)
        players.forEach {
            UI.drawCardList(it)
        }
    }

    private fun playTurn(user: User, cardDeck: CardDeck) {
        when (user) {
            is Dealer -> if (user.canDraw()) {
                user.draw(cardDeck)
                UI.drawDealerDrawMessage(user)
            }
            is Player -> while (user.canDraw() && InputReceiver.receiveWhetherDrawCard(user)) {
                user.draw(cardDeck)
                UI.drawCardList(user)
            }
        }
    }
}

fun main() {
    CardGame.run()
}
