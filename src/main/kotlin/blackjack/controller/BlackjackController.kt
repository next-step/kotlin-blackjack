package blackjack.controller

import blackjack.domain.card.Deck
import blackjack.domain.user.Dealer
import blackjack.domain.user.Users
import blackjack.view.InputView
import blackjack.view.OutputView

/**
 * 블랙잭 진행을 위한 컨트롤러
 * Created by Jaesungchi on 2022.06.07..
 */
object BlackjackController {
    fun startGame() {
        val deck = Deck()
        val players = Users.of(InputView.getPlayersName(), deck)
        val dealer = Dealer(deck.takeCard())
        players.setBatMoney(InputView)

        OutputView.printHandOutMessage(players)
        OutputView.printUsersCard(players, dealer)

        players.hit(InputView, OutputView)
        dealer.hitStage(deck, InputView, OutputView)

        OutputView.printResult(players, dealer)
        OutputView.printWinAndLose(players, dealer)
    }
}
