package blackjack

import blackjack.card.Deck
import blackjack.participant.Dealer
import blackjack.participant.Players
import blackjack.policy.InputMoreCardPolicy
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    val playerNames = InputView.inputPlayerNames()

    val dealer = Dealer()
    val gamePlayer = Players.of(playerNames)
    val deck = Deck()
    val game = BlackJackGame(dealer, gamePlayer, deck)

    val startGame = game.start()
    OutputView.printPlayersStartCardPack(startGame)

    // Action Item
    // [x] 1. inputMoreCard를 외부에서 주입하도록 한다. (추가 카드를 받는 기능을 제어 가능하도록 한다)
    // [ ] 2. BlackJackGame에 아래 코드를 이동시킨다.
    // [ ] 3. 최종 결과로 Result를 던지도록 한다.
    //  - Result에는 Player, Dealer에 대한 정보가 담긴다
    startGame.play(InputMoreCardPolicy)

    OutputView.printBlackJackResult(gamePlayer.player)
}
