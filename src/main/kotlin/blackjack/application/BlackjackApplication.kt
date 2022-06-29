package blackjack.application

import blackjack.*
import blackjack.domain.card.Deck
import blackjack.domain.player.Dealer
import blackjack.domain.player.Player
import blackjack.domain.player.UserRole
import blackjack.domain.player.UserSetting
import blackjack.view.BlackjackRequestView
import blackjack.view.PlayersView
import blackjack.view.ResultView

const val BASIC_RULE_DELIMITER = ","

val cardShuffle = BlackjackCardShuffle()
val blackjackRequestView: RequestView = BlackjackRequestView()

fun main() {
    println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
    val gamers = readln()
        .split(BASIC_RULE_DELIMITER)
        .asSequence()
        .map {
            println("%s 의 배팅 금액은?".format(it))
            Player(UserSetting(it, readln().toInt()))
        }
        .plus(Dealer())
        .toList()

    val blackjackGame = BlackjackGame(BlackjackGameElement(gamers, Deck(cardShuffle)), blackjackRequestView)
    val playersView = PlayersView(blackjackGame.getGamers())
    playersView.showPlayerNames()
    playersView.showInitCards()

    val users = blackjackGame.play()

    val blackjackJudgement = BlackjackJudgement(users)

    val resultView = ResultView(blackjackJudgement.updateGameJudgement())
    resultView.result()
}
