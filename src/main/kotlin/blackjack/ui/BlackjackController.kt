package blackjack.ui

import blackjack.application.BlackjackService
import blackjack.domain.Deck

class BlackjackController(
    private val blackjackService: BlackjackService,
) {
    fun start(deck: Deck) {
        // 블랙젝 게임 초기화
        val names = InputView.getPlayerNames()
        val game = blackjackService.initializeGame(names, deck)

        // 초기 상태 출력
        ResultView.displayState(game)

        // 게임 진행

        // 게임 결과 출력
        ResultView.displayState(game, false)
    }
}
