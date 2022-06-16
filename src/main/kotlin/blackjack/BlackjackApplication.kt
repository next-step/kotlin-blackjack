package blackjack

import blackjack.dto.ResultDto

const val BASIC_RULE_DELIMITER = ","

val cardShuffle = BlackjackCardShuffle()
val cards = cardShuffle.getCards().toMutableList()

fun main() {
    val gamers = mutableListOf<UserRole>(Dealer())

    println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
    val players = readln()
        .split(BASIC_RULE_DELIMITER)
        .map { Player(it) }

    gamers.addAll(players)

    val blackjackGame = BlackjackGame(gamers)
    val users = blackjackGame.play()

    val blackjackJudgement = BlackjackJudgement(users)

    val resultDto = ResultDto(blackjackJudgement.updateGameJudgement())
    resultDto.result()
}
