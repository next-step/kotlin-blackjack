package blackjack

import blackjack.view.PlayersView
import blackjack.view.ResultView

const val BASIC_RULE_DELIMITER = ","

val cardShuffle = BlackjackCardShuffle()
val blackjackRequestView: RequestView = BlackjackRequestView()

fun main() {
    val gamers = mutableListOf<UserRole>(Dealer())
    println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
    val players = readln()
        .split(BASIC_RULE_DELIMITER)
        .map { Player(it) }

    gamers.addAll(players)

    val blackjackGame = BlackjackGame(BlackjackGameElement(gamers, Deck(cardShuffle)), blackjackRequestView)
    val playersView = PlayersView(blackjackGame.getGamers())
    playersView.showPlayerNames()
    playersView.showInitCards()

    val users = blackjackGame.play()

    val blackjackJudgement = BlackjackJudgement(users)

    val resultView = ResultView(blackjackJudgement.updateGameJudgement())
    resultView.result()
}
