package blackjack.application

import blackjack.BlackjackCardShuffle
import blackjack.RequestView
import blackjack.domain.BlackjackBetting
import blackjack.domain.blackjackgame.BlackjackGame
import blackjack.domain.blackjackgame.BlackjackGameElement
import blackjack.domain.card.Deck
import blackjack.domain.card.Decks
import blackjack.domain.judge.BlackjackJudgement
import blackjack.domain.player.Dealer
import blackjack.domain.player.Player
import blackjack.domain.player.UserRole
import blackjack.domain.player.UserSetting
import blackjack.view.BlackjackRequestView
import blackjack.view.PlayersView
import blackjack.view.ResultView

private const val BASIC_RULE_COUNT = 2
private const val BLACKJACK_CARD_COUNT = 52
private const val BLACKJACK_PLAY_MIN_PLAYER_COUNT = BLACKJACK_CARD_COUNT / BASIC_RULE_COUNT
private const val BASIC_RULE_DELIMITER = ","

val blackjackRequestView: RequestView = BlackjackRequestView()

fun main() {
    println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
    val gamers = createGamers()

    val blackjackGame = BlackjackGame(BlackjackGameElement(gamers, createDecks(gamers.size)), blackjackRequestView)
    val playersView = PlayersView(blackjackGame.getGamers())
    playersView.showPlayerNames()
    playersView.showInitCards()

    val users = blackjackGame.play()

    val blackjackJudgement = BlackjackJudgement(users)

    val blackjackBetting = BlackjackBetting(blackjackJudgement.updateGameJudgement())

    val resultView = ResultView(blackjackBetting.getGameResult())
    resultView.result()
}

private fun createGamers(): List<UserRole> {
    return readln()
        .split(BASIC_RULE_DELIMITER).asSequence()
        .map {
            println("%s 의 배팅 금액은?".format(it))
            Player(UserSetting(it, readln().toInt()))
        }
        .plus(Dealer())
        .toList()
}

private fun createDecks(gamersSize: Int): Decks {
    val availableGameCount = (gamersSize / BLACKJACK_PLAY_MIN_PLAYER_COUNT) + 1
    val decks: MutableList<Deck> = mutableListOf()
    repeat(availableGameCount) {
        decks.add(Deck(BlackjackCardShuffle()))
    }
    return Decks(decks)
}
