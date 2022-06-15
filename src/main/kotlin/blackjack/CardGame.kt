package blackjack

import blackjack.domain.BetResult
import blackjack.domain.CardDeck
import blackjack.domain.DealerRule
import blackjack.domain.Player
import blackjack.domain.PlayerRule
import blackjack.domain.Players
import blackjack.domain.RandomShuffleStrategy
import blackjack.domain.WinningDiscriminator
import blackjack.ui.InputReceiver
import blackjack.ui.UI

object CardGame {
    fun run() {
        val dealer = Player("딜러", rule = DealerRule)
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

        val results = WinningDiscriminator.discrimination(dealer, players)
        drawRecord(results)
    }

    private fun drawResult(dealer: Player, players: Players) {
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

    private fun playFirstTurn(dealer: Player, players: Players, cardDeck: CardDeck) {
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

    private fun playTurn(player: Player, cardDeck: CardDeck) {
        when (player.rule) {
            DealerRule -> if (player.canDraw()) {
                player.draw(cardDeck)
                UI.drawDealerDrawMessage(player)
            }
            PlayerRule -> while (player.canDraw() && InputReceiver.receiveWhetherDrawCard(player)) {
                player.draw(cardDeck)
                UI.drawCardList(player)
            }
        }
    }
}

fun main() {
    CardGame.run()
}
