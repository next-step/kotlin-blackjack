package blackjack

import blackjack.domain.CardDeck
import blackjack.domain.Dealer
import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.domain.RandomShuffleStrategy
import blackjack.domain.WinningDiscriminator
import blackjack.domain.WinningResult
import blackjack.ui.InputReceiver
import blackjack.ui.UI

object CardGame {
    fun run() {
        val dealer = Dealer()
        val players = InputReceiver.receiverPlayers()
        val cardDeck = CardDeck.new(RandomShuffleStrategy)

        playFirstTurn(dealer, players, cardDeck)

        UI.drawDivider()
        players.forEach {
            playPlayerTurn(it, cardDeck)
        }

        UI.drawDivider()
        playDealerTurn(dealer, cardDeck)

        drawResult(dealer, players)

        val results = WinningDiscriminator.discrimination(dealer, players)
        drawRecord(results)
    }

    private fun drawResult(dealer: Dealer, players: Players) {
        UI.drawDivider()
        UI.drawResult(dealer)
        players.forEach {
            UI.drawResult(it)
        }
    }

    private fun drawRecord(results: List<WinningResult>) {
        UI.drawDivider()
        UI.drawRecordTitle()
        results.forEach {
            UI.drawRecord(it)
        }
    }

    private fun playFirstTurn(dealer: Dealer, players: Players, cardDeck: CardDeck) {
        repeat(2) {
            dealer.draw(cardDeck)
            players.drawAllPlayer(cardDeck)
        }

        UI.drawFirstTurnMessage(dealer, players)

        UI.drawCardList(dealer)
        players.list.forEach {
            UI.drawCardList(it)
        }
    }

    private fun playPlayerTurn(player: Player, cardDeck: CardDeck) {
        while (player.canDraw() && InputReceiver.receiverWhetherDrawCard(player)) {
            player.draw(cardDeck)
            UI.drawCardList(player)
        }
    }

    private fun playDealerTurn(dealer: Dealer, cardDeck: CardDeck) {
        if (dealer.canDraw()) {
            dealer.draw(cardDeck)
            UI.drawDealerDrawMessage(dealer)
        }
    }
}

fun main() {
    CardGame.run()
}
