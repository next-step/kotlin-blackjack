package next.step.blackjack.controller

import next.step.blackjack.domain.card.Cards
import next.step.blackjack.domain.card.GameCards
import next.step.blackjack.domain.dealer.Dealer
import next.step.blackjack.domain.game.GameRule
import next.step.blackjack.domain.player.Players
import next.step.racing.view.InputView
import next.step.racing.view.OutputView

fun main() {
    runCatching {
        val gameCards = GameCards.shuffled()
        val dealer = Dealer.of(Cards.of(gameCards.pop(GameRule.INIT_CARD_CNT)))
        val players = Players.of(InputView.readPlayerNames()) { gameCards.pop(GameRule.INIT_CARD_CNT) }
        OutputView.showStart(dealer, players, GameRule.INIT_CARD_CNT)
        players.turn(InputView::readTurn, { gameCards.pop() }) { player -> OutputView.showPlayerCards(player) }
        dealer.turn({ gameCards.pop() }) { OutputView.showDealerHit(it) }
        OutputView.showCardsWithPoints(dealer, players)
        OutputView.showGameResult(dealer.name(), dealer.fight(players))
    }.onFailure { e ->
        OutputView.showError(e.message)
        main()
    }
}
