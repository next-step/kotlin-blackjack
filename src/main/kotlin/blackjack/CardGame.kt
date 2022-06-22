package blackjack

import blackjack.domain.BetResult
import blackjack.domain.BetResultDiscriminator
import blackjack.domain.CardDeck
import blackjack.domain.Dealer
import blackjack.domain.Players
import blackjack.domain.RandomShuffleStrategy
import blackjack.ui.InputReceiver
import blackjack.ui.UI

object CardGame {
    fun run() {
        val dealer = Dealer()
        val players = InputReceiver.receivePlayers()
        val cardDeck = CardDeck.new(RandomShuffleStrategy)

        playFirstTurn(dealer, players, cardDeck)
        playTurn(players, dealer, cardDeck)

        drawResult(dealer, players)

        val results = BetResultDiscriminator.discrimination(dealer, players)
        drawRecord(results)
    }

    private fun playTurn(players: Players, dealer: Dealer, cardDeck: CardDeck) {
        UI.drawDivider()
        players.forEach {
            it.drawWhilePossible(cardDeck, InputReceiver::receiveWhetherDrawCard, UI::drawCardList)
        }
        UI.drawDivider()
        dealer.drawWhilePossible(cardDeck, { true }, UI::drawDealerDrawMessage)
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
}

fun main() {
    CardGame.run()
}
