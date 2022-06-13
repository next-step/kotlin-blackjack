package blackjack

import blackjack.dto.PlayersDto
import blackjack.dto.ResultDto

const val TOP_CARD = 0
const val BASIC_RULE_COUNT = 2
const val BASIC_RULE_DELIMITER = ","

val cardShuffle = BlackjackCardShuffle()
val cards = cardShuffle.getCards().toMutableList()

fun main() {
    println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
    val players = readln()
        .split(BASIC_RULE_DELIMITER)
        .map { Player(it) }

    repeat(BASIC_RULE_COUNT) {
        players.forEach {
            it.draw(cards.removeAt(TOP_CARD))
        }
    }

    val playersDto = PlayersDto(players)
    playersDto.showPlayerNames()
    playersDto.showInitCards()

    val results = mutableListOf<Player>()
    for (player in players) {
        var p = player
        while (!p.isFinish()) {
            p = deal(p)
        }
        results.add(p)
    }

    val resultDto = ResultDto(results)
    resultDto.result()
}

private fun deal(player: Player): Player {
    println("%s 님은 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)".format(player.name))
    return when (readln()) {
        "y" -> player.draw(cards.removeAt(0))
        else -> player.stand()
    }
}
